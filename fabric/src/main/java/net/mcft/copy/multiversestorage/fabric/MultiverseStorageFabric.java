package net.mcft.copy.multiversestorage.fabric;

import net.fabricmc.api.ModInitializer;
import net.mcft.copy.multiversestorage.MultiverseStorage;

public class MultiverseStorageFabric
extends MultiverseStorage
implements ModInitializer {
	@Override
	public void onInitialize() {
		init();
	}
}
