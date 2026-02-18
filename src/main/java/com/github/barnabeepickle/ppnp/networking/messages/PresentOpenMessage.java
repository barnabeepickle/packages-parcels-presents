package com.github.barnabeepickle.ppnp.networking.messages;


import com.github.barnabeepickle.ppnp.content.blocks.entity.PresentTileEntity;
import com.github.barnabeepickle.ppnp.ppnpMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PresentOpenMessage implements IMessage {
    public PresentOpenMessage() {

    }

    private BlockPos blockPos;

    public PresentOpenMessage(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.blockPos.getX());
        buf.writeInt(this.blockPos.getY());
        buf.writeInt(this.blockPos.getZ());
    }

    public static class Handler implements IMessageHandler<PresentOpenMessage, IMessage> {
        @Override
        public IMessage onMessage(PresentOpenMessage message, MessageContext ctx) {
            //CharmMod.LOGGER.info("Server recieved packet from client");
            // This is the player the packet whose client sent the packet
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            WorldServer serverWorld = serverPlayer.getServerWorld();
            BlockPos presentBlockPos = message.blockPos;
            ppnpMod.LOGGER.info("stage 0");
            if (serverWorld.getTileEntity(presentBlockPos) instanceof PresentTileEntity tile) {
                ppnpMod.LOGGER.info("stage 1 | {}", tile.getUserPlayer() == null);
                if (tile.getUserPlayer() != null) {
                    ppnpMod.LOGGER.info("stage 2");
                    if (tile.getUserPlayer().getGameProfile() == serverPlayer.getGameProfile()) {
                        ppnpMod.LOGGER.info("stage 3");
                        // Execute the action on the main server thread by adding it as a scheduled task
                        serverWorld.addScheduledTask(() -> {
                            ppnpMod.LOGGER.info("stage 4");
                            tile.openPresent(serverWorld, presentBlockPos, serverPlayer);
                        });
                    }
                }
            }
            // No response packet
            return null;
        }
    }
}