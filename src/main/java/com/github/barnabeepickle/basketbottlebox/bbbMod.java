package com.github.barnabeepickle.basketbottlebox;

import com.github.barnabeepickle.basketbottlebox.client.ModClientHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

        MinecraftForge.EVENT_BUS.register(ModClientHandler.class);
    }
}
