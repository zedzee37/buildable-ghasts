package zedzee.github.io.buildableghasts;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zedzee.github.io.buildableghasts.entity.BuildableGhastsEntities;
import zedzee.github.io.buildableghasts.world.VirtualWorld;

public class BuildableGhasts implements ModInitializer {
	public static final String MOD_ID = "buildable_ghasts";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BuildableGhastsEntities.init();
	}

	public static Identifier identifier(String path) {
		return Identifier.of(MOD_ID, path);
	}
}