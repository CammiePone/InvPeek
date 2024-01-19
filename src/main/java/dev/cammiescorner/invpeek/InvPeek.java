package dev.cammiescorner.invpeek;

import dev.cammiescorner.invpeek.api.InvPeekInitializer;
import dev.cammiescorner.invpeek.api.InvPeekRegistry;
import dev.cammiescorner.invpeek.mixin.common.ArmorStandAccessor;
import dev.cammiescorner.invpeek.mixin.common.ChiseledBookshelfAccessor;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec2f;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import java.util.Optional;

public class InvPeek implements ModInitializer, InvPeekInitializer {
	private static final InvPeekRegistry REGISTRY = new InvPeekRegistry();
	public static final String MOD_ID = "invpeek";

	@Override
	public void onInitialize(ModContainer mod) {
		MidnightConfig.init(MOD_ID, InvPeekConfig.class);

		for(InvPeekInitializer entrypoint : QuiltLoader.getEntrypoints(MOD_ID, InvPeekInitializer.class))
			entrypoint.init(REGISTRY);
	}

	@Override
	public void init(InvPeekRegistry registry) {
		registry.registerBlockEntity(BlockEntityType.CHISELED_BOOKSHELF, (player, world, hitResult, blockState, chiseledBookshelf) -> {
			if(!InvPeekConfig.sneakToPreviewBookshelf || player.isSneaking()) {
				Optional<Vec2f> optional = ChiseledBookshelfAccessor.invpeek$getRelativeHitCoordinates(hitResult, blockState.get(HorizontalFacingBlock.FACING));

				if(optional.isPresent())
					return chiseledBookshelf.getStack(ChiseledBookshelfAccessor.invpeek$getHitSlot(optional.get()));
			}

			return ItemStack.EMPTY;
		});
		
		registry.registerBlockEntity(BlockEntityType.LECTERN, (player, world, hitResult, blockState, lectern) -> (!InvPeekConfig.sneakToPreviewLectern || player.isSneaking()) && blockState.get(LecternBlock.HAS_BOOK) ? lectern.getBook() : ItemStack.EMPTY);
		registry.registerEntity(EntityType.ARMOR_STAND, (player, world, hitResult, armorStand) -> !InvPeekConfig.sneakToPreviewArmorStand || player.isSneaking() ? armorStand.getEquippedStack(((ArmorStandAccessor) armorStand).invpeek$slotFromPosition(hitResult.getPos().subtract(armorStand.getPos()))) : ItemStack.EMPTY);
		registry.registerEntity(EntityType.ITEM_FRAME, (player, world, hitResult, itemFrame) -> !InvPeekConfig.sneakToPreviewItemFrame || player.isSneaking() ? itemFrame.getHeldItemStack() : ItemStack.EMPTY);
		registry.registerEntity(EntityType.GLOW_ITEM_FRAME, (player, world, hitResult, itemFrame) -> !InvPeekConfig.sneakToPreviewItemFrame || player.isSneaking() ? itemFrame.getHeldItemStack() : ItemStack.EMPTY);
	}
}
