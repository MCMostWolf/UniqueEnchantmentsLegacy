package com.wolf.ue.enchantments.init;

import com.wolf.ue.enchantments.DeathAversionEnchantment;
import com.wolf.ue.enchantments.EcologicalEnchantment;
import com.wolf.ue.enchantments.FocusImpactEnchantment;
import com.wolf.ue.enchantments.SpartanWeaponEnchantment;
import com.wolf.ue.enchantments.curse.ComboStarEnchantment;
import com.wolf.ue.enchantments.curse.EnderTeleportEnchantment;
import com.wolf.ue.enchantments.curse.PestilencesOdiumEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.wolf.ue.UniqueEnchantments.MODID;

public class RegisterEnchantments {
    static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final RegistryObject<Enchantment> ENDER_TELEPORT = REGISTRY.register("ender_teleport", EnderTeleportEnchantment::new);
    public static final RegistryObject<Enchantment> DEATH_AVERSION = REGISTRY.register("death_aversion", DeathAversionEnchantment::new);
    public static final RegistryObject<Enchantment> COMBO_STAR = REGISTRY.register("combo_star", ComboStarEnchantment::new);
    public static final RegistryObject<Enchantment> PESTILENCES_ODIUM = REGISTRY.register("pestilences_odium", PestilencesOdiumEnchantment::new);
    public static final RegistryObject<Enchantment> ECOLOGICAL = REGISTRY.register("ecological", EcologicalEnchantment::new);
    public static final RegistryObject<Enchantment> FOCUS_IMPACT = REGISTRY.register("focus_impact", FocusImpactEnchantment::new);
    public static final RegistryObject<Enchantment> SPARTAN_WEAPON = REGISTRY.register("spartan_weapon", SpartanWeaponEnchantment::new);

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}