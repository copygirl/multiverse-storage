package net.mcft.copy.multiversestorage.client.fabric;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.impl.client.rendering.EntityModelLayerImpl;
import net.mcft.copy.multiversestorage.MultiverseChestEntity;
import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.mcft.copy.multiversestorage.client.MultiverseChestRenderer;
import net.mcft.copy.multiversestorage.client.MultiverseStorageClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.BlockPos;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class MultiverseStorageClientFabric
extends MultiverseStorageClient {
	@Override
	public void init() {
		BlockEntityRendererRegistry.register(MultiverseStorage.CHEST_ENTITY.get(), MultiverseChestRenderer::new);

		EntityModelLayerImpl.PROVIDERS.put(MultiverseStorageClient.LAYER_CHEST, MultiverseChestRenderer::createBodyLayer);

		ClientSpriteRegistryCallback.event(Sheets.CHEST_SHEET).register(
			(atlas, registry) -> registry.register(MultiverseStorageClient.TEXTURE_CHEST));

		MultiverseChestEntity chestEntity = new MultiverseChestEntity(BlockPos.ZERO, MultiverseStorage.CHEST.get().defaultBlockState());
		BuiltinItemRendererRegistry.INSTANCE.register(MultiverseStorage.CHEST_ITEM.get(), (stack, mode, matrices, vertexConsumers, light, overlay) ->
			Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(chestEntity, matrices, vertexConsumers, light, overlay));
	}
}
