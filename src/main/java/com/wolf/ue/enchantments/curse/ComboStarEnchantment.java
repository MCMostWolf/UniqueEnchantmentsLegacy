package com.wolf.ue.enchantments.curse;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ComboStarEnchantment extends BasicEnchantment {
    public ComboStarEnchantment(EquipmentSlot... slots) {
        super(EnchantmentCategory.WEAPON, slots, Rarity.RARE, true, false, true, true, 1);
    }
}