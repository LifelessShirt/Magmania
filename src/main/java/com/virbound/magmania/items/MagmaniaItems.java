package com.virbound.magmania.items;

import com.virbound.magmania.Magmania;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class MagmaniaItems {
    // Magma collectors must have a 5-level durability, each level contains 1 bucket of [lava || water].
    // Need to rename this to "Liquid collector" or change purpose of this item.
    public static final Item MAGMA_COLLECTOR = register(
            new MagmaCollector(new Item.Settings().maxCount(64).fireproof().rarity(Rarity.UNCOMMON)),
            "magma_collector");
    public static final Item MAGMA_COLLECTOR_FILLED = register(
            new MagmaCollectorFilled(new Item.Settings().maxCount(64).fireproof().rarity(Rarity.RARE)),
            "magma_collector_filled");
    // Magma ore can be obtained with combining water (or some cooling liquid) with magma block,
    // then cooled magma block need to be mined.
    // ________________________
    // IDK it need to be or not
    //           \/
    public static final Item MAGMA_ORB = register(
            new MagmaOrb(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.RARE)),
            "magma_orb");



    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Magmania.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static void initialize() {
    }
}
