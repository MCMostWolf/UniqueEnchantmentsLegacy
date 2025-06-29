package com.wolf.ue.enchantments;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FocusImpact extends BasicEnchantment {
    public FocusImpact(EquipmentSlot... slots) {
        super(EnchantmentCategory.WEAPON, slots, Rarity.RARE, false, false, false, false, 3);
    }
}
