package com.virbound.magmania.blocks;

import com.virbound.magmania.items.MagmaniaItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.virbound.magmania.blocks.MagmaticTable.FILLED;

public class MagmaticTableBlockEntity extends BlockEntity {

    public MagmaticTableBlockEntity(BlockPos pos, BlockState state) {
        super(MagmaniaBlockEntities.MAGMATIC_TABLE_BLOCK_ENTITY, pos, state);
    }
    private static final Random RANDOM = Random.create();

    public ItemStack getRenderStack() {
        if (!this.isRemoved()) {
            if (world.getBlockState(pos).get(FILLED).intValue() <= 4) {
                return MagmaniaItems.MAGMA_COLLECTOR.getDefaultStack();
            } else {
                return MagmaniaItems.MAGMA_ORB.getDefaultStack();
            }
        } else {
            return MagmaniaItems.MAGMA_ORB.getDefaultStack();
        }
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    public Long getTime() {
        return world.getTime();
    }

    public World getWorld() {
        return world;
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}

