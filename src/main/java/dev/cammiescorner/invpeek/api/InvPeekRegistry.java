package dev.cammiescorner.invpeek.api;

import com.mojang.datafixers.util.Function4;
import com.mojang.datafixers.util.Function5;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class InvPeekRegistry {
	public static final Map<BlockEntityType<?>, Function5<PlayerEntity, World, BlockHitResult, BlockState, BlockEntity, ItemStack>> BLOCK_ENTITIES = new HashMap<>();
	public static final Map<EntityType<?>, Function4<PlayerEntity, World, EntityHitResult, Entity, ItemStack>> ENTITIES = new HashMap<>();

	public <B extends BlockEntity> void registerBlockEntity(BlockEntityType<B> type, Function5<PlayerEntity, World, BlockHitResult, BlockState, B, ItemStack> func) {
		BLOCK_ENTITIES.put(type, (Function5<PlayerEntity, World, BlockHitResult, BlockState, BlockEntity, ItemStack>) func);
	}

	public <E extends Entity> void registerEntity(EntityType<E> type, Function4<PlayerEntity, World, EntityHitResult, E, ItemStack> func) {
		ENTITIES.put(type, (Function4<PlayerEntity, World, EntityHitResult, Entity, ItemStack>) func);
	}
}
