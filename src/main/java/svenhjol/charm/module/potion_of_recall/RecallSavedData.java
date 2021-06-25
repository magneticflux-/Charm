package svenhjol.charm.module.potion_of_recall;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RecallSavedData extends SavedData {
    public static final String DIMENSIONS_NBT = "Dimensions";
    public static final String STARTS_NBT = "Starts";

    private final Level level;

    public Map<UUID, ResourceLocation> dimensions = new HashMap<>();
    public Map<UUID, BlockPos> starts = new HashMap<>();

    public RecallSavedData(ServerLevel level) {
        this.level = level;
        setDirty();
    }

    public static RecallSavedData fromNbt(ServerLevel level, CompoundTag nbt) {
        RecallSavedData savedData = new RecallSavedData(level);
        CompoundTag dimensions = (CompoundTag)nbt.get(DIMENSIONS_NBT);
        CompoundTag starts = (CompoundTag)nbt.get(STARTS_NBT);

        savedData.dimensions = new HashMap<>();
        savedData.starts = new HashMap<>();

        if (dimensions != null && !dimensions.isEmpty()) {
            dimensions.getAllKeys().forEach(u -> {
                String dim = dimensions.getString(u);
                savedData.dimensions.put(UUID.fromString(u), ResourceLocation.tryParse(dim));
            });
        }
        if (starts != null && !starts.isEmpty()) {
            starts.getAllKeys().forEach(u -> {
                long l = starts.getLong(u);
                savedData.starts.put(UUID.fromString(u), BlockPos.of(l));
            });
        }

        return savedData;
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        CompoundTag dimensionsNbt = new CompoundTag();
        CompoundTag startsNbt = new CompoundTag();

        this.dimensions.forEach((uuid, res) -> {
            dimensionsNbt.putString(uuid.toString(), res.toString());
        });

        this.starts.forEach((uuid, pos) -> {
            startsNbt.putLong(uuid.toString(), pos.asLong());
        });

        compoundTag.put(DIMENSIONS_NBT, dimensionsNbt);
        compoundTag.put(STARTS_NBT, startsNbt);
        return compoundTag;
    }

    public static String nameFor(DimensionType dimensionType) {
        return "charm_recall_effect" + dimensionType.getFileSuffix();
    }
}
