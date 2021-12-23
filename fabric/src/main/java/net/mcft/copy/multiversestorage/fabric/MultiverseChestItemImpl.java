package net.mcft.copy.multiversestorage.fabric;

import net.mcft.copy.multiversestorage.MultiverseChestItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MultiverseChestItemImpl {
	public static BlockItem create(Block block, Item.Properties properties) {
		return new MultiverseChestItem(block, properties);
	}
}
