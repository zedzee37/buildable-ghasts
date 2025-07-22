package zedzee.github.io.buildableghasts.components;

import net.minecraft.block.BlockState;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.Component;
import zedzee.github.io.buildableghasts.grid.SubGrid;

public class SubGridComponent implements Component {
    public static final int GRID_SIZE = 3;
    public SubGrid grid;

    public SubGridComponent() {
        this.grid = new SubGrid(GRID_SIZE, GRID_SIZE, GRID_SIZE);
    }

    @Override
    public void readData(ReadView readView) {
        int xSize = readView.getInt("x_size", 0);
        int ySize = readView.getInt("y_size", 0);
        int zSize = readView.getInt("z_size", 0);

        BlockState[] blocks = readView.getTypedListView("blocks", BlockState.CODEC)
                .stream()
                .toArray(BlockState[]::new);

        grid.setBlocks(blocks, xSize, ySize, zSize);
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putInt("x_size", grid.getXSize());
        writeView.putInt("y_size", grid.getYSize());
        writeView.putInt("z_size", grid.getZSize());

        WriteView.ListAppender<BlockState> blocksListView = writeView.getListAppender("blocks", BlockState.CODEC);

        for (int i = 0; i < grid.getTotalLength(); i++) {
            blocksListView.add(grid.getI(i));
        }
    }
}
