package com.wolf.ue.enchantments.curse;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PestilencesOdiumEnchantment extends BasicEnchantment {
    public PestilencesOdiumEnchantment(EquipmentSlot... slots) {
        super(EnchantmentCategory.ARMOR, slots, Rarity.RARE, true, false, true, true, 2);
    }
}