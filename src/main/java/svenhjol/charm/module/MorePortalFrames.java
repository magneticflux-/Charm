package svenhjol.charm.module;

import net.minecraft.block.BlockState;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.CharmTags;
import svenhjol.charm.base.iface.Module;

@Module(mod = Charm.MOD_ID, description = "Crying obsidian and refined obsidian can be used to construct nether portal frames.")
public class MorePortalFrames extends CharmModule {
    public static boolean isValidBlock(BlockState blockState) {
        return blockState.isIn(CharmTags.NETHER_PORTAL_FRAMES);
    }
}
