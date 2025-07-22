package zedzee.github.io.buildableghasts;

import net.minecraft.entity.passive.HappyGhastEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import zedzee.github.io.buildableghasts.components.SubGridComponent;

public class EntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<SubGridComponent> SUB_GRID =
            ComponentRegistry.getOrCreate(BuildableGhasts.identifier("sub_grid"), SubGridComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(HappyGhastEntity.class, SUB_GRID, (entity) -> new SubGridComponent());
    }
}
