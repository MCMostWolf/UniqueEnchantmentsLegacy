package com.wolf.ue.util;


import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.Nullable;

@UtilityClass // 请不要 import static
public class EnchantmentUtil {
    @Getter
    private static @Nullable ItemStack enchantmentArmor; // EnchantmentUtil#hasArmorEnchantment(Iterable<ItemStack>,Enchantment) 检查后再使用
    @Getter
    private static @Nullable ItemStack maxEnchantmentArmor;

    /**
     * 任意一件护甲存在某一附魔返回true
     */
    public boolean hasArmorEnchantment(Iterable<ItemStack> armorSlots, Enchantment enchantment) {
        for (var armorSlot : armorSlots) {
            if (armorSlot.getEnchantmentLevel(enchantment) > 0) {
                enchantmentArmor = armorSlot;
                return true;
            }
        }

        return false;
    }

    /**
     * 找到护甲中某附魔最大等级
     *
     * @param armorSlots
     * @param enchantment
     */
    public int findArmorHasMaxLevel(Iterable<ItemStack> armorSlots, Enchantment enchantment) {
        var maxLevel = 0;
        for (var armorSlot : armorSlots) {
            if (armorSlot.getAllEnchantments().containsKey(enchantment)) {
                continue;
            }
            if (maxLevel < armorSlot.getEnchantmentLevel(enchantment)) {
                maxLevel = armorSlot.getEnchantmentLevel(enchantment);
                maxEnchantmentArmor = armorSlot;
            }
        }
        return maxLevel;
    }
}