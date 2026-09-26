package net.nu11une.wardenlootforge.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nu11une.wardenlootforge.WardenLootForge;
import net.nu11une.wardenlootforge.common.ModAxeItem;
import net.nu11une.wardenlootforge.common.ModArmorItem;
import net.nu11une.wardenlootforge.common.ModArmorMaterials;
import net.nu11une.wardenlootforge.common.ModPickaxeItem;
import net.nu11une.wardenlootforge.common.ModSwordItem;
import net.nu11une.wardenlootforge.common.ModToolMaterials;
import net.nu11une.wardenlootforge.common.WardenBloodItem;
import net.nu11une.wardenlootforge.common.WardenEarsItem;
import net.nu11une.wardenlootforge.config.WardenLootConfig;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WardenLootForge.MOD_ID);
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WardenLootForge.MOD_ID);

    public static final DeferredItem<Item> SCULK_SOUL = ITEMS.registerSimpleItem("sculk_soul");
    public static final DeferredItem<Item> SCULK_INGOT = ITEMS.registerSimpleItem("sculk_ingot", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> WARDEN_HEART = ITEMS.registerSimpleItem("warden_heart",
            new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final DeferredItem<WardenBloodItem> WARDEN_BLOOD = ITEMS.register("warden_blood",
            () -> new WardenBloodItem(new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationModifier(0.0F)
                    .alwaysEdible()
                    .effect(new MobEffectInstance(MobEffects.BLINDNESS, 1200), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 3), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.ECHOLOCATE, 1200, 0, false, false), 1.0F)
                    .build())));

    public static final DeferredItem<ModArmorItem> SCULK_HELMET = ITEMS.registerItem("sculk_helmet",
            p -> new ModArmorItem(ModArmorMaterials.normal(), ArmorItem.Type.HELMET, p),
            new Item.Properties().fireResistant());
    public static final DeferredItem<ModArmorItem> SCULK_CHESTPLATE = ITEMS.registerItem("sculk_chestplate",
            p -> new ModArmorItem(ModArmorMaterials.normal(), ArmorItem.Type.CHESTPLATE, p),
            new Item.Properties().fireResistant());
    public static final DeferredItem<ModArmorItem> SCULK_CHESTPLATE_UNCHARGED = ITEMS.registerItem("sculk_chestplate_uncharged",
            p -> new ModArmorItem(ModArmorMaterials.uncharged(), ArmorItem.Type.CHESTPLATE, p),
            new Item.Properties().fireResistant());
    public static final DeferredItem<ModArmorItem> SCULK_LEGGINGS = ITEMS.registerItem("sculk_leggings",
            p -> new ModArmorItem(ModArmorMaterials.leggings(), ArmorItem.Type.LEGGINGS, p),
            new Item.Properties().fireResistant());
    public static final DeferredItem<ModArmorItem> SCULK_BOOTS = ITEMS.registerItem("sculk_boots",
            p -> new ModArmorItem(ModArmorMaterials.normal(), ArmorItem.Type.BOOTS, p),
            new Item.Properties().fireResistant());

    public static final DeferredItem<ModSwordItem> SCULK_SWORD = ITEMS.registerItem("sculk_sword",
            p -> new ModSwordItem(ModToolMaterials.SCULKERITE, p.attributes(
                    SwordItem.createAttributes(ModToolMaterials.SCULKERITE, 3, -2.4F))),
            new Item.Properties().fireResistant());
    public static final DeferredItem<ModPickaxeItem> SCULK_PICKAXE = ITEMS.registerItem("sculk_pickaxe",
            p -> new ModPickaxeItem(ModToolMaterials.SCULKERITE, p.attributes(
                    DiggerItem.createAttributes(ModToolMaterials.SCULKERITE, 0.0F, -3.0F))),
            new Item.Properties().fireResistant());
    public static final DeferredItem<ModAxeItem> SCULK_AXE = ITEMS.registerItem("sculk_axe",
            p -> new ModAxeItem(ModToolMaterials.SCULKERITE, p.attributes(
                    DiggerItem.createAttributes(ModToolMaterials.SCULKERITE, 5.0F, -3.0F))),
            new Item.Properties().fireResistant());
    public static final DeferredItem<HoeItem> SCULK_HOE = ITEMS.registerItem("sculk_hoe",
            p -> new HoeItem(ModToolMaterials.SCULKERITE,
                    p.attributes(DiggerItem.createAttributes(ModToolMaterials.SCULKERITE, -4.0F, 0.0F))),
            new Item.Properties().fireResistant());
    public static final DeferredItem<ShovelItem> SCULK_SHOVEL = ITEMS.registerItem("sculk_shovel",
            p -> new ShovelItem(ModToolMaterials.SCULKERITE,
                    p.attributes(DiggerItem.createAttributes(ModToolMaterials.SCULKERITE, 1.0F, -3.0F))),
            new Item.Properties().fireResistant());

    public static final DeferredItem<WardenEarsItem> WARDEN_EARS_TRINKET = ITEMS.registerItem("warden_ears_trinket",
            WardenEarsItem::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WARDEN_LOOT_TAB = TABS.register(
            "wardenlootforge_group", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.wardenlootforge.wardenlootforge_group"))
                    .icon(() -> new ItemStack(SCULK_CHESTPLATE.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SCULK_SOUL.get());
                        output.accept(SCULK_INGOT.get());
                        output.accept(WARDEN_HEART.get());
                        output.accept(WARDEN_BLOOD.get());
                        if (WardenLootConfig.REGISTER_TOOLS.get()) {
                            output.accept(SCULK_SWORD.get());
                            output.accept(SCULK_PICKAXE.get());
                            output.accept(SCULK_AXE.get());
                            output.accept(SCULK_HOE.get());
                            output.accept(SCULK_SHOVEL.get());
                        }
                        if (WardenLootConfig.REGISTER_CHESTPLATE.get()) {
                            output.accept(SCULK_CHESTPLATE.get());
                        }
                        if (WardenLootConfig.REGISTER_OTHER_ARMOR.get()) {
                            output.accept(SCULK_HELMET.get());
                            output.accept(SCULK_LEGGINGS.get());
                            output.accept(SCULK_BOOTS.get());
                        }
                        output.accept(WARDEN_EARS_TRINKET.get());
                    })
                    .build());

    private ModItems() {}

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        TABS.register(modEventBus);
    }
}
