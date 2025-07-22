package zedzee.github.io.buildableghasts.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.world.World;

public class StaticBlockEntity extends FallingBlockEntity {

    public StaticBlockEntity(
        EntityType<? extends FallingBlockEntity> entityType,
        World world
    ) {
        super(entityType, world);
    }

    @Override
    public void tick() {}
}
