package net.mcft.copy.multiversestorage.client.mixin.forge;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.mcft.copy.multiversestorage.client.forge.MultiverseStorageClientForge;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.RenderProperties;

@Mixin(RenderProperties.class)
public abstract class DynamicItemRendererMixin {
	@Inject(method = "get(Lnet/minecraft/world/item/Item;)Lnet/minecraftforge/client/IItemRenderProperties;",
	        at = @At("HEAD"), remap = false, cancellable = true)
	private static void get(Item item, CallbackInfoReturnable<IItemRenderProperties> ci) {
		IItemRenderProperties props = MultiverseStorageClientForge.dynamicItemRenderers.get(item);
		if (props != null) ci.setReturnValue(props);
	}
}
