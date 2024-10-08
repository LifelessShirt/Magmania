package com.virbound.magmania.items;

import com.virbound.magmania.blocks.MagmaniaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagmaCollector extends Item {
    public MagmaCollector(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {

            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            World world = context.getWorld();

            BlockState state = context.getWorld().getBlockState(positionClicked);
            BlockState newState = MagmaniaBlocks.MAGMA_EMPTY.getDefaultState();

            if(isMagmaBlock(state) && player.isHolding(MagmaniaItems.MAGMA_COLLECTOR)) {
                Block.replace(state, newState, world, positionClicked, 0, 512);

                ItemStack mainHandItemStack = player.getStackInHand(Hand.MAIN_HAND);
                ItemStack offHandItemStack = player.getStackInHand(Hand.OFF_HAND);

                if (mainHandItemStack.getItem() == MagmaniaItems.MAGMA_COLLECTOR) {
                    mainHandItemStack.setCount(mainHandItemStack.getCount() - 1);
                } else if (offHandItemStack.getItem() == MagmaniaItems.MAGMA_COLLECTOR) {
                    offHandItemStack.setCount(offHandItemStack.getCount() - 1);
                }
                player.giveItemStack(MagmaniaItems.MAGMA_COLLECTOR_FILLED.getDefaultStack());
                world.playSound(null, positionClicked, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1f, 1.5f);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.PASS;
            }
        } else {
            return ActionResult.PASS;
        }
    }

    private boolean isMagmaBlock(BlockState state) {
        return state.isOf(Blocks.MAGMA_BLOCK);
    }
}
