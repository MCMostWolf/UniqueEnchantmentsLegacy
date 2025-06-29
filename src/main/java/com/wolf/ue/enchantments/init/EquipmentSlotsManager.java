package com.wolf.ue.enchantments.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public final class EquipmentSlotsManager {
    public static final EquipmentSlot[] NONE = new EquipmentSlot[] {};
    public static final EquipmentSlot[] ARMORS = new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET };
    public static final EquipmentSlot[] HANDS = new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND };
    public static final EquipmentSlot[] MAIN_HAND = new EquipmentSlot[] { EquipmentSlot.MAINHAND };
    public static final EquipmentSlot[] OFF_HAND = new EquipmentSlot[] { EquipmentSlot.OFFHAND };
    public static final EquipmentSlot[] HEAD = new EquipmentSlot[] { EquipmentSlot.HEAD };
    public static final EquipmentSlot[] CHEST = new EquipmentSlot[] { EquipmentSlot.CHEST };
    public static final EquipmentSlot[] LEGS = new EquipmentSlot[] { EquipmentSlot.LEGS };
    public static final EquipmentSlot[] FEET = new EquipmentSlot[] { EquipmentSlot.FEET };

    public static final EnchantmentCategory ANY = EnchantmentCategory.create("any", item -> true);

    private EquipmentSlotsManager() { }
}