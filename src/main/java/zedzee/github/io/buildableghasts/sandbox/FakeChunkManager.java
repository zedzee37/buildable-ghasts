package zedzee.github.io.buildableghasts.sandbox;

import net.minecraft.world.BlockView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.light.LightingProvider;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;

public class FakeChunkManager extends ChunkManager {
    @Override
    public @Nullable Chunk getChunk(int x, int z, ChunkStatus leastStatus, boolean create) {
        return null;
    }

    @Override
    public void tick(BooleanSupplier shouldKeepTicking, boolean tickChunks) {

    }

    @Override
    public String getDebugString() {
        return "";
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public LightingProvider getLightingProvider() {
        return null;
    }

    @Override
    public BlockView getWorld() {
        return null;
    }
}
