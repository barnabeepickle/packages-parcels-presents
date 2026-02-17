package com.github.barnabeepickle.ppnp.networking.messages;


import com.github.barnabeepickle.ppnp.content.blocks.entity.PresentTileEntity;
import com.github.barnabeepickle.ppnp.ppnpMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PresentMessage implements IMessage {
    public PresentMessage() {

    }

    private BlockPos blockPos;
    private boolean anonymous;

    public PresentMessage(BlockPos blockPos, boolean anonymous) {
        this.blockPos = blockPos;
        this.anonymous = anonymous;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.anonymous = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.blockPos.getX());
        buf.writeInt(this.blockPos.getY());
        buf.writeInt(this.blockPos.getZ());
        buf.writeBoolean(this.anonymous);
    }

    public static class Handler implements IMessageHandler<PresentMessage, IMessage> {
        @Override
        public IMessage onMessage(PresentMessage message, MessageContext ctx) {
            //CharmMod.LOGGER.info("Server recieved packet from client");
            // This is the player the packet whose client sent the packet
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            BlockPos presentBlockPos = message.blockPos;
            boolean anonymous = message.anonymous;
            if (serverPlayer.getServerWorld().getTileEntity(presentBlockPos) instanceof PresentTileEntity tile) {
                // Execute the action on the main server thread by adding it as a scheduled task
                serverPlayer.getServerWorld().addScheduledTask(() -> {
                    if (anonymous) {
                        tile.makeAnonymous();
                    } else {
                        tile.makeNotAnonymous();
                    }
                    ppnpMod.LOGGER.info("anon s | {}", tile.isAnonymous());
                });
            }
            // No response packet
            return null;
        }
    }
}