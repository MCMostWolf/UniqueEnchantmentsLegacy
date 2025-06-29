package com.wolf.ue;

import com.wolf.ue.enchantments.init.RegisterEnchantments;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.wolf.ue.UniqueEnchantments.MODID;

@Mod(MODID)
public class UniqueEnchantments {
    public static final String MODID = "unique_enchantments_legacy";

    public UniqueEnchantments() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RegisterEnchantments.register(eventBus);
    }
}