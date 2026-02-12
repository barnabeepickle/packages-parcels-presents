package com.github.barnabeepickle.ppnp;

import com.github.barnabeepickle.ppnp.client.ModClientHandler;
import com.github.barnabeepickle.ppnp.events.ModBlocks;
import com.github.barnabeepickle.ppnp.events.ModItems;
import com.github.barnabeepickle.ppnp.events.ModTileEntities;
import com.github.barnabeepickle.ppnp.networking.ModGUIHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, name = Tags.MOD_NAME, version = Tags.VERSION)
@Mod.EventBusSubscriber
public class bbbMod {
    @Mod.Instance(Tags.MODID)
    public static bbbMod INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.EventHandler
    public void preLoadEvent(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new ModGUIHandler());

        MinecraftForge.EVENT_BUS.register(ModBlocks.class);
        MinecraftForge.EVENT_BUS.register(ModTileEntities.class);
        MinecraftForge.EVENT_BUS.register(ModItems.class);

        MinecraftForge.EVENT_BUS.register(ModClientHandler.class);
    }
}
