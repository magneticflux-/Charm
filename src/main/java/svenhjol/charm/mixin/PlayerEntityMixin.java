package svenhjol.charm.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm.base.CharmTags;
import svenhjol.charm.event.PlayerDropInventoryCallback;
import svenhjol.charm.event.PlayerTickCallback;
import svenhjol.charm.module.AerialAffinity;
import svenhjol.charm.module.ParrotsStayOnShoulder;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
    @Shadow private long shoulderEntityAddedTime;

    @Shadow @Final public PlayerInventory inventory;

    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
        method = "dropShoulderEntities",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookSpawnShoulderEntities(CallbackInfo ci) {
        if (ParrotsStayOnShoulder.shouldParrotStayMounted(this.world, this.shoulderEntityAddedTime))
            ci.cancel();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void hookTick(CallbackInfo ci) {
        PlayerTickCallback.EVENT.invoker().interact((PlayerEntity)(Object)this);
    }

    @Inject(
        method = "dropInventory",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerInventory;dropAll()V"
        ),
        cancellable = true
    )
    private void hookDropInventory(CallbackInfo ci) {
        ActionResult result = PlayerDropInventoryCallback.EVENT.invoker().interact((PlayerEntity) (Object) this, this.inventory);
        if (result == ActionResult.SUCCESS)
            ci.cancel();
    }

    @Redirect(
        method = "getBlockBreakingSpeed",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/entity/player/PlayerEntity;onGround:Z"
        )
    )
    private boolean hookDigSpeedOnGround(PlayerEntity player, BlockState state) {
        return player.isOnGround() || AerialAffinity.digFast(player);
    }

    /**
     * Vanilla's disableShield method hardcodes the cooldown to just the vanilla shield.
     * This needs to be set for all shields so that you can't bypass the cooldown
     * by swapping to a different type of shield.
     * @param sprinting
     * @param ci
     */
    @Inject(
        method = "disableShield",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"
        )
    )
    private void hookDisableShield(boolean sprinting, CallbackInfo ci) {
        List<Item> values = CharmTags.SHIELDS.values();
        values.forEach(type -> this.getItemCooldownManager().set(type, 100));
    }
}
