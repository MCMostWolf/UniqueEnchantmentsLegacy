package com.wolf.ue.enchantments;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FocusImpactEnchantment extends BasicEnchantment {
    public FocusImpactEnchantment(EquipmentSlot... slots) {
        super(EnchantmentCategory.WEAPON, slots, Rarity.RARE, false, false, false, false, 3);
    }
}