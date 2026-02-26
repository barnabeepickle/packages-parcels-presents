package com.github.barnabeepickle.ppnp.events;

import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlock;
import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlockPrefix;
import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.ppnp.utils.RegisteryUtil.registerEntry;

public class ModBlocks {
    public static PresentBlock RED_PRESENT = new PresentBlockPrefix(EnumDyeColor.RED);
    public static PresentBlock ORANGE_PRESENT = new PresentBlockPrefix(EnumDyeColor.ORANGE);
    public static PresentBlock YELLOW_PRESENT = new PresentBlockPrefix(EnumDyeColor.YELLOW);
    public static PresentBlock LIME_PRESENT = new PresentBlockPrefix(EnumDyeColor.LIME);
    public static PresentBlock BLUE_PRESENT = new PresentBlockPrefix(EnumDyeColor.BLUE);
    public static PresentBlock PURPLE_PRESENT = new PresentBlockPrefix(EnumDyeColor.PURPLE);

    @SubscribeEvent
    public static void registerBlocksEvent(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockEvent = event.getRegistry();

        // Register blocks here
        registerEntry(blockEvent, RED_PRESENT, RED_PRESENT.getName());
        registerEntry(blockEvent, ORANGE_PRESENT, ORANGE_PRESENT.getName());
        registerEntry(blockEvent, YELLOW_PRESENT, YELLOW_PRESENT.getName());
        registerEntry(blockEvent, LIME_PRESENT, LIME_PRESENT.getName());
        registerEntry(blockEvent, BLUE_PRESENT, BLUE_PRESENT.getName());
        registerEntry(blockEvent, PURPLE_PRESENT, PURPLE_PRESENT.getName());

    }
}
