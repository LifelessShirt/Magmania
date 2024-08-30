package com.virbound.magmania.client;

import com.virbound.magmania.blocks.MagmaniaBlockEntities;
import com.virbound.magmania.client.blocks.MagmaticTableBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MagmaniaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(MagmaniaBlockEntities.MAGMATIC_TABLE_BLOCK_ENTITY, MagmaticTableBlockEntityRenderer::new);
    }
}
