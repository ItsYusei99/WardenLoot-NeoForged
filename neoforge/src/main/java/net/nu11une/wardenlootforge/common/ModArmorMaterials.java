package net.nu11une.wardenlootforge.common;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.nu11une.wardenlootforge.WardenLootForge;
import net.nu11une.wardenlootforge.config.WardenLootConfig;
import net.nu11une.wardenlootforge.register.ModItems;

import java.util.List;
import java.util.Map;

public final class ModArmorMaterials {
    private static final ArmorMaterial SCULKERITE = create("sculkerite", false);
    private static final ArmorMaterial SCULKERITE_COMPAT = create("sculkerite_compat", false);
    private static final ArmorMaterial SCULKERITE_UNCHARGED = create("sculkerite_uncharged", true);

    private ModArmorMaterials() {}

    private static ArmorMaterial create(String textureName, boolean uncharged) {
        int protection = WardenLootConfig.getValueOrDefault(WardenLootConfig.ARMOR_PROTECTION);
        float toughness = WardenLootConfig.getValueOrDefault(WardenLootConfig.ARMOR_TOUGHNESS).floatValue();
        Map<ArmorItem.Type, Integer> defense = uncharged
                ? Map.of(ArmorItem.Type.HELMET, 8,
                        ArmorItem.Type.CHESTPLATE, protection,
                        ArmorItem.Type.LEGGINGS, 8,
                        ArmorItem.Type.BOOTS, 8)
                : Map.of(ArmorItem.Type.HELMET, protection,
                        ArmorItem.Type.CHESTPLATE, protection + 6,
                        ArmorItem.Type.LEGGINGS, protection + 3,
                        ArmorItem.Type.BOOTS, protection);
        ArmorMaterial material = new ArmorMaterial(
                defense,
                15,
                SoundEvents.ARMOR_EQUIP_NETHERITE,
                () -> Ingredient.of(ModItems.SCULK_INGOT.get()),
                List.of(new ArmorMaterial.Layer(
                        ResourceLocation.fromNamespaceAndPath(WardenLootForge.MOD_ID, textureName))),
                uncharged ? toughness / 2.0F : toughness,
                uncharged ? 0.05F : 0.15F);
        return material;
    }

    public static Holder<ArmorMaterial> normal() {
        return Holder.direct(WardenLootConfig.getValueOrDefault(WardenLootConfig.ANIMATE_ARMOR)
                ? SCULKERITE : SCULKERITE_COMPAT);
    }

    public static Holder<ArmorMaterial> leggings() {
        return Holder.direct(SCULKERITE);
    }

    public static Holder<ArmorMaterial> uncharged() {
        return Holder.direct(SCULKERITE_UNCHARGED);
    }
}
