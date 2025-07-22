package zedzee.github.io.buildableghasts.mixin.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HappyGhastEntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.HappyGhastEntityModel;
import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import net.minecraft.entity.passive.HappyGhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zedzee.github.io.buildableghasts.SubGridHappyGhastRenderFeature;

@Mixin(HappyGhastEntityRenderer.class)
public class SubGridRenderMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void addRenderFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        HappyGhastEntityRenderer renderer = (HappyGhastEntityRenderer)(Object)this;
        LivingEntityRenderer<HappyGhastEntity, HappyGhastEntityRenderState, HappyGhastEntityModel> livingEntityRenderer = renderer;
        AddFeatureInvoker<HappyGhastEntityRenderState, HappyGhastEntityModel> invoker = (AddFeatureInvoker<HappyGhastEntityRenderState, HappyGhastEntityModel>)livingEntityRenderer;

        invoker.invokeAddFeature(new SubGridHappyGhastRenderFeature<>(renderer));
    }
}
