package com.virbound.magmania;

import com.virbound.magmania.items.MagmaniaItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Magmania implements ModInitializer {
    public static final String MOD_ID = "magmania";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Magmania Group
    public static final RegistryKey<ItemGroup> MAGMANIA_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Magmania.MOD_ID, "item_group"));
    public static final ItemGroup MAGMANIA = FabricItemGroup.builder()
            .icon(() -> new ItemStack(MagmaniaItems.MAGMA_COLLECTOR))
            .displayName(Text.translatable("itemGroup.magmania"))
            .build();

    @Override
    public void onInitialize() {

        // Magmania items init
        MagmaniaItems.initialize();
        // Magmania blocks init
        MagmaniaBlocks.initialize();

        // Register Magmania group
        Registry.register(Registries.ITEM_GROUP, MAGMANIA_KEY, MAGMANIA);
        ItemGroupEvents.modifyEntriesEvent(MAGMANIA_KEY).register(itemGroup -> {
            itemGroup.add(MagmaniaItems.MAGMA_COLLECTOR);
            itemGroup.add(MagmaniaItems.MAGMA_COLLECTOR_FILLED);
            itemGroup.add(MagmaniaBlocks.MAGMATIC_STONE.asItem());
            itemGroup.add(MagmaniaBlocks.MAGMA_EMPTY.asItem());
            // ...
        });

    }
}
