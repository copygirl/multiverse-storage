package net.mcft.copy.multiversestorage.client;

import net.fabricmc.api.Environment;
import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.mcft.copy.multiversestorage.MultiverseStorageCommon;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public abstract class MultiverseStorageClient
extends MultiverseStorageCommon {
	public static final ResourceLocation TEXTURE_CHEST = new ResourceLocation(MultiverseStorage.MOD_ID, "entity/chest");
	public static final ModelLayerLocation LAYER_CHEST = new ModelLayerLocation(new ResourceLocation(MultiverseStorage.MOD_ID, "chest"), "main");

	@Override
	public abstract void init();

	@ExpectPlatform
	public static MultiverseStorageClient create() {
		throw new AssertionError();
	}
}
