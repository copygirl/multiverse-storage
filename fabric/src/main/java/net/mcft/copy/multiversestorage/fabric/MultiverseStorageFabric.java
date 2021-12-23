package net.mcft.copy.multiversestorage.fabric;

import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.fabricmc.api.ModInitializer;

public class MultiverseStorageFabric
extends MultiverseStorage
implements ModInitializer {
	@Override
	public void onInitialize() {
		MultiverseStorage.init();
	}
}
