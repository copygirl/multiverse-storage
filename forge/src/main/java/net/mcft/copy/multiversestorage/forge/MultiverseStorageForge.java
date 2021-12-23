package net.mcft.copy.multiversestorage.forge;

import dev.architectury.platform.forge.EventBuses;
import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MultiverseStorage.MOD_ID)
public class MultiverseStorageForge
extends MultiverseStorage {
	public MultiverseStorageForge() {
		EventBuses.registerModEventBus(MultiverseStorage.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
		MultiverseStorage.init();
	}
}
