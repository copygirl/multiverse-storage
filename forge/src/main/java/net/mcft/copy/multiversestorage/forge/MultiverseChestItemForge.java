package net.mcft.copy.multiversestorage.forge;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.mcft.copy.multiversestorage.MultiverseChestItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.util.Lazy;

public class MultiverseChestItemForge
extends MultiverseChestItem {
	public MultiverseChestItemForge(Block block, Properties properties) {
		super(block, properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		consumer.accept(new BlockEntityItemRenderProperties(this));
	}

	@OnlyIn(Dist.CLIENT)
	public static class BlockEntityItemRenderProperties
	implements IItemRenderProperties {
		private final Lazy<BlockEntityItemRenderer> renderer;

		public BlockEntityItemRenderProperties(BlockItem item) {
			// At this point I'm not sure what I'm doing and instead I'll just try what works.
			this.renderer = Lazy.of(() -> {
				EntityBlock block  = (EntityBlock)item.getBlock();
				BlockState state   = item.getBlock().defaultBlockState();
				BlockEntity entity = block.newBlockEntity(BlockPos.ZERO, state);
				return new BlockEntityItemRenderer(entity);
			});
		}

		@Override
		public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
			return this.renderer.get();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class BlockEntityItemRenderer
	extends BlockEntityWithoutLevelRenderer {
		private final BlockEntityRenderDispatcher dispatcher;
		private final BlockEntity entity;

		public BlockEntityItemRenderer(BlockEntity entity) {
			super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
			this.dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
			this.entity     = entity;
		}

		@Override
		public void renderByItem(ItemStack stack, TransformType mode, PoseStack matrices,
		                         MultiBufferSource vertexConsumers, int light, int overlay) {
			dispatcher.renderItem(entity, matrices, vertexConsumers, light, overlay);
		}
	}
}
