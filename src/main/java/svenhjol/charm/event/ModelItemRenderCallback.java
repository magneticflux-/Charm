package svenhjol.charm.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

public interface ModelItemRenderCallback {
    Event<ModelItemRenderCallback> EVENT = EventFactory.createArrayBacked(ModelItemRenderCallback.class, (listeners) -> (renderer, matrices, stack, vertexConsumers, light, overlay) -> {
        for (ModelItemRenderCallback listener : listeners) {
            if (listener.interact(renderer, matrices, stack, vertexConsumers, light, overlay)) {
                return true;
            }
        }

        return false;
    });

    boolean interact(BuiltinModelItemRenderer renderer, MatrixStack matrices, ItemStack stack, VertexConsumerProvider vertexConsumers, int light, int overlay);
}
