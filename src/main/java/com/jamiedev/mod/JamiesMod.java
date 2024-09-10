package com.jamiedev.mod;

import com.jamiedev.mod.init.*;
import com.jamiedev.mod.items.JamiesModItemGroup;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JamiesMod implements ModInitializer {
	public static String MOD_ID = "jamiesmod";

	@Override
	public void onInitialize() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		JamiesModBlocks.init();
		JamiesModItems.init();
		JamiesModEntityTypes.init();
		JamiesModBiomes.init();
		JamiesModItemGroup.registerItemgroups();
		JamiesModFeatures.init();
	}

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static Identifier getModId(String id){
		return Identifier.of(MOD_ID, id);
	}

}

/**
 *
 *
 *
 * JAMIES MOD v7i643
 *
 * dimension
 *
 *
 */