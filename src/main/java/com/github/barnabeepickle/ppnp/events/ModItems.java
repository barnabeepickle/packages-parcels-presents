package com.github.barnabeepickle.ppnp.events;

import com.github.barnabeepickle.ppnp.content.items.CardboardBoxItem;
import com.github.barnabeepickle.ppnp.content.items.CardboardItem;
import com.github.barnabeepickle.ppnp.content.items.ModBlockItem;
import com.github.barnabeepickle.ppnp.content.items.base.WrappingPaperItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.ppnp.events.ModBlocks.RED_PRESENT;
import static com.github.barnabeepickle.ppnp.utils.RegisteryUtil.registerEntry;

public class ModItems {
    public static CardboardItem CARDBOARD = new CardboardItem();
    public static CardboardBoxItem CARDBOARD_BOX = new CardboardBoxItem();
    public static WrappingPaperItem RED_WRAPPING_PAPER = new WrappingPaperItem("red");

    public static ItemBlock ITEM_RED_PRESENT = new ModBlockItem(RED_PRESENT);

    @SubscribeEvent
    public static void registerItemsEvent(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> itemEvent = event.getRegistry();

        // Register items here
        registerEntry(itemEvent, CARDBOARD, CARDBOARD.getName());
        registerEntry(itemEvent, CARDBOARD_BOX, CARDBOARD_BOX.getName());
        registerEntry(itemEvent, RED_WRAPPING_PAPER, RED_WRAPPING_PAPER.getName());

        // Register block items here
        registerEntry(itemEvent, ITEM_RED_PRESENT, RED_PRESENT.getName());
    }
}
