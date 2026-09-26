package net.nu11une.wardenlootforge;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.nu11une.wardenlootforge.config.WardenLootConfig;
import net.nu11une.wardenlootforge.event.WardenLootEvents;
import net.nu11une.wardenlootforge.register.ModEffects;
import net.nu11une.wardenlootforge.register.ModItems;
import net.nu11une.wardenlootforge.util.loot.WardenLootModifiers;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(WardenLootForge.MOD_ID)
public final class WardenLootForge {
    public static final String MOD_ID = "wardenlootforge";
    public static final ResourceKey<Enchantment> WARDEN_DAMAGE_KEY = ResourceKey.create(
            Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MOD_ID, "warden_damage"));
    public static final Logger LOGGER = LogUtils.getLogger();

    public WardenLootForge(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, WardenLootConfig.SPEC);

        ModEffects.register(modEventBus);
        ModItems.register(modEventBus);
        WardenLootModifiers.register(modEventBus);

        modBusListener(modEventBus);
        WardenLootEvents.register(NeoForge.EVENT_BUS);
        LOGGER.info("[{}] NeoForge 1.21.1 port initialized", MOD_ID);
    }

    private void modBusListener(IEventBus modEventBus) {
        modEventBus.addListener(this::enqueueIMC);
    }

    private void enqueueIMC(InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("curios")) {
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                    () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        }
    }
}
