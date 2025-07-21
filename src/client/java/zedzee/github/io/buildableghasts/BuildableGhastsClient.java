package zedzee.github.io.buildableghasts;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import zedzee.github.io.buildableghasts.mixin.client.DrawBlockOutlineAccessor;

import java.util.Random;

public class BuildableGhastsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((context, hitResult) -> {
			DrawBlockOutlineAccessor outlineAccessor = ((DrawBlockOutlineAccessor) context.worldRenderer());

			MinecraftClient client = MinecraftClient.getInstance();
			MatrixStack matrices = new MatrixStack();
			BufferBuilderStorage bufferBuilders = client.getBufferBuilders();
			VertexConsumer consumer = bufferBuilders.getEntityVertexConsumers().getBuffer(RenderLayer.getLines());
			ClientPlayerEntity player = client.player;

			Camera camera = context.camera();
			Vec3d cameraPos = camera.getCameraPos();

			if (!(hitResult instanceof BlockHitResult blockHitResult)) {
				return false;
			}

			BlockPos pos = blockHitResult.getBlockPos();
			BlockState state = player.getWorld().getBlockState(pos);

			VertexRendering.drawOutline(
					matrices,
					consumer,
					state.getOutlineShape(player.getWorld(), pos, ShapeContext.of(player)),
					((double)pos.getX() + 0.03f) - cameraPos.x,
					((double)pos.getY() + 0.03f) - cameraPos.y,
					(double)pos.getZ() - cameraPos.z,
					ColorHelper.withAlpha(102, -16777216)
			);
			return false;
		});
	}
}