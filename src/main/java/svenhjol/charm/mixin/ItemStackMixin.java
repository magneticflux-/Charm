package svenhjol.charm.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charm.base.CharmTags;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    /**
     * Check the CharmTags shields tag rather than just the vanilla shield item.
     * There are too many hardcoded checks in vanilla so *gently* patch this method.
     * @param item
     * @param cir
     */
    @Inject(
        method = "isOf",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookIsOf(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (item == Items.SHIELD && CharmTags.SHIELDS.contains(this.getItem())) {
            cir.setReturnValue(true);
        }
    }
}
