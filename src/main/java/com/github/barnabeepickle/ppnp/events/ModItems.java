package com.github.barnabeepickle.ppnp.events;

import com.github.barnabeepickle.ppnp.content.items.CardboardItem;
import com.github.barnabeepickle.ppnp.content.items.ModBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.ppnp.events.ModBlocks.RED_PRESENT;
import static com.github.barnabeepickle.ppnp.utils.RegisteryUtil.registerEntry;

public class ModItems {
    public static CardboardItem CARDBOARD = new CardboardItem();

    public static ItemBlock ITEM_RED_PRESENT = new ModBlockItem(RED_PRESENT);

    @SubscribeEvent
    public static void registerItemsEvent(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> itemEvent = event.getRegistry();

        // Register items here
        registerEntry(itemEvent, CARDBOARD, CARDBOARD.getName());

        // Register block items here
        registerEntry(itemEvent, ITEM_RED_PRESENT, RED_PRESENT.getName());
    }
}
