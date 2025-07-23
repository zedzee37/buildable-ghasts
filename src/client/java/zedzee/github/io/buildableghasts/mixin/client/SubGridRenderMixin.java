package zedzee.github.io.buildableghasts.mixin.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HappyGhastEntityRenderer;
import net.minecraft.client.render.entity.model.HappyGhastEntityModel;
import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import net.minecraft.entity.passive.HappyGhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zedzee.github.io.buildableghasts.EntityComponents;
import zedzee.github.io.buildableghasts.SubGridHappyGhastRenderFeature;
import zedzee.github.io.buildableghasts.components.SubGridComponent;
import zedzee.github.io.buildableghasts.grid.SubGrid;
import zedzee.github.io.buildableghasts.util.SubGridRenderInfo;

import java.util.Optional;

@Mixin(HappyGhastEntityRenderer.class)
public class SubGridRenderMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void addRenderFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        HappyGhastEntityRenderer renderer = (HappyGhastEntityRenderer)(Object)this;

        AddFeatureInvoker<HappyGhastEntityRenderState, HappyGhastEntityModel> invoker =
                (AddFeatureInvoker<HappyGhastEntityRenderState, HappyGhastEntityModel>) renderer;

        invoker.invokeAddFeature(new SubGridHappyGhastRenderFeature<>(renderer, context.getBlockRenderManager()));
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/passive/HappyGhastEntity;Lnet/minecraft/client/render/entity/state/HappyGhastEntityRenderState;F)V", at = @At("TAIL"))
    public void addSubGridInfo(HappyGhastEntity happyGhastEntity, HappyGhastEntityRenderState happyGhastEntityRenderState, float f, CallbackInfo ci) {
        Optional<SubGridComponent> maybeSubGrid = EntityComponents.SUB_GRID.maybeGet(happyGhastEntity);
        if (maybeSubGrid.isEmpty()) {
            return;
        }

        SubGrid subGrid = maybeSubGrid.get().grid;
        SubGridRenderInfo renderInfo = (SubGridRenderInfo) happyGhastEntityRenderState;
        renderInfo.setSubGrid(subGrid);
    }
}
