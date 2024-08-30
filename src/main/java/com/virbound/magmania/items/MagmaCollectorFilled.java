package com.virbound.magmania.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagmaCollectorFilled extends Item {
    public MagmaCollectorFilled(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("itemTooltip.magmania.magma_collector_filled").formatted(Formatting.GRAY));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {

            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            World world = context.getWorld();

            BlockState state = context.getWorld().getBlockState(positionClicked);

            if(isMagmaBlock(state) && player.isHolding(MagmaniaItems.MAGMA_COLLECTOR_FILLED)) {
                ItemStack mainHandItemStack = player.getStackInHand(Hand.MAIN_HAND);
                ItemStack offHandItemStack = player.getStackInHand(Hand.OFF_HAND);

                if (mainHandItemStack.getItem() == MagmaniaItems.MAGMA_COLLECTOR_FILLED) {
                    mainHandItemStack.setCount(mainHandItemStack.getCount() - 1);
                } else if (offHandItemStack.getItem() == MagmaniaItems.MAGMA_COLLECTOR_FILLED) {
                    offHandItemStack.setCount(offHandItemStack.getCount() - 1);
                }
                player.giveItemStack(MagmaniaItems.MAGMA_COLLECTOR.getDefaultStack());
                world.playSound(null, positionClicked, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 1f, 1.5f);
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
