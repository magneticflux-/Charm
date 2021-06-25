package svenhjol.charm.module.potion_of_recall;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import svenhjol.charm.Charm;
import svenhjol.charm.annotation.Module;
import svenhjol.charm.event.EntityTickCallback;
import svenhjol.charm.event.ServerWorldInitCallback;
import svenhjol.charm.helper.DimensionHelper;
import svenhjol.charm.module.CharmModule;
import svenhjol.charm.potion.CharmPotion;

import java.util.Optional;
import java.util.UUID;

@Module(mod = Charm.MOD_ID, description = "When the Recall potion effect wears off you will immediately teleport back to the place where you drank it.")
public class PotionOfRecall extends CharmModule {
    public static RecallEffect RECALL_EFFECT;
    public static CharmPotion RECALL_POTION;
    public static CharmPotion LONG_RECALL_POTION;

    private static RecallSavedData savedData;

    @Override
    public void register() {
        RECALL_EFFECT = new RecallEffect(this);
        RECALL_POTION = new RecallPotion(this);
        LONG_RECALL_POTION = new LongRecallPotion(this);
    }

    @Override
    public void init() {
        EntityTickCallback.EVENT.register(this::handleEntityTick);
        ServerWorldInitCallback.EVENT.register(this::loadRecallSavedData);
    }

    private void handleEntityTick(LivingEntity entity) {
        if (entity.level.isClientSide || entity.level.getGameTime() % 5 == 0)
            return;

        getSavedData().ifPresent(data -> {
            UUID uuid = entity.getUUID();

            if (!entity.hasEffect(RECALL_EFFECT)) {
                if (!data.dimensions.containsKey(uuid) || !data.starts.containsKey(uuid))
                    return;

                // entity has just lost the effect, do the teleport
                ServerLevel serverLevel = (ServerLevel)entity.level;
                ResourceLocation dimension = data.dimensions.get(uuid);
                BlockPos start = data.starts.get(uuid);
                data.dimensions.remove(uuid);
                data.starts.remove(uuid);
                data.setDirty();

                ResourceKey<Level> destDimension = DimensionHelper.getDimension(dimension);
                if (destDimension == null) {
                    Charm.LOG.error("Destination dimension did not make sense: " + dimension);
                    return;
                }

                ServerLevel destLevel = serverLevel.getServer().getLevel(destDimension);
                if (destLevel == null) {
                    Charm.LOG.error("Could not look up dimension by its registry key: " + dimension);
                    return;
                }

                if (destLevel == serverLevel) {
                    serverLevel.playSound(null, entity.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 0.94F);
                    entity.teleportToWithTicket(start.getX() + 0.5D, start.getY(), start.getZ() + 0.5D);
                    serverLevel.playSound(null, start, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.06F);
                } else {
                    entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 7 * 20));
                }

                return;
            }

            if (!data.dimensions.containsKey(uuid)) {
                // entity has just received the effect, store the dimension and start position
                data.dimensions.put(uuid, DimensionHelper.getDimension(entity.level));
                data.starts.put(uuid, entity.blockPosition());
                data.setDirty();
            }
        });
    }

    public static Optional<RecallSavedData> getSavedData() {
        return Optional.ofNullable(savedData);
    }

    private void loadRecallSavedData(ServerLevel level) {
        if (!DimensionHelper.isOverworld(level))
            return;

        savedData = level.getDataStorage().computeIfAbsent(
            (nbt) -> RecallSavedData.fromNbt(level, nbt),
            () -> new RecallSavedData(level),
            RecallSavedData.nameFor(level.dimensionType()));

        Charm.LOG.debug("[PotionOfRecall] Loaded recall saved data" + level.dimension().location());
    }
}
