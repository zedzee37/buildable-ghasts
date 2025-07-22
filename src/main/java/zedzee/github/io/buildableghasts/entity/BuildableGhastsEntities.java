package zedzee.github.io.buildableghasts.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import zedzee.github.io.buildableghasts.BuildableGhasts;
import zedzee.github.io.buildableghasts.util.ModRegister;

public class BuildableGhastsEntities extends ModRegister {

    public static EntityType<StaticBlockEntity> STATIC_BLOCK_ENTITY_TYPE =
        register(
            "static_block",
            EntityType.Builder.create(StaticBlockEntity::new, SpawnGroup.MISC)
        );

    private static <T extends Entity> EntityType<T> register(
        String path,
        EntityType.Builder<T> builder
    ) {
        Identifier id = BuildableGhasts.identifier(path);
        return Registry.register(
            Registries.ENTITY_TYPE,
            id,
            builder.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, id))
        );
    }
}
