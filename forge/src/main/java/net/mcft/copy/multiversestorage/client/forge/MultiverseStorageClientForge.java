package net.mcft.copy.multiversestorage.client.forge;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;

import net.mcft.copy.multiversestorage.MultiverseChestEntity;
import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.mcft.copy.multiversestorage.client.MultiverseChestRenderer;
import net.mcft.copy.multiversestorage.client.MultiverseStorageClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MultiverseStorage.MOD_ID, bus = Bus.MOD)
public class MultiverseStorageClientForge
extends MultiverseStorageClient {
	public static final HashMap<Item, IItemRenderProperties> dynamicItemRenderers = new HashMap<>();

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(MultiverseStorage.CHEST_ENTITY.get(), MultiverseChestRenderer::new);

		MultiverseChestEntity entity = new MultiverseChestEntity(BlockPos.ZERO, MultiverseStorage.CHEST.get().defaultBlockState());
		dynamicItemRenderers.put(MultiverseStorage.CHEST_ITEM.get(), new IItemRenderProperties() {
			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				BlockEntityRenderDispatcher dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
				EntityModelSet models = Minecraft.getInstance().getEntityModels();
				return new BlockEntityWithoutLevelRenderer(dispatcher, models) {
					@Override
					public void renderByItem(ItemStack stack, TransformType mode, PoseStack matrices,
					                         MultiBufferSource vertexConsumers, int light, int overlay) {
						dispatcher.renderItem(entity, matrices, vertexConsumers, light, overlay);
					}
				};
			}
		});
	}

	@SubscribeEvent
	public static void onRegisterLayerDefinitions(RegisterLayerDefinitions event) {
		event.registerLayerDefinition(LAYER_CHEST, MultiverseChestRenderer::createBodyLayer);
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		if (event.getAtlas().location().equals(Sheets.CHEST_SHEET))
			event.addSprite(TEXTURE_CHEST);
	}
}
