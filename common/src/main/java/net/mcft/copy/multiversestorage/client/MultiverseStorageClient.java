package net.mcft.copy.multiversestorage.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public abstract class MultiverseStorageClient {
	public static final ResourceLocation TEXTURE_CHEST = new ResourceLocation(MultiverseStorage.MOD_ID, "entity/chest");
	public static final ModelLayerLocation LAYER_CHEST = new ModelLayerLocation(new ResourceLocation(MultiverseStorage.MOD_ID, "chest"), "main");
}
