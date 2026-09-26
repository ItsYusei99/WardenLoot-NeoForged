package net.nu11une.wardenlootforge.common;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.nu11une.wardenlootforge.config.WardenLootConfig;
import net.nu11une.wardenlootforge.register.ModItems;

public final class ModToolMaterials {
    public static final Tier SCULKERITE = new Tier() {
        @Override
        public int getUses() {
            return WardenLootConfig.getValueOrDefault(WardenLootConfig.TOOL_DURABILITY);
        }

        @Override
        public float getSpeed() {
            return WardenLootConfig.getValueOrDefault(WardenLootConfig.TOOL_SPEED).floatValue();
        }

        @Override
        public float getAttackDamageBonus() {
            return WardenLootConfig.getValueOrDefault(WardenLootConfig.TOOL_BASE_DAMAGE).floatValue();
        }

        @Override
        public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() {
            int level = WardenLootConfig.getValueOrDefault(WardenLootConfig.TOOL_MINING_LEVEL);
            if (level >= 5) return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
            if (level >= 4) return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
            if (level >= 3) return BlockTags.INCORRECT_FOR_IRON_TOOL;
            if (level >= 2) return BlockTags.INCORRECT_FOR_STONE_TOOL;
            return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.SCULK_INGOT.get());
        }
    };

    private ModToolMaterials() {}
}
