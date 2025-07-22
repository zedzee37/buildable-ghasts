package zedzee.github.io.buildableghasts;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.HappyGhastEntityModel;
import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class SubGridHappyGhastRenderFeature<M extends HappyGhastEntityModel> extends FeatureRenderer<HappyGhastEntityRenderState, M> {
    public SubGridHappyGhastRenderFeature(FeatureRendererContext<HappyGhastEntityRenderState, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HappyGhastEntityRenderState state, float limbAngle, float limbDistance) {

    }
}
