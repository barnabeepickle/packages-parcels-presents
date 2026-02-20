package com.github.barnabeepickle.ppnp.events;

import com.github.barnabeepickle.ppnp.content.blocks.PurplePresent;
import com.github.barnabeepickle.ppnp.content.blocks.RedPresent;
import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlock;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.ppnp.utils.RegisteryUtil.registerEntry;

public class ModBlocks {
    public static PresentBlock RED_PRESENT = new RedPresent();
    public static PresentBlock PURPLE_PRESENT = new PurplePresent();

    @SubscribeEvent
    public static void registerBlocksEvent(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockEvent = event.getRegistry();

        // Register blocks here
        registerEntry(blockEvent, RED_PRESENT, RED_PRESENT.getName());
        registerEntry(blockEvent, PURPLE_PRESENT, PURPLE_PRESENT.getName());

    }
}
