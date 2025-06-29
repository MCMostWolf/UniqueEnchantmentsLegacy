package com.wolf.ue.enchantments;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import com.wolf.ue.enchantments.init.EquipmentSlotsManager;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class VitaeEnchantment extends BasicEnchantment {
    public VitaeEnchantment() {
        super(EnchantmentCategory.ARMOR, EquipmentSlotsManager.ARMORS, Rarity.VERY_RARE, true, false, false, false, 5);
    }
}