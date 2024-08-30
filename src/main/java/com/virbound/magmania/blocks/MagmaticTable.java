package com.virbound.magmania.blocks;

import com.virbound.magmania.items.MagmaniaItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MagmaticTable extends BlockWithEntity implements BlockEntityProvider {
    // Get shape of block like ENCHANTING_TABLE
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);
    // Block property and it's appending to block
    public static final IntProperty FILLED = IntProperty.of("filled", 0, 5);
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }
    // Set settings and default state
    public MagmaticTable(FabricBlockSettings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FILLED, 0));
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    // Set shape of block with defined above
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MagmaticTableBlockEntity(pos, state);
    }

    // onUse to provide functionality
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            // Skip if the player isn't allowed to modify the world.
            return ActionResult.PASS;
        } else {
            // Get the current value of the FILLED property
            int filled = state.get(FILLED);
            if (filled < 5) {
                player.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_FALL, 0.5f, 1f);
                world.setBlockState(pos, state.with(FILLED, state.get(FILLED)+1));
            } else if (filled == 5) {
                player.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, 0.5f, 1f);
                world.setBlockState(pos, state.with(FILLED, 0));
            } else {
                world.setBlockState(pos, state.with(FILLED, 0));
            }

            return ActionResult.SUCCESS;
        }
    }
}
