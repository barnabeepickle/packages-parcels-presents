package com.github.barnabeepickle.basketbottlebox.events;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.basketbottlebox.content.ModCreativeTabs.primaryCreativeTab;
import static com.github.barnabeepickle.basketbottlebox.events.ModBlocks.RED_PRESENT;
import static com.github.barnabeepickle.basketbottlebox.utils.RegisteryUtil.registerEntry;

public class ModItems {
    public static ItemBlock ITEM_RED_PRESENT = new ItemBlock(RED_PRESENT);

    public ModItems() {
        ITEM_RED_PRESENT.setCreativeTab(primaryCreativeTab);
    }

    public static void registerItemsEvent(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> itemEvent = event.getRegistry();

        // Register items here

        // Register block items here
        registerEntry(itemEvent, ITEM_RED_PRESENT, RED_PRESENT.getName());
    }
}
