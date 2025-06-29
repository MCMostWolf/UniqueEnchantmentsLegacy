package com.wolf.ue.event;

import com.wolf.ue.enchantments.init.RegisterEnchantments;
import com.wolf.ue.linkage.AlexsCaves;
import com.wolf.ue.util.AttributeManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static com.wolf.ue.UniqueEnchantments.MODID;
import static com.wolf.ue.util.AttributeManager.DEATH_AVERSION_MODIFIER_UUID;
import static com.wolf.ue.util.AttributeManager.removeModifierSafety;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EnchantmentEventHandler {
    // 末影阅历
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        Level level = player.level();
        if (stack.getEnchantmentLevel(RegisterEnchantments.ENDER_TELEPORT.get()) > 0) {
            if (stack.getItem() instanceof MapItem) {
                MapItemSavedData mapdata = MapItem.getSavedData(stack, player.level());
                if (mapdata == null || !mapdata.dimension.location().equals(player.level().dimension().location())) {
                    return;
                }
                BlockPos position = null;
                CompoundTag tag = stack.getTag();
                if (tag != null) {
                    ListTag list = tag.getList("Decorations", 10);
                    for (int i = 0, m = list.size(); i < m; i++) {
                        CompoundTag nbtData = list.getCompound(i);
                        if (nbtData.getString("id").equalsIgnoreCase("+")) {
                            position = new BlockPos(nbtData.getInt("x"), player.getBlockY(), nbtData.getInt("z"));
                        }
                    }
                    if (position != null) {
                        player.teleportTo(position.getX(), position.getY(), position.getZ());
                        int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, position.getX(), position.getZ());
                        player.teleportTo(position.getX(), y, position.getZ());
                    } else {
                        player.teleportTo(player.getX() + ((int) (Math.random() * 10000) - 5000), player.getY(), player.getZ() + ((int) (Math.random() * 10000) - 5000));
                        int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, (int) player.getX(), (int) player.getZ());
                        player.teleportTo(player.getX(), y + 0.5, player.getZ());
                    }
                }

            }
            if (ModList.get().isLoaded("alexscaves")) {
                AlexsCaves.enderTeleportAC(stack, player);
            }
            stack.setCount(0);
        }
    }

    // 死亡憎恶
    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        Player respawnPlayer = event.getEntity();
        Player originPlayer = event.getOriginal();
        AttributeInstance originMaxHealth = originPlayer.getAttribute(Attributes.MAX_HEALTH);
        if (originMaxHealth != null && originMaxHealth.hasModifier(AttributeManager.DEATH_AVERSION_MODIFIER)) {
            AttributeManager.addModifierSafety(respawnPlayer, Attributes.MAX_HEALTH, AttributeManager.DEATH_AVERSION_MODIFIER);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getOffhandItem()
                    .getEnchantmentLevel(RegisterEnchantments.DEATH_AVERSION.get()) > 0 || player.getMainHandItem()
                    .getEnchantmentLevel(RegisterEnchantments.DEATH_AVERSION.get()) > 0) {
                AttributeManager.addModifierSafety(player, Attributes.MAX_HEALTH, AttributeManager.DEATH_AVERSION_MODIFIER);
            }
            for (ItemStack stack : player.getArmorSlots()) {
                if (stack.getEnchantmentLevel(RegisterEnchantments.DEATH_AVERSION.get()) > 0) {
                    AttributeManager.addModifierSafety(player, Attributes.MAX_HEALTH, AttributeManager.DEATH_AVERSION_MODIFIER);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEquipChange(LivingEquipmentChangeEvent event) {
        ItemStack toStack = event.getTo();
        if (toStack.getEnchantmentLevel(RegisterEnchantments.DEATH_AVERSION.get()) > 0) {
            AttributeManager.removeModifierSafety(event.getEntity(), Attributes.MAX_HEALTH, DEATH_AVERSION_MODIFIER_UUID);
        }
    }

    // 白金之星
    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        Player attacker = event.getEntity();
        if (attacker.getMainHandItem().getEnchantmentLevel(RegisterEnchantments.COMBO_STAR.get()) > 0) {
            if (event.isVanillaCritical()) {
                AttributeModifier modifier = AttributeManager.getModifierSafety(attacker, Attributes.ATTACK_DAMAGE, AttributeManager.COMBO_STAR_MODIFIER_UUID);
                if (modifier == null) {
                    AttributeManager.addModifierSafety(attacker, Attributes.ATTACK_DAMAGE, AttributeManager.COMBO_STAR_MODIFIER);
                } else {
                    AttributeManager.changeModifierSafety(attacker, Attributes.ATTACK_DAMAGE, AttributeManager.newComboStarModifier(modifier.getAmount() + 0.5));
                }
            } else {
                removeModifierSafety(attacker, Attributes.ATTACK_DAMAGE, AttributeManager.COMBO_STAR_MODIFIER_UUID);
                AttributeManager.addModifierSafety(attacker, Attributes.ATTACK_DAMAGE, new AttributeModifier(AttributeManager.COMBO_STAR_MODIFIER_UUID, AttributeManager.COMBO_STAR_MODIFIER_NAME, -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } else {
            AttributeManager.removeModifierSafety(attacker, Attributes.ATTACK_DAMAGE, AttributeManager.COMBO_STAR_MODIFIER_UUID);
        }
    }

    // 瘟疫憎恶
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offhandItem = player.getOffhandItem();
        boolean shouldPestilence = false;
        boolean shouldEco = false;
        int pestilenceRadius = 0;
        List<ItemStack> stacks = new ArrayList<>();
        if (player.level().getGameTime() % 40 == 0) {
            if (mainHandItem.getEnchantmentLevel(RegisterEnchantments.PESTILENCES_ODIUM.get()) > 0 || offhandItem.getEnchantmentLevel(RegisterEnchantments.PESTILENCES_ODIUM.get()) > 0) {
                shouldPestilence = true;
            }
            if (mainHandItem.getEnchantmentLevel(RegisterEnchantments.ECOLOGICAL.get()) > 0) {
                stacks.add(mainHandItem);
                shouldEco = true;
            }
            if (offhandItem.getEnchantmentLevel(RegisterEnchantments.ECOLOGICAL.get()) > 0) {
                stacks.add(offhandItem);
                shouldEco = true;
            }
            for (ItemStack stack : player.getArmorSlots()) {
                if (stack.getEnchantmentLevel(RegisterEnchantments.PESTILENCES_ODIUM.get()) > 0) {
                    shouldPestilence = true;
                    pestilenceRadius = Math.max(20 * stack.getEnchantmentLevel(RegisterEnchantments.PESTILENCES_ODIUM.get()), pestilenceRadius);
                }
                if (stack.getEnchantmentLevel(RegisterEnchantments.ECOLOGICAL.get()) > 0) {
                    stacks.add(stack);
                    shouldEco = true;
                }
            }
            if (shouldPestilence) {
                List<LivingEntity> victims = player.level()
                        .getEntitiesOfClass(LivingEntity.class, new AABB(player.getX() + pestilenceRadius, player.getY() + pestilenceRadius, player.getZ() + pestilenceRadius, player.getX() - pestilenceRadius, player.getY() - pestilenceRadius, player.getZ() - pestilenceRadius));
                for (LivingEntity victim : victims) {
                    if (!(victim instanceof Monster) && !(victim instanceof Player)) {
                        victim.hurt(player.level().damageSources().fellOutOfWorld(), 5);
                    }
                }
            }
            if (shouldEco) {
                ecological(player, stacks);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        Entity entity = event.getSource().getEntity();
        if (entity instanceof Player player) {
            ItemStack stack = player.getMainHandItem();
            ItemStack offHandStack = player.getOffhandItem();
            int level = stack.getEnchantmentLevel(RegisterEnchantments.FOCUS_IMPACT.get());
            if (level > 0) {
                double attackSpeed = player.getAttributeValue(Attributes.ATTACK_SPEED);
                event.setAmount((float) (event.getAmount() * (1 + Math.log10(Math.pow(1.2 / (attackSpeed * 0.5), 2) * Math.log(level + 6)))));
            }
            level = stack.getEnchantmentLevel(RegisterEnchantments.SPARTAN_WEAPON.get());
            if (level > 0) {
                if (offHandStack.getItem() instanceof ShieldItem) {
                    event.setAmount(event.getAmount() * (1 + 2 * level));
                }
            }
        }
    }

    public static void ecological(Player player, List<ItemStack> stacks) {
        int playerX = (int) (player.getX() + 0.5);
        int playerY = (int) (player.getY() + 0.5);
        int playerZ = (int) (player.getZ() + 0.5);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    BlockPos pos = new BlockPos(playerX + i - 2, playerY + j - 2, playerZ + k - 2);
                    BlockState state = player.level().getBlockState(pos);
                    if (state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS)) {
                        if (!player.level().isClientSide) {
                            for (ItemStack stack : stacks) {
                                stack.hurt(-stack.getEnchantmentLevel(RegisterEnchantments.ECOLOGICAL.get()), player.level().random, (ServerPlayer) player);
                            }
                        }
                    }
                }
            }
        }
    }
}