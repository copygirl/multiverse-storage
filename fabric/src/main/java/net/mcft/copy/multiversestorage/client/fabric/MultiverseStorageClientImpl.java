package net.mcft.copy.multiversestorage.client.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mcft.copy.multiversestorage.client.MultiverseStorageClient;

@Environment(EnvType.CLIENT)
public class MultiverseStorageClientImpl {
	public static MultiverseStorageClient create() {
		return new MultiverseStorageClientFabric();
	}
}
