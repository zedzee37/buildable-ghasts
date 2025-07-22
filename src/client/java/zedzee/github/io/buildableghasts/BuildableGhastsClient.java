package zedzee.github.io.buildableghasts;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import zedzee.github.io.buildableghasts.entity.BuildableGhastsEntities;

public class BuildableGhastsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(
            BuildableGhastsEntities.STATIC_BLOCK_ENTITY_TYPE,
            FallingBlockEntityRenderer::new
        );

        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register(
            (context, hitResult) -> {
                if (
                    !(hitResult instanceof EntityHitResult entityHitResult) ||
                    entityHitResult.getEntity().getType() !=
                    EntityType.HAPPY_GHAST
                ) {
                    return true;
                }
                //			if (!(hitResult instanceof EntityHitResult entityHitResult)) {
                //				return true;
                //			}

                HappyGhastEntity happyGhast =
                    (HappyGhastEntity) entityHitResult.getEntity();

                // Has to be wearing the harness
                if (!happyGhast.isWearingBodyArmor()) {
                    return true;
                }

                MinecraftClient client = MinecraftClient.getInstance();
                MatrixStack matrices = new MatrixStack();
                BufferBuilderStorage bufferBuilders =
                    client.getBufferBuilders();

                VertexConsumer consumer = bufferBuilders
                    .getEntityVertexConsumers()
                    .getBuffer(RenderLayer.getLines());

                Camera camera = context.camera();
                Vec3d cameraPos = camera.getCameraPos();

                Entity entity = entityHitResult.getEntity();
                Vec3d pos = hitResult.getPos();
                pos = new Vec3d(
                    Math.round(pos.x),
                    entity.getPos().y,
                    Math.round(pos.z)
                );

                //			Box boundingBox = entity.getBoundingBox();
                //
                //			// get rid of the global position of the box
                //			boundingBox = boundingBox.offset(-pos.x, -pos.y, -pos.z);

                VoxelShape shape = VoxelShapes.fullCube();
                matrices.translate(pos.subtract(cameraPos));
                matrices.multiply(
                    RotationAxis.POSITIVE_Y.rotationDegrees(
                        -entity.getBodyYaw()
                    )
                );

                VertexRendering.drawOutline(
                    matrices,
                    consumer,
                    shape,
                    -0.5,
                    3,
                    -0.5,
                    ColorHelper.withAlpha(102, -16777216)
                );

                return false;
            }
        );
    }
}
