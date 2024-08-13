package com.virbound.magmania.items;

import com.virbound.magmania.Magmania;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class MagmaniaItems {
    public static final Item MAGMA_COLLECTOR = register(
            new MagmaCollector(new Item.Settings().maxCount(64).fireproof().rarity(Rarity.UNCOMMON)),
            "magma_collector");
    public static final Item MAGMA_COLLECTOR_FILLED = register(
            new Item(new Item.Settings().maxCount(64).fireproof().rarity(Rarity.RARE)),
            "magma_collector_filled");



    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Magmania.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static void initialize() {
    }
}
