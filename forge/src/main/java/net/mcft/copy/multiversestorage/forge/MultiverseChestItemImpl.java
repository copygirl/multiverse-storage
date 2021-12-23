package net.mcft.copy.multiversestorage.forge;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MultiverseChestItemImpl {
	public static BlockItem create(Block block, Item.Properties properties) {
		return new MultiverseChestItemForge(block, properties);
	}
}
