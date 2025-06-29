package com.wolf.ue.enchantments;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class DeathAversion extends BasicEnchantment {
    public DeathAversion(EquipmentSlot... slots) {
        super(EnchantmentCategory.ARMOR, slots, Rarity.UNCOMMON, true, false, true, true, 1);
    }
    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return true;
    }
}
