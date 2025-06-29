package com.wolf.ue.enchantments;

import com.mcmostwolf.enchantmentlib.api.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ComboStar extends BasicEnchantment {
    public ComboStar(EquipmentSlot... slots) {
        super(EnchantmentCategory.WEAPON, slots, Rarity.RARE, true, false, true, true, 1);
    }
}
