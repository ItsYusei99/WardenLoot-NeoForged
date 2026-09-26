package net.nu11une.wardenlootforge.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.nu11une.wardenlootforge.WardenLootForge;

public final class ModToolTips {
    public static final MutableComponent WARDEN_SET_BONUS = text("armor.warden_fear_set", ChatFormatting.DARK_AQUA);
    public static final MutableComponent WARDEN_BONUS = text("armor.warden_fear", ChatFormatting.DARK_AQUA);
    public static final MutableComponent COLD_HEART = text("armor.cold_heart", ChatFormatting.DARK_PURPLE);
    public static final MutableComponent DARKNESS_IMMUNITY_BONUS = text("armor.darkness_immunity", ChatFormatting.DARK_GRAY);
    public static final MutableComponent INFLICT_DARKNESS = text("weapon.inflict_darkness", ChatFormatting.DARK_GRAY);
    public static final MutableComponent DEEPSLATE_MINER = text("tool.deepslate_miner", ChatFormatting.DARK_GRAY);
    public static final MutableComponent TRINKET_ECHOLOCATE = text("trinket.warden_ears", ChatFormatting.DARK_AQUA);

    private ModToolTips() {}

    private static MutableComponent text(String suffix, ChatFormatting color) {
        return Component.translatable("tooltip." + WardenLootForge.MOD_ID + "." + suffix).withStyle(color);
    }
}
