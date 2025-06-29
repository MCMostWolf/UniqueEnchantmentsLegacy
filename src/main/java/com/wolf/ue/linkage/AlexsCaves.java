package com.wolf.ue.linkage;

import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.github.alexmodguy.alexscaves.server.item.CaveMapItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.levelgen.Heightmap;

public class AlexsCaves {
    public static Ingredient mapAndAcMapIngredient() {
        return Ingredient.of(new ItemStack(ACItemRegistry.CAVE_MAP.get()),new ItemStack(Items.FILLED_MAP));
    }
    public static void enderTeleportAC(ItemStack stack, Player player) {
        if (stack.getItem() instanceof CaveMapItem) {
            BlockPos position;
            CompoundTag tag = stack.getTag();
            if (tag != null) {
                if (tag.contains("BiomeZ") && tag.contains("BiomeX") && tag.contains("BiomeY")) {
                    position = new BlockPos(tag.getInt("BiomeX"), tag.getInt("BiomeY"), tag.getInt("BiomeZ"));
                    player.teleportTo(position.getX(), 255, position.getZ());
                    int y = player.level().getHeight(Heightmap.Types.MOTION_BLOCKING, position.getX(), position.getZ());
                    player.teleportTo(position.getX(), y, position.getZ());
                }
            }
        }
    }
}
