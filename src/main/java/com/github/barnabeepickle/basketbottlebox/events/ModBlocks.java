package com.github.barnabeepickle.basketbottlebox.events;

import com.github.barnabeepickle.basketbottlebox.content.blocks.RedPresent;
import com.github.barnabeepickle.basketbottlebox.content.blocks.base.PresentBlock;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.basketbottlebox.utils.RegisteryUtil.registerEntry;

public class ModBlocks {
    public static PresentBlock RED_PRESENT = new RedPresent();

    @SubscribeEvent
    public static void registerBlocksEvent(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockEvent = event.getRegistry();

        // Register blocks here
        registerEntry(blockEvent, RED_PRESENT, RED_PRESENT.getName());

    }
}
