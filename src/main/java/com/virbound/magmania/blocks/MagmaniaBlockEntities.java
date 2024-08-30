package com.virbound.magmania.blocks;

import com.virbound.magmania.Magmania;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class MagmaniaBlockEntities {
    public static final BlockEntityType<MagmaticTableBlockEntity> MAGMATIC_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Magmania.MOD_ID, "magmatic_table_be"),
                    FabricBlockEntityTypeBuilder.create(MagmaticTableBlockEntity::new, MagmaniaBlocks.MAGMATIC_TABLE).build());

    public static void initialize() {}
}
