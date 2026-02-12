package com.github.barnabeepickle.ppnp.events;

import com.github.barnabeepickle.ppnp.content.blocks.entity.PresentTileEntity;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.barnabeepickle.ppnp.utils.RegisteryUtil.registerTileEntity;

public class ModTileEntities {
    @SubscribeEvent
    public static void registerTileEntitiesEvent(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockEvent = event.getRegistry();

        // Register tile entities here
        registerTileEntity(PresentTileEntity.class, "present_tile_entity");
    }
}
