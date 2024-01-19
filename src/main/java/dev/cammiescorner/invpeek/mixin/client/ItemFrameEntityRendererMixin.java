package dev.cammiescorner.invpeek.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.cammiescorner.invpeek.InvPeekConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameEntityRendererMixin {
	@ModifyExpressionValue(method = "hasLabel", at = @At(value = "INVOKE",
		target = "Lnet/minecraft/item/ItemStack;hasCustomName()Z"
	))
	private boolean invpeek$hasLabel(boolean original) {
		return (InvPeekConfig.hideAllPreviews || (InvPeekConfig.sneakToPreviewItemFrame && !MinecraftClient.getInstance().player.isSneaking())) && original;
	}
}
