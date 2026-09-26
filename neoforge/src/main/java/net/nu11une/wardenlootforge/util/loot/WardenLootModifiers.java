package net.nu11une.wardenlootforge.util.loot;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.nu11une.wardenlootforge.WardenLootForge;

public final class WardenLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    WardenLootForge.MOD_ID);

    static {
        SERIALIZERS.register("ancient_city_loot_modifier", () -> AncientCityLootModifier.CODEC);
        SERIALIZERS.register("sculk_block_loot_modifier", () -> SculkBlockLootModifier.CODEC);
        SERIALIZERS.register("warden_loot_modifier", () -> WardenLootModifier.CODEC);
    }

    private WardenLootModifiers() {}

    public static void register(IEventBus modEventBus) {
        SERIALIZERS.register(modEventBus);
    }
}
