package net.nu11une.wardenlootforge.util.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.nu11une.wardenlootforge.config.WardenLootConfig;
import net.nu11une.wardenlootforge.register.ModItems;

public final class WardenLootModifier extends LootModifier {
    public static final MapCodec<WardenLootModifier> CODEC = RecordCodecBuilder.mapCodec(
            instance -> codecStart(instance).apply(instance, WardenLootModifier::new));

    public WardenLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!WardenLootConfig.WARDEN_LOOT.get()) {
            return generatedLoot;
        }
        for (int i = 0; i < 12; i++) {
            if (context.getRandom().nextFloat() > 0.5F) {
                generatedLoot.add(new ItemStack(ModItems.SCULK_SOUL.get()));
            }
        }
        generatedLoot.add(new ItemStack(ModItems.SCULK_SOUL.get(), 4));
        if (WardenLootConfig.REGISTER_CHESTPLATE.get() || WardenLootConfig.REGISTER_OTHER_ARMOR.get()) {
            generatedLoot.add(new ItemStack(ModItems.WARDEN_HEART.get()));
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier> codec() {
        return CODEC;
    }
}
