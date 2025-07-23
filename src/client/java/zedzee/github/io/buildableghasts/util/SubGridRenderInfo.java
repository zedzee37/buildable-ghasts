package zedzee.github.io.buildableghasts.util;

import zedzee.github.io.buildableghasts.grid.SubGrid;

public interface SubGridRenderInfo {
    default SubGrid getSubGrid() {
        return null;
    }
    default void setSubGrid(SubGrid subGrid) {}
}
