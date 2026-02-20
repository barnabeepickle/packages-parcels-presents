package com.github.barnabeepickle.ppnp.events;

import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlock;
import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlockPrefix;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.ppnp.utils.RegisteryUtil.registerEntry;

public class ModBlocks {
    public static PresentBlock RED_PRESENT = new PresentBlockPrefix("red", MapColor.RED);
    public static PresentBlock PURPLE_PRESENT = new PresentBlockPrefix("purple", MapColor.PURPLE);

    @SubscribeEvent
    public static void registerBlocksEvent(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockEvent = event.getRegistry();

        // Register blocks here
        registerEntry(blockEvent, RED_PRESENT, RED_PRESENT.getName());
        registerEntry(blockEvent, PURPLE_PRESENT, PURPLE_PRESENT.getName());

    }
}
