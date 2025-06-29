package com.wolf.ue.enchantments;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class EcologicalEnchantment extends BasicEnchantment {
    public EcologicalEnchantment(EquipmentSlot... slots) {
        super(EnchantmentCategory.ARMOR, slots, Rarity.RARE, true, false, true, false, 3);
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return stack.isDamageableItem();
    }
}