package svenhjol.charm.module.respawn_in_nether;

import net.minecraft.core.BlockPos;
import svenhjol.charm.Charm;
import svenhjol.charm.annotation.Module;
import svenhjol.charm.module.CharmModule;

import java.util.ArrayList;
import java.util.List;

@Module(mod = Charm.MOD_ID, enabledByDefault = false, description = "When you die, you respawn at a random position in the Nether.")
public class RespawnInNether extends CharmModule {
    public static final List<BlockPos> respawnPosition = new ArrayList<>();
}
