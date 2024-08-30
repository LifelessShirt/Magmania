package com.virbound.magmania.blocks;

import com.virbound.magmania.Magmania;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class MagmaniaBlocks {
    public static final Block MAGMATIC_STONE = register(
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).luminance(15).strength(0.2f, 0.5f).requiresTool()),
            "magmatic_stone",true
    );
    public static final Block MAGMA_EMPTY = register(
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).luminance(1).strength(0.1f, 0.2f).requiresTool()),
            "magma_block_empty",true
    );
    public static final Block MAGMATIC_TABLE = register(
            new MagmaticTable(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).strength(0.3f, 1f).requiresTool()),
            "magmatic_table",true
    );

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(Magmania.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {}
}
