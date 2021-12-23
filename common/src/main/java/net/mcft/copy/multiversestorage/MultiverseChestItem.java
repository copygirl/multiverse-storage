package net.mcft.copy.multiversestorage;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class MultiverseChestItem
extends BlockItem {
	public MultiverseChestItem(Block block, Properties properties) {
		super(block, properties);
	}

	@ExpectPlatform
	public static BlockItem create(Block block, Properties properties) {
		throw new AssertionError();
	}
}
