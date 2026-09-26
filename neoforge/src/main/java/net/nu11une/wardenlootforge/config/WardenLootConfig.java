package net.nu11une.wardenlootforge.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class WardenLootConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue REGISTER_TOOLS;
    public static final ModConfigSpec.BooleanValue REGISTER_CHESTPLATE;
    public static final ModConfigSpec.BooleanValue REGISTER_OTHER_ARMOR;
    public static final ModConfigSpec.BooleanValue ENABLE_WARDEN_BANE;

    public static final ModConfigSpec.IntValue TOOL_MINING_LEVEL;
    public static final ModConfigSpec.IntValue TOOL_DURABILITY;
    public static final ModConfigSpec.DoubleValue TOOL_SPEED;
    public static final ModConfigSpec.DoubleValue TOOL_BASE_DAMAGE;
    public static final ModConfigSpec.IntValue ARMOR_PROTECTION;
    public static final ModConfigSpec.DoubleValue ARMOR_TOUGHNESS;
    public static final ModConfigSpec.DoubleValue WARDEN_BANE_MULTIPLIER;

    public static final ModConfigSpec.BooleanValue SCULK_DROPS_SOUL;
    public static final ModConfigSpec.DoubleValue SCULK_SOUL_CHANCE;
    public static final ModConfigSpec.BooleanValue ANCIENT_CITY_LOOT;
    public static final ModConfigSpec.BooleanValue WARDEN_LOOT;
    public static final ModConfigSpec.BooleanValue WARDEN_KILL_SOUL;

    public static final ModConfigSpec.BooleanValue TRINKET_COSMETIC_ONLY;
    public static final ModConfigSpec.DoubleValue TRINKET_RANGE_MULTIPLIER;
    public static final ModConfigSpec.BooleanValue ANIMATE_ARMOR;

    public static <T> T getValueOrDefault(ModConfigSpec.ConfigValue<T> value) {
        try {
            return value.get();
        } catch (IllegalStateException configNotLoadedYet) {
            return value.getDefault();
        }
    }

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("registry");
        REGISTER_TOOLS = builder.comment("Show/register the sculk tools in Warden Loot's creative tab.")
                .define("registerTools", true);
        REGISTER_CHESTPLATE = builder.comment("Enable the Warden heartplate and its survival effect.")
                .define("registerChestplate", true);
        REGISTER_OTHER_ARMOR = builder.comment("Enable the helmet, leggings, and boots.")
                .define("registerHelmetLeggingsBoots", true);
        ENABLE_WARDEN_BANE = builder.comment("Enable the Bane of the Depth-Keeper enchantment and related loot.")
                .define("registerWardenBaneEnchantment", true);
        builder.pop();

        builder.push("stats");
        TOOL_MINING_LEVEL = builder.defineInRange("toolMiningLevel", 4, 1, 10);
        TOOL_DURABILITY = builder.defineInRange("toolDurability", 3070, 1, 32767);
        TOOL_SPEED = builder.defineInRange("toolSpeed", 12.0, 1.0, 64.0);
        TOOL_BASE_DAMAGE = builder.defineInRange("toolBaseDamage", 8.0, 0.0, 64.0);
        ARMOR_PROTECTION = builder.defineInRange("armorBaseProtection", 8, 0, 30);
        ARMOR_TOUGHNESS = builder.defineInRange("armorToughness", 4.0, 0.0, 20.0);
        WARDEN_BANE_MULTIPLIER = builder.defineInRange("wardenBaneEnchantmentMultiplier", 1.0, 0.0, 100.0);
        builder.pop();

        builder.push("lootTables");
        SCULK_DROPS_SOUL = builder.define("sculkDropsSoul", true);
        SCULK_SOUL_CHANCE = builder.comment("0.006 means a 0.6% chance.")
                .defineInRange("soulChanceFromSculk", 0.006, 0.0, 1.0);
        ANCIENT_CITY_LOOT = builder.define("ancientCityHasModLoot", true);
        WARDEN_LOOT = builder.define("wardenDropsModLoot", true);
        WARDEN_KILL_SOUL = builder.define("wardenKillsDropSoul", true);
        builder.pop();

        builder.push("misc");
        TRINKET_COSMETIC_ONLY = builder.define("trinketCosmeticOnly", false);
        TRINKET_RANGE_MULTIPLIER = builder.defineInRange("trinketRangeMultiplier", 1.0, 0.0, 16.0);
        ANIMATE_ARMOR = builder.comment("Use the animated armor texture. The port includes a vanilla animation file.")
                .define("animateArmor", true);
        builder.pop();

        SPEC = builder.build();
    }

    private WardenLootConfig() {}
}
