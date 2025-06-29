package com.wolf.ue.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.Objects;
import java.util.UUID;

public class AttributeManager {
    public static final String DEATH_AVERSION_MODIFIER_NAME = "death_aversion_modifier";
    public static final String COMBO_STAR_MODIFIER_NAME = "combo_star_modifier";
    public static final UUID DEATH_AVERSION_MODIFIER_UUID = UUID.fromString("A17200EE-C817-662A-FBEE-917A5990E14E");
    public static final UUID COMBO_STAR_MODIFIER_UUID = UUID.fromString("A078DC5F-B6FE-232A-C46B-91174CC232C6");
    public static final AttributeModifier DEATH_AVERSION_MODIFIER = new AttributeModifier(
            DEATH_AVERSION_MODIFIER_UUID,
            DEATH_AVERSION_MODIFIER_NAME,
            -2,
            AttributeModifier.Operation.ADDITION);

    public static final AttributeModifier COMBO_STAR_MODIFIER = new AttributeModifier(
            COMBO_STAR_MODIFIER_UUID,
            COMBO_STAR_MODIFIER_NAME,
            0.1,
            AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static void addModifierSafety(LivingEntity livingEntity, Attribute attribute, AttributeModifier attributeModifier) {
        AttributeInstance attributeInstance = livingEntity.getAttribute(attribute);
        if (attributeInstance != null && !attributeInstance.hasModifier(attributeModifier)) {
            Objects.requireNonNull(livingEntity.getAttribute(attribute)).addPermanentModifier(attributeModifier);
        }
    }
    public static void removeModifierSafety(LivingEntity livingEntity, Attribute attribute, UUID attributeModifierId) {
        AttributeInstance attributeInstance = livingEntity.getAttribute(attribute);
        if (attributeInstance != null && attributeInstance.getModifier(attributeModifierId) != null) {
            attributeInstance.removeModifier(attributeModifierId);
        }
    }

    public static void changeModifierSafety(LivingEntity livingEntity, Attribute attribute, AttributeModifier attributeModifier) {
        AttributeInstance attributeInstance = livingEntity.getAttribute(attribute);
        if (attributeInstance != null && attributeInstance.hasModifier(attributeModifier)) {
            attributeInstance.removeModifier(attributeModifier.getId());
            attributeInstance.addPermanentModifier(attributeModifier);
        }
    }

    public static AttributeModifier getModifierSafety(LivingEntity livingEntity, Attribute attribute, UUID modifierId) {
        AttributeInstance attributeInstance = livingEntity.getAttribute(attribute);
        if (attributeInstance != null && attributeInstance.getModifier(modifierId) != null) {
            return attributeInstance.getModifier(modifierId);
        }
        else {
            return null;
        }
    }

    public static AttributeModifier newComboStarModifier(double amount) {
        return new AttributeModifier(
                COMBO_STAR_MODIFIER_UUID,
                COMBO_STAR_MODIFIER_NAME,
                amount,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
