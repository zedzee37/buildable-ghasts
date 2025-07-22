package zedzee.github.io.buildableghasts.grid;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import zedzee.github.io.buildableghasts.BuildableGhasts;

import java.util.Arrays;

public class SubGrid {
    private int xSize;
    private int ySize;
    private int zSize;
    private BlockState[] blocks;
    private static final BlockState AIR = Blocks.AIR.getDefaultState();

    public SubGrid(int width, int height, int depth) {
        this.xSize = width;
        this.ySize = height;
        this.zSize = depth;

        blocks = new BlockState[width * height * depth];
        Arrays.fill(blocks, AIR);
    }

    public void setBlocks(BlockState[] blocks, int xSize, int ySize, int zSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
        this.blocks = blocks;
    }

    public void setAir(int i) {
        blocks[i] = AIR;
    }

    public BlockState getI(int i) {
        return blocks[i];
    }

    public void setI(BlockState state, int i) {
        blocks[i] = state;
    }

    private int coordsToIdx(int x, int y, int z) {
        return x + y * xSize + z * xSize * ySize;
    }

    public BlockState get(int x, int y, int z) {
        return blocks[coordsToIdx(x, y, z)];
    }

    public void set(BlockState state, int x, int y, int z) {
        blocks[coordsToIdx(x, y, z)] = state;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public int getZSize() {
        return zSize;
    }

    public int getTotalLength() {
        return xSize * ySize * zSize;
    }

    public void debugPrintGrid() {
        for (int i = 0; i < xSize; i++) {
            BuildableGhasts.LOGGER.info("LAYER {}", i);

            for (int j = 0; j < ySize; j++) {

                for (int k = 0; k < zSize; k++) {
                    BuildableGhasts.LOGGER.info(" {} ", blocks[coordsToIdx(i, j, k)].getBlock().getName().toString());
                }

                BuildableGhasts.LOGGER.info("");
            }

            BuildableGhasts.LOGGER.info("LAYER END");
        }
    }
}
