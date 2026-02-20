package com.github.barnabeepickle.ppnp.client;

import com.github.barnabeepickle.ppnp.events.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

import static com.github.barnabeepickle.ppnp.events.ModBlocks.*;

public class ModClientHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent evt) {
        registerItemModels();
        registerBlockItemModels();
    }

    public static void registerItemModels() {
        // register item models here
        registerItem(ModItems.CARDBOARD);
        registerItem(ModItems.CARDBOARD_BOX);
        registerItem(ModItems.RED_WRAPPING_PAPER);
    }

    // Item model registration utilities
    private static void registerItem(Item item)
    {
        registerItem(item, 0);
    }

    @SuppressWarnings("SameParameterValue")
    private static void registerItem(Item item, int meta)
    {
        String name = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(name, "inventory"));
    }

    public static void registerBlockItemModels() {
        // register block item models here
        registerBlockItem(RED_PRESENT);
        registerBlockItem(ORANGE_PRESENT);
        registerBlockItem(YELLOW_PRESENT);
        registerBlockItem(LIME_PRESENT);
        registerBlockItem(PURPLE_PRESENT);
        registerBlockItem(BLUE_PRESENT);
    }

    // Block Item model registration utility
    private static void registerBlockItem(Block block)
    {
        String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString();
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(name, "inventory"));
    }
}
