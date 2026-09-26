package net.nu11une.wardenlootforge.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.PlayLevelSoundEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.nu11une.wardenlootforge.WardenLootForge;
import net.nu11une.wardenlootforge.common.ModArmorItem;
import net.nu11une.wardenlootforge.config.WardenLootConfig;
import net.nu11une.wardenlootforge.register.ModEffects;
import net.nu11une.wardenlootforge.register.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

public final class WardenLootEvents {
    private WardenLootEvents() {}

    public static void register(IEventBus eventBus) {
        eventBus.addListener(WardenLootEvents::onPlayLevelSound);
        eventBus.addListener(WardenLootEvents::onEffectApplicable);
        eventBus.addListener(WardenLootEvents::onLivingDeath);
        eventBus.addListener(WardenLootEvents::onLivingDrops);
        eventBus.addListener(WardenLootEvents::onBlockDrops);
        eventBus.addListener(WardenLootEvents::onIncomingDamage);
        eventBus.addListener(WardenLootEvents::onWardenChangeTarget);
    }

    private static void onPlayLevelSound(PlayLevelSoundEvent.AtEntity event) {
        Level level = event.getLevel();
        if (level.isClientSide() || !(event.getEntity() instanceof LivingEntity target)) {
            return;
        }
        if (WardenLootConfig.TRINKET_COSMETIC_ONLY.get()) {
            return;
        }

        SoundEvent sound = event.getSound().value();
        float distance = sound.getRange(event.getOriginalVolume()) * 0.9F
                * WardenLootConfig.TRINKET_RANGE_MULTIPLIER.get().floatValue();
        if (distance <= 0.0F) {
            return;
        }

        for (Player player : level.players()) {
            if (!hasEcholocation(player)) {
                continue;
            }
            if (player.closerThan(target, distance, distance * 0.7F)) {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60, 0,
                        false, false, false), player);
            }
        }
    }

    private static boolean hasEcholocation(Player player) {
        if (player.hasEffect(ModEffects.ECHOLOCATE)) {
            return true;
        }
        return CuriosApi.getCuriosInventory(player)
                .map(inventory -> inventory.isEquipped(ModItems.WARDEN_EARS_TRINKET.get()))
                .orElse(false);
    }

    private static void onEffectApplicable(MobEffectEvent.Applicable event) {
        if (WardenLootConfig.REGISTER_OTHER_ARMOR.get()
                && event.getEntity() instanceof Player player
                && event.getEffectInstance().is(MobEffects.DARKNESS)
                && player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.SCULK_HELMET.get())) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }

    private static void onLivingDeath(LivingDeathEvent event) {
        if (!WardenLootConfig.REGISTER_CHESTPLATE.get()
                || !(event.getEntity() instanceof Player player)
                || event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return;
        }

        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!chestplate.is(ModItems.SCULK_CHESTPLATE.get())) {
            return;
        }

        ItemStack uncharged = chestplate.transmuteCopy(ModItems.SCULK_CHESTPLATE_UNCHARGED.get());
        player.setItemSlot(EquipmentSlot.CHEST, uncharged);
        player.setHealth(1.0F);
        player.removeAllEffects();
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
        player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 600, 0));
        player.level().broadcastEntityEvent(player, (byte) 35);
        event.setCanceled(true);

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(net.minecraft.stats.Stats.ITEM_USED.get(ModItems.SCULK_CHESTPLATE.get()));
            CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, uncharged.copy());
        }
    }

    private static void onLivingDrops(LivingDropsEvent event) {
        if (!WardenLootConfig.WARDEN_KILL_SOUL.get()) {
            return;
        }
        Entity killer = event.getSource().getEntity();
        if (!(killer instanceof Warden) || !(event.getEntity().level() instanceof ServerLevel level)) {
            return;
        }

        float chance = event.getEntity() instanceof Player ? 0.20F : 0.05F;
        if (level.getRandom().nextFloat() < chance) {
            event.getDrops().add(new ItemEntity(level, event.getEntity().getX(), event.getEntity().getY(),
                    event.getEntity().getZ(), new ItemStack(ModItems.SCULK_SOUL.get())));
        }
    }

    private static void onBlockDrops(BlockDropsEvent event) {
        if (!WardenLootConfig.SCULK_DROPS_SOUL.get()
                || !event.getState().is(Blocks.SCULK)
                || event.getLevel().isClientSide()) {
            return;
        }
        if (event.getLevel().getRandom().nextFloat() < WardenLootConfig.SCULK_SOUL_CHANCE.get()) {
            var pos = event.getPos();
            event.getDrops().add(new ItemEntity(event.getLevel(), pos.getX() + 0.5, pos.getY() + 0.5,
                    pos.getZ() + 0.5, new ItemStack(ModItems.SCULK_SOUL.get())));
        }
    }

    private static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!WardenLootConfig.ENABLE_WARDEN_BANE.get()
                || !(event.getEntity() instanceof Warden)
                || !(event.getSource().getEntity() instanceof LivingEntity attacker)
                || !(attacker.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        Holder<Enchantment> enchantment = serverLevel.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(WardenLootForge.WARDEN_DAMAGE_KEY);
        int level = EnchantmentHelper.getItemEnchantmentLevel(enchantment, attacker.getMainHandItem());
        if (level > 0) {
            float bonus = (10.0F + level * 6.0F)
                    * WardenLootConfig.WARDEN_BANE_MULTIPLIER.get().floatValue();
            event.setAmount(event.getAmount() + bonus);
        }
    }

    private static void onWardenChangeTarget(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof Warden warden)
                || !(event.getNewAboutToBeSetTarget() instanceof Player player)
                || !wearsFullArmorSet(player)) {
            return;
        }
        event.setCanceled(true);
        warden.clearAnger(player);
        warden.getBrain().setMemoryWithExpiry(net.minecraft.world.entity.ai.memory.MemoryModuleType.DIG_COOLDOWN,
                Unit.INSTANCE, 0L);
        if (player instanceof ServerPlayer serverPlayer) {
            ItemStack criterionItem = WardenLootConfig.REGISTER_CHESTPLATE.get()
                    ? new ItemStack(ModItems.SCULK_CHESTPLATE.get())
                    : new ItemStack(ModItems.SCULK_INGOT.get());
            CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, criterionItem);
        }
    }

    private static boolean wearsFullArmorSet(Player player) {
        int total = 0;
        int equipped = 0;
        if (WardenLootConfig.REGISTER_CHESTPLATE.get()) {
            total++;
            if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ModArmorItem) {
                equipped++;
            }
        }
        if (WardenLootConfig.REGISTER_OTHER_ARMOR.get()) {
            total += 3;
            if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.SCULK_HELMET.get())) equipped++;
            if (player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.SCULK_LEGGINGS.get())) equipped++;
            if (player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.SCULK_BOOTS.get())) equipped++;
        }
        return total > 0 && equipped == total;
    }
}
