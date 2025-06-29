package com.wolf.ue.enchantments.curse;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import com.wolf.ue.linkage.AlexsCavesLinkage;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class EnderTeleportEnchantment extends BasicEnchantment {
    public EnderTeleportEnchantment(EquipmentSlot... slots) {
        super(EnchantmentCategory.ARMOR, slots, Rarity.RARE, true, true, true, false, 1);
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        Ingredient ingredient = Ingredient.of(new ItemStack(Items.FILLED_MAP));
        if (ModList.get().isLoaded("alexscaves")) {
            ingredient = AlexsCavesLinkage.mapAndAcMapIngredient();
        }
        return ingredient.test(stack);
    }
}