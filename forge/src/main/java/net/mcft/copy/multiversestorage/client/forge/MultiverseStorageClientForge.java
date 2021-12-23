package net.mcft.copy.multiversestorage.client.forge;

import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.mcft.copy.multiversestorage.client.MultiverseChestRenderer;
import net.mcft.copy.multiversestorage.client.MultiverseStorageClient;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class MultiverseStorageClientForge
extends MultiverseStorageClient {
	@Override
	public void init() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(MultiverseStorage.CHEST_ENTITY.get(), MultiverseChestRenderer::new);
	}

	@SubscribeEvent
	public void onRegisterLayerDefinitions(RegisterLayerDefinitions event) {
		event.registerLayerDefinition(MultiverseStorageClient.LAYER_CHEST, MultiverseChestRenderer::createBodyLayer);
	}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre event) {
		if (event.getAtlas().location().equals(Sheets.CHEST_SHEET))
			event.addSprite(MultiverseStorageClient.TEXTURE_CHEST);
	}
}
