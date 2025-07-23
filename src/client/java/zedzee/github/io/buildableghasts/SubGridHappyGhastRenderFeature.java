package zedzee.github.io.buildableghasts;

import net.minecraft.block.Blocks;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.HappyGhastEntityModel;
import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import zedzee.github.io.buildableghasts.grid.SubGrid;
import zedzee.github.io.buildableghasts.util.SubGridRenderInfo;

public class SubGridHappyGhastRenderFeature<M extends HappyGhastEntityModel> extends FeatureRenderer<HappyGhastEntityRenderState, M> {
    private final BlockRenderManager blockRenderManager;

    public SubGridHappyGhastRenderFeature(FeatureRendererContext<HappyGhastEntityRenderState, M> context, BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HappyGhastEntityRenderState state, float limbAngle, float limbDistance) {
        SubGridRenderInfo subGridInfo = (SubGridRenderInfo) state;

        SubGrid subGrid = subGridInfo.getSubGrid();
        if (subGrid == null) {
            return;
        }

        matrices.push();
        matrices.translate(-0.25, -2.5, 0.0);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        blockRenderManager.renderBlockAsEntity(Blocks.DIRT.getDefaultState(), matrices, vertexConsumers, 255, OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }
}
