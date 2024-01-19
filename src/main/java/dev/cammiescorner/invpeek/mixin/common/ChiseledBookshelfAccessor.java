package dev.cammiescorner.invpeek.mixin.common;

import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Optional;

@Mixin(ChiseledBookshelfBlock.class)
public interface ChiseledBookshelfAccessor {
	@Invoker("getHitSlot")
	static int invpeek$getHitSlot(Vec2f vec2f) {
		return 0;
	}

	@Invoker("getRelativeHitCoordinates")
	static Optional<Vec2f> invpeek$getRelativeHitCoordinates(BlockHitResult blockHitResult, Direction direction) {
		return Optional.empty();
	}
}
