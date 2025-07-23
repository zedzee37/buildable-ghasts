package zedzee.github.io.buildableghasts.mixin.client;

import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import zedzee.github.io.buildableghasts.grid.SubGrid;
import zedzee.github.io.buildableghasts.util.SubGridRenderInfo;

@Mixin(HappyGhastEntityRenderState.class)
public class HappyGhastEntitySubGridRenderState implements SubGridRenderInfo {
    @Unique
    private SubGrid subGrid;

    @Override
    public SubGrid getSubGrid() {
        return subGrid;
    }

    @Override
    public void setSubGrid(SubGrid subGrid) {
        this.subGrid = subGrid;
    }
}
