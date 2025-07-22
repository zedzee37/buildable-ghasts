package zedzee.github.io.buildableghasts.components;

import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import zedzee.github.io.buildableghasts.EntityComponents;
import zedzee.github.io.buildableghasts.grid.SubGrid;

public class SubGridComponent implements Component, AutoSyncedComponent {
    public static final int GRID_SIZE = 3;
    public SubGrid grid;
    private final HappyGhastEntity provider;

    public SubGridComponent(HappyGhastEntity provider) {
        this.provider = provider;
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

    public void setI(BlockState state, int i) {
        grid.setI(state, i);
        EntityComponents.SUB_GRID.sync(this.provider);
    }

    public void set(BlockState state, int x, int y, int z) {
        grid.set(state, x, y, z);
        EntityComponents.SUB_GRID.sync(this.provider);
    }

    // TODO: Write optimize packets
}
