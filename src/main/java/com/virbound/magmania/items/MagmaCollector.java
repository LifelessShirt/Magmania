package com.virbound.magmania.items;

import com.virbound.magmania.Magmania;
import com.virbound.magmania.MagmaniaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
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

                if (player.getStackInHand(Hand.MAIN_HAND).getItem() == MagmaniaItems.MAGMA_COLLECTOR) {
                    player.getStackInHand(Hand.MAIN_HAND).setCount(player.getStackInHand(Hand.MAIN_HAND).getCount() - 1);
                } else if (player.getStackInHand(Hand.OFF_HAND).getItem() == MagmaniaItems.MAGMA_COLLECTOR) {
                    player.getStackInHand(Hand.OFF_HAND).setCount(player.getStackInHand(Hand.OFF_HAND).getCount() - 1);
                }
                player.giveItemStack(MagmaniaItems.MAGMA_COLLECTOR_FILLED.getDefaultStack());
                world.playSound(null, positionClicked, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1f, 1.5f);
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    private boolean isMagmaBlock(BlockState state) {
        return state.isOf(Blocks.MAGMA_BLOCK);
    }
}
