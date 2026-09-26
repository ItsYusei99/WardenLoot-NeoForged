package net.nu11une.wardenlootforge.common;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.nu11une.wardenlootforge.register.ModItems;
import net.nu11une.wardenlootforge.util.ModToolTips;

import java.util.List;

public final class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial material, Type type, Item.Properties properties) {
        super(net.minecraft.core.Holder.direct(material), type, properties);
    }

    public ModArmorItem(net.minecraft.core.Holder<ArmorMaterial> material, Type type, Item.Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (stack.is(ModItems.SCULK_HELMET.get()) || stack.is(ModItems.SCULK_LEGGINGS.get())
                || stack.is(ModItems.SCULK_BOOTS.get())) {
            tooltip.add(ModToolTips.WARDEN_SET_BONUS);
            if (stack.is(ModItems.SCULK_HELMET.get())) {
                tooltip.add(ModToolTips.DARKNESS_IMMUNITY_BONUS);
            }
        } else if (stack.is(ModItems.SCULK_CHESTPLATE.get())) {
            tooltip.add(ModToolTips.WARDEN_BONUS);
            tooltip.add(ModToolTips.COLD_HEART);
        } else if (stack.is(ModItems.SCULK_CHESTPLATE_UNCHARGED.get())) {
            tooltip.add(ModToolTips.WARDEN_BONUS);
        }
    }
}
