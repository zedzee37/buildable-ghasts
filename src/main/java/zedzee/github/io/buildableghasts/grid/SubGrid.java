package zedzee.github.io.buildableghasts.grid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryEntryLookup;

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

    public NbtCompound writeToNbt() {
        NbtCompound compound = new NbtCompound();

        compound.putInt("x_size", xSize);
        compound.putInt("y_size", ySize);
        compound.putInt("z_size", zSize);

        NbtList blockList = new NbtList();

        for (int i = 0; i < blocks.length; i++) {
            BlockState state = blocks[i];

            if (state == null) {
                continue;
            }

            blockList.add(NbtHelper.fromBlockState(state));
        }

        compound.put("blocks", blockList);
        return compound;
    }

    public void readFromNbt(RegistryEntryLookup<Block> blockRegistryLookup, NbtCompound compound) {
        xSize = compound.getInt("x_size").get();
        ySize = compound.getInt("y_size").get();
        zSize = compound.getInt("z_size").get();

        NbtList blocksList = compound.getList("blocks").get();
        for (int i = 0; i < blocksList.size(); i++) {
            blocks[i] = NbtHelper.toBlockState(blockRegistryLookup, blocksList.get(i).asCompound().get());
        }
    }

    public void setAir(int i) {
        blocks[i] = AIR;
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
}
