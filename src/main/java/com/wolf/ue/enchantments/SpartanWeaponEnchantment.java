package com.wolf.ue.enchantments;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SpartanWeaponEnchantment extends BasicEnchantment {
    public SpartanWeaponEnchantment(EquipmentSlot... slots) {
        super(EnchantmentCategory.WEAPON, slots, Rarity.UNCOMMON, true, false, false, false, 5);
    }
}