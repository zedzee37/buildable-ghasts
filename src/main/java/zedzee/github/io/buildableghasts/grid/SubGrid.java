package zedzee.github.io.buildableghasts.grid;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;

public class SubGrid {
    private final int width;
    private final int height;
    private final int depth;
    private ArrayList<ArrayList<ArrayList<BlockState>>> blocks;

    public SubGrid(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;

        blocks = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ArrayList<ArrayList<BlockState>> row = new ArrayList<>();

            for (int j = 0; j < height; j++) {
                 ArrayList<BlockState> col = new ArrayList<>();

                 for (int k = 0; k < depth; k++) {
                     col.add(Blocks.AIR.getDefaultState());
                 }

                 row.add(col);
            }
            blocks.add(row);
        }
    }

    public BlockState get(int x, int y, int z) {
        return blocks.get(x).get(y).get(z);
    }

    public void set(int x, int y, int z, BlockState state) {
        blocks.get(x).get(y).set(z, state);
    }

    public NbtCompound getNbt() {
        NbtCompound compound = new NbtCompound();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < depth; k++) {
                    BlockState state = get(i, j, k);
                }
            }
        }

        return compound;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    static record SubGridCell(Vec3i pos, NbtCompound nbtData, Block) {

    }
}
