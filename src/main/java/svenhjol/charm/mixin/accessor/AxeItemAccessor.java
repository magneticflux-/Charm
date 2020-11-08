package svenhjol.charm.mixin.accessor;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(AxeItem.class)
public interface AxeItemAccessor {
    @Accessor("EFFECTIVE_BLOCKS")
    static Set<Block> getEffectiveBlocks() {
        throw new IllegalStateException();
    }

    @Accessor("EFFECTIVE_BLOCKS")
    static void setEffectiveBlocks(Set<Block> blocks) {
        throw new IllegalStateException();
    }
}
