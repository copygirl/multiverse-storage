package net.mcft.copy.multiversestorage;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;

public abstract class MultiverseStorage {
	public static final String MOD_ID = "multiversestorage";

	public static final CreativeModeTab CREATIVE_TAB = CreativeTabRegistry.create(
		new ResourceLocation(MOD_ID, "creative_tab"), () -> new ItemStack(MultiverseStorage.CHEST_ITEM.get()));

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
	public static final DeferredRegister<Item>  ITEMS  = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

	public static final RegistrySupplier<Block> CHEST = BLOCKS.register("chest", () ->
		new MultiverseChest(Block.Properties.of(Material.WOOD).strength(3.0F)));
	public static final RegistrySupplier<Item> CHEST_ITEM = ITEMS.register("chest", () ->
		new BlockItem(CHEST.get(), new Item.Properties().tab(CREATIVE_TAB)));
	public static final RegistrySupplier<BlockEntityType<MultiverseChestEntity>> CHEST_ENTITY = BLOCK_ENTITIES.register("chest", () ->
		BlockEntityType.Builder.of(MultiverseChestEntity::new, CHEST.get()).build(null));

	public static void init() {
		BLOCKS.register();
		ITEMS.register();
		BLOCK_ENTITIES.register();
	}
}
