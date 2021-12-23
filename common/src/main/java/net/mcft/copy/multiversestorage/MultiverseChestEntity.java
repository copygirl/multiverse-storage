package net.mcft.copy.multiversestorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MultiverseChestEntity
extends BlockEntity {
	public MultiverseChestEntity(BlockPos pos, BlockState state) {
		super(MultiverseStorage.CHEST_ENTITY.get(), pos, state);
	}

	public float getOpenNess() {
		return 0; // TODO: Implement this.
	}
}
