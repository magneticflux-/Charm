package svenhjol.charm.mixin.accessor;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.portal.PortalInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import svenhjol.charm.annotation.CharmMixin;

@Mixin(ServerPlayer.class)
@CharmMixin(required = true)
public interface ServerPlayerAccessor {
    @Mutable @Accessor
    void setIsChangingDimension(boolean flag);

    @Invoker
    PortalInfo invokeFindDimensionEntryPoint(ServerLevel level);
}
