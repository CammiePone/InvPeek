package dev.cammiescorner.invpeek.client;

import com.mojang.blaze3d.platform.InputUtil;
import dev.cammiescorner.invpeek.InvPeek;
import dev.cammiescorner.invpeek.InvPeekConfig;
import dev.cammiescorner.invpeek.api.InvPeekRegistry;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.KeyBind;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

public class InvPeekClient implements ClientModInitializer {
	private final MinecraftClient client = MinecraftClient.getInstance();
	public static KeyBind hidePreviews;

	@Override
	public void onInitializeClient(ModContainer mod) {
		hidePreviews = KeyBindingHelper.registerKeyBinding(new KeyBind("key.invpeek.hidePreviews", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "key_category.invpeek"));

		ClientTickEvents.END.register(client1 -> {
			if(client1.player != null && client1.world != null) {
				while(hidePreviews.wasPressed()) {
					InvPeekConfig.hideAllPreviews = !InvPeekConfig.hideAllPreviews;
					InvPeekConfig.write(InvPeek.MOD_ID);
				}
			}
		});

		HudRenderCallback.EVENT.register((graphics, tickDelta) -> {
			PlayerEntity player = client.player;
			World world = client.world;

			if(!InvPeekConfig.hideAllPreviews && player != null && !player.isSpectator() && (client.currentScreen == null || client.currentScreen instanceof ChatScreen)) {
				if(world != null && client.crosshairTarget != null && client.crosshairTarget.getType() != HitResult.Type.MISS) {
					if(client.crosshairTarget instanceof BlockHitResult hitResult) {
						BlockPos pos = hitResult.getBlockPos();
						BlockEntity blockEntity = world.getBlockEntity(pos);
						BlockState state = world.getBlockState(pos);

						if(blockEntity != null) {
							ItemStack stack = InvPeekRegistry.BLOCK_ENTITIES.getOrDefault(blockEntity.getType(), (player1, world1, hitResult1, state1, blockEntity1) -> ItemStack.EMPTY).apply(player, world, hitResult, state, blockEntity);

							if(stack != null && !stack.isEmpty())
								graphics.drawTooltip(client.textRenderer, stack, client.getWindow().getScaledWidth() / 2, client.getWindow().getScaledHeight() / 2);
						}
					}

					if(client.crosshairTarget instanceof EntityHitResult hitResult) {
						Entity entity = hitResult.getEntity();

						if(entity != null) {
							ItemStack stack = InvPeekRegistry.ENTITIES.getOrDefault(entity.getType(), (player1, world1, hitResult1, entity1) -> ItemStack.EMPTY).apply(player, world, hitResult, entity);

							if(stack != null && !stack.isEmpty())
								graphics.drawTooltip(client.textRenderer, stack, client.getWindow().getScaledWidth() / 2, client.getWindow().getScaledHeight() / 2);
						}
					}
				}
			}
		});
	}
}
