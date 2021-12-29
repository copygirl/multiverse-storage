package net.mcft.copy.multiversestorage.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mcft.copy.multiversestorage.MultiverseChest;
import net.mcft.copy.multiversestorage.MultiverseChestEntity;
import net.mcft.copy.multiversestorage.MultiverseStorage;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

@Environment(EnvType.CLIENT)
public class MultiverseChestRenderer
implements BlockEntityRenderer<MultiverseChestEntity> {
	private final ModelPart bottom;
	private final ModelPart lid;
	private final ModelPart lock;

	public MultiverseChestRenderer(BlockEntityRendererProvider.Context context) {
		ModelPart model = context.bakeLayer(MultiverseStorageClient.LAYER_CHEST);
		this.bottom = model.getChild("bottom");
		this.lid    = model.getChild("lid");
		this.lock   = model.getChild("lock");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();
		part.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1,  0,  1, 14, 10, 14), PartPose.ZERO);
		part.addOrReplaceChild("lid"   , CubeListBuilder.create().texOffs(0,  0).addBox(1,  0,  0, 14,  5, 14), PartPose.offset(0, 9, 1));
		part.addOrReplaceChild("lock"  , CubeListBuilder.create().texOffs(0,  0).addBox(7, -1, 15,  2,  4,  1), PartPose.offset(0, 8, 0));
		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public void render(MultiverseChestEntity entity, float tickDelta, PoseStack matrices,
	                   MultiBufferSource vertexConsumers, int light, int overlay) {
		BlockState state = (entity.getLevel() != null) ? entity.getBlockState()
			: MultiverseStorage.CHEST.get().defaultBlockState().setValue(MultiverseChest.FACING, Direction.SOUTH);

		matrices.pushPose();

		float rot = state.getValue(MultiverseChest.FACING).toYRot();
		matrices.translate(0.5, 0.5, 0.5);
		matrices.mulPose(Vector3f.YP.rotationDegrees(-rot));
		matrices.translate(-0.5, -0.5, -0.5);

		Material material = new Material(Sheets.CHEST_SHEET, MultiverseStorageClient.TEXTURE_CHEST);
		VertexConsumer vertices = material.buffer(vertexConsumers, RenderType::entityCutout);

		float openness = 1.0f - entity.getOpenNess();
		openness = 1.0f - openness * openness * openness;
		lid.xRot = lock.xRot = -openness * (float)Math.toDegrees(90);

		bottom.render(matrices, vertices, light, overlay);
		lid.render(matrices, vertices, light, overlay);
		lock.render(matrices, vertices, light, overlay);

		matrices.popPose();
	}
}
