package dev.cammiescorner.invpeek.mixin.common;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorStandEntity.class)
public interface ArmorStandAccessor {
	@Final
	@Invoker("slotFromPosition")
	EquipmentSlot invpeek$slotFromPosition(Vec3d hitPos);
}
