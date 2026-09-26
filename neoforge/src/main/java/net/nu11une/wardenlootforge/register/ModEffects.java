package net.nu11une.wardenlootforge.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nu11une.wardenlootforge.WardenLootForge;
import net.nu11une.wardenlootforge.common.EcholocateEffect;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, WardenLootForge.MOD_ID);

    public static final DeferredHolder<MobEffect, EcholocateEffect> ECHOLOCATE =
            EFFECTS.register("echolocate", EcholocateEffect::new);

    private ModEffects() {}

    public static void register(net.neoforged.bus.api.IEventBus bus) {
        EFFECTS.register(bus);
    }
}
