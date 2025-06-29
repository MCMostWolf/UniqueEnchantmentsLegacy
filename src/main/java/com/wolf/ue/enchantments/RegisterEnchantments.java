package com.wolf.ue.enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.wolf.ue.UniqueEnchantments.MODID;

public class RegisterEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final RegistryObject<Enchantment> ENDER_TELEPORT = REGISTRY.register("ender_teleport", EnderTeleport::new);
    public static final RegistryObject<Enchantment> DEATH_AVERSION = REGISTRY.register("death_aversion", DeathAversion::new);
    public static final RegistryObject<Enchantment> COMBO_STAR = REGISTRY.register("combo_star", ComboStar::new);
    public static final RegistryObject<Enchantment> PESTILENCES_ODIUM = REGISTRY.register("pestilences_odium", PestilencesOdium::new);
    public static final RegistryObject<Enchantment> ECOLOGICAL = REGISTRY.register("ecological", Ecological::new);
    public static final RegistryObject<Enchantment> FOCUS_IMPACT = REGISTRY.register("focus_impact", FocusImpact::new);
    public static final RegistryObject<Enchantment> SPARTAN_WEAPON = REGISTRY.register("spartan_weapon", SpartanWeapon::new);
    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
