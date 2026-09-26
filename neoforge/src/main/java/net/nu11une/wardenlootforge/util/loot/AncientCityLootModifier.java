package net.nu11une.wardenlootforge.util.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.nu11une.wardenlootforge.WardenLootForge;
import net.nu11une.wardenlootforge.config.WardenLootConfig;
import net.nu11une.wardenlootforge.register.ModItems;

public final class AncientCityLootModifier extends LootModifier {
    public static final MapCodec<AncientCityLootModifier> CODEC = RecordCodecBuilder.mapCodec(
            instance -> codecStart(instance).apply(instance, AncientCityLootModifier::new));

    public AncientCityLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!WardenLootConfig.ANCIENT_CITY_LOOT.get()) {
            return generatedLoot;
        }
        for (int i = 0; i < 4; i++) {
            if (context.getRandom().nextFloat() < 0.08F) {
                generatedLoot.add(new ItemStack(ModItems.SCULK_SOUL.get()));
            }
        }
        if (context.getRandom().nextFloat() < 0.06F) {
            generatedLoot.add(new ItemStack(ModItems.SCULK_INGOT.get()));
        }
        if (WardenLootConfig.ENABLE_WARDEN_BANE.get()) {
            for (int i = 0; i < 3; i++) {
                if (context.getRandom().nextFloat() < 0.03F) {
                    int level = Math.round(context.getRandom().nextFloat() * 4.0F + 1.0F);
                    generatedLoot.add(createWardenBaneBook((ServerLevel) context.getLevel(), level));
                }
            }
        }
        return generatedLoot;
    }

    private static ItemStack createWardenBaneBook(ServerLevel level, int enchantmentLevel) {
        ResourceKey<Enchantment> key = ResourceKey.create(Registries.ENCHANTMENT,
                WardenLootForge.WARDEN_DAMAGE_KEY.location());
        Holder<Enchantment> enchantment = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(key);
        return EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, enchantmentLevel));
    }

    @Override
    public MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier> codec() {
        return CODEC;
    }
}
