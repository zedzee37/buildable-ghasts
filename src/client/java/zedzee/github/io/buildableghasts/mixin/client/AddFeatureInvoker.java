package zedzee.github.io.buildableghasts.mixin.client;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntityRenderer.class)
public interface AddFeatureInvoker<S extends EntityRenderState, M extends EntityModel<? super S>> {
    @Invoker("addFeature")
    boolean invokeAddFeature(FeatureRenderer<S, M> feature);
}
