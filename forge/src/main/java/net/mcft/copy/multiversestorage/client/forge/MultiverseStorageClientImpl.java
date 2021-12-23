package net.mcft.copy.multiversestorage.client.forge;

import net.mcft.copy.multiversestorage.client.MultiverseStorageClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MultiverseStorageClientImpl {
	public static MultiverseStorageClient create() {
		return new MultiverseStorageClientForge();
	}
}
