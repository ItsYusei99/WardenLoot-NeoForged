package net.nu11une.wardenlootforge.common;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nu11une.wardenlootforge.util.ModToolTips;

import java.util.List;

public final class ModPickaxeItem extends PickaxeItem {
    public ModPickaxeItem(Tier tier, Item.Properties properties) {
        super(tier, properties);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().contains("deepslate")) {
            return super.getDestroySpeed(stack, state) * 4.0F;
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModToolTips.DEEPSLATE_MINER);
    }
}
