package com.github.barnabeepickle.ppnp.networking;

import com.github.barnabeepickle.ppnp.Tags;
import com.github.barnabeepickle.ppnp.networking.messages.PresentOpenMessage;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

// https://docs.minecraftforge.net/en/1.12.x/networking/simpleimpl/

public class NetworkHandler {
    private static int id = 0;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MODID);

    public static void initMessages() {
        NetworkHandler.INSTANCE.registerMessage(PresentOpenMessage.Handler.class, PresentOpenMessage.class, id, Side.CLIENT);
        NetworkHandler.INSTANCE.registerMessage(PresentOpenMessage.Handler.class, PresentOpenMessage.class, id, Side.SERVER);
        id++;

    }
}
