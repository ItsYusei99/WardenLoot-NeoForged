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

public final class SculkBlockLootModifier extends LootModifier {
    public static final MapCodec<SculkBlockLootModifier> CODEC = RecordCodecBuilder.mapCodec(
            instance -> codecStart(instance).apply(instance, SculkBlockLootModifier::new));

    public SculkBlockLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (WardenLootConfig.SCULK_DROPS_SOUL.get()
                && context.getRandom().nextFloat() < WardenLootConfig.SCULK_SOUL_CHANCE.get()) {
            generatedLoot.add(new ItemStack(ModItems.SCULK_SOUL.get()));
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier> codec() {
        return CODEC;
    }
}
