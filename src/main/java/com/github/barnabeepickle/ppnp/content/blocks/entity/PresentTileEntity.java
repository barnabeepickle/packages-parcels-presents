package com.github.barnabeepickle.ppnp.content.blocks.entity;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IGuiAction;
import com.cleanroommc.modularui.drawable.GuiTextures;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.drawable.text.RichText;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.BoolValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.RichTextWidget;
import com.cleanroommc.modularui.widgets.ToggleButton;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.cleanroommc.modularui.widgets.slot.SlotGroup;
import com.github.barnabeepickle.ppnp.Tags;
import com.github.barnabeepickle.ppnp.networking.NetworkHandler;
import com.github.barnabeepickle.ppnp.networking.messages.PresentMessage;
import com.github.barnabeepickle.ppnp.ppnpMod;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class PresentTileEntity extends TileEntity implements IGuiHolder<PosGuiData> {
    private static final int SLOT_COUNT = 18;
    private final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_COUNT);

    private boolean creativePlayerDestroyed;

    private final BoolValue anonymous = new BoolValue(false);
    private String targetPlayer = "";
    private String ownerPlayer = "";

    public PresentTileEntity() {

    }

    public void toggleAnonymous() {
        this.anonymous.setValue(!this.anonymous.getBoolValue());
    }

    public void makeAnonymous() {
        this.anonymous.setValue(true);
    }

    public void makeNotAnonymous() {
        this.anonymous.setValue(false);
    }

    public boolean isAnonymous() {
        return this.anonymous.getBoolValue();
    }


    public boolean hasTargetPlayer() {
        return !Objects.equals(targetPlayer, "");
    }

    public boolean hasOwnerPlayer() {
        return !Objects.equals(ownerPlayer, "");
    }

    public boolean isPlayerTarget(EntityPlayer player) {
        if (this.hasTargetPlayer()) {
            return Objects.equals(player.getName(), targetPlayer);
        }
        return false;
    }

    public boolean isPlayerOwner(EntityPlayer player) {
        if (this.hasOwnerPlayer()) {
            return Objects.equals(player.getName(), ownerPlayer);
        }
        return false;
    }

    public String getTargetPlayer() {
        return this.targetPlayer;
    }

    public String getOwnerPlayer() {
        return this.ownerPlayer;
    }


    public void setTargetPlayer(String name) {
        this.targetPlayer = name;
    }

    public void setTargetPlayer(EntityPlayer player) {
        this.setOwnerPlayer(player.getName());
    }


    public void setTargetPlayer(GameProfile profile) {
        this.setOwnerPlayer(profile.getName());
    }

    public void setOwnerPlayer(String name) {
        this.ownerPlayer = name;
    }

    public void setOwnerPlayer(EntityPlayer player) {
        this.setOwnerPlayer(player.getName());
    }

    public void setOwnerPlayer(GameProfile profile) {
        this.setOwnerPlayer(profile.getName());
    }

    public int getSizeInventory() {
        return SLOT_COUNT;
    }

    public boolean isEmpty() {
        for (int i = 0; i < SLOT_COUNT; i++) {
            if (itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean didCreativePlayerDestroyed() {
        return this.creativePlayerDestroyed;
    }

    public void setCreativePlayerDestroyed(boolean destroy) {
        this.creativePlayerDestroyed = destroy;
    }

    public boolean shouldDrop() {
        return !this.didCreativePlayerDestroyed() || !this.isEmpty();
    }

    @Override
    public @Nonnull NBTTagCompound writeToNBT(@Nonnull NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        return this.saveToNbt(nbt);
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.loadFromNbt(nbt);

    }

    public NBTTagCompound saveToNbt(NBTTagCompound nbt) {
        nbt.setTag("present_inv", this.itemHandler.serializeNBT());
        nbt.setString("target_player", this.targetPlayer);
        nbt.setString("owner_player", this.ownerPlayer);
        nbt.setBoolean("anonymous", this.anonymous.getBoolValue());

        return nbt;
    }

    public void loadFromNbt(NBTTagCompound nbt) {
        this.itemHandler.deserializeNBT(nbt.getCompoundTag("present_inv"));
        this.targetPlayer = nbt.getString("target_player");
        this.ownerPlayer = nbt.getString("owner_player");
        this.anonymous.setBoolValue(nbt.getBoolean("anonymous"));
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket(){
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(@Nonnull NetworkManager net, SPacketUpdateTileEntity pkt){
        NBTTagCompound nbt = pkt.getNbtCompound();
        this.readFromNBT(nbt);
    }

    // ModularUI stuff below

    UITexture EYE = UITexture.builder()
            .location(Tags.MODID, "textures/gui/eye.png")
            .imageSize(12, 24)
            .iconColorType()
            .name("eye")
            .build();

    @Override
    @SideOnly(Side.CLIENT)
    public ModularScreen createScreen(PosGuiData guiData, ModularPanel mainPanel) {
        return new ModularScreen(Tags.MODID, mainPanel);
    }

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        World world = guiData.getWorld();
        BlockPos blockPos = guiData.getBlockPos();

        SlotGroup presentSlots = new SlotGroup("present_slot_group", 9, true);
        syncManager.registerSlotGroup(presentSlots);

        ModularPanel panel = ModularPanel.defaultPanel("present_gui");

        // add the name to the top of the UI
        panel.child(new RichTextWidget()
                .addLine(IKey.lang("container.present"))
                .size(162, 8)
                .pos(7, 6)
        );

        // add the present's inventory
        for (int i = 0; i < SLOT_COUNT; i++) {
            int x = i % 9;
            int y = i / 9;
            panel.child(new ItemSlot().slot(
                    new ModularSlot(this.itemHandler, i)
                            .slotGroup(presentSlots)
            ).pos((x * 18) + 7, (y * 18) + 16));
        }

        // owner player display text
        //ppnpMod.LOGGER.info(IKey.lang("container.present.owner", this.getOwnerPlayer()) + " | anyonymous: " + this.isAnonymous());
        RichTextWidget ownerRichText = new RichTextWidget()
                .size(162, 8)
                .pos(7, 56);
        // this try statement handles not being able to get the UUID this is often
        // because your in an offline instance of the game (like the dev environment)
        try {
            //noinspection DataFlowIssue
            ownerRichText.addTooltipLine(FMLCommonHandler
                    .instance()
                    .getMinecraftServerInstance()
                    .getPlayerList()
                    .getPlayerByUsername(this.getOwnerPlayer()).toString()
            );
        } catch (NullPointerException ignored) { }
        // adds the text dependent on if this is an anonymous gift
        ownerRichText.textBuilder(this::switchText);
        panel.child(ownerRichText);

        // target player display text
        //ppnpMod.LOGGER.info(IKey.lang("container.present.target", this.getTargetPlayer()));
        RichTextWidget targetRichText = new RichTextWidget()
                .addLine(IKey.lang("container.present.target", this.getTargetPlayer()))
                .size(162, 8)
                .pos(7, 68);
        // same as above
        try {
            //noinspection DataFlowIssue
            targetRichText.addTooltipLine(FMLCommonHandler
                    .instance()
                    .getMinecraftServerInstance()
                    .getPlayerList()
                    .getPlayerByUsername(this.getTargetPlayer()).toString()
            );
        } catch (NullPointerException ignored) { } finally {
            panel.child(targetRichText);
        }

        // toggle button for changing if the present is anonymous or not (disabled for non-owner players)
        ToggleButton buttonAnonymous = new ToggleButton()
                .pos(130, 54)
                .size(13, 12);
        if (this.hasOwnerPlayer()) {
            if (this.isPlayerOwner(guiData.getPlayer())) {
                buttonAnonymous.setEnabled(true);
                //ppnpMod.LOGGER.info("current user player is owner");
            } else {
                buttonAnonymous.disabled();
            }
        }
        buttonAnonymous.overlay(EYE.getSubArea(0.0F, 0.5F, 1.0F, 1.0F));
        buttonAnonymous.hoverOverlay(EYE.getSubArea(0.0F, 0.0F, 1.0F, 0.5F));
        // the button is pressed we toggle the anonymous boolean and mark the text as dirt so it gets updated
        buttonAnonymous.listenGuiAction((IGuiAction.MousePressed) mouseButton -> {
            if (buttonAnonymous.isBelowMouse()) {
                //ppnpMod.LOGGER.info("Anonymous Toggle Button Pressed, action");
                toggleAnonymous();
                if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
                    NetworkHandler.INSTANCE.sendToServer(new PresentMessage(blockPos, anonymous.getBoolValue()));
                }
                ppnpMod.LOGGER.info("anon c | {}", this.isAnonymous());
                ownerRichText.markDirty();
                return true;
            }
            return false;
        });
        buttonAnonymous.invertSelected(this.isAnonymous());
        panel.child(buttonAnonymous);

        // add the player inventory
        panel.bindPlayerInventory();

        // listeners for various actions`
        // client & server listeners
        syncManager.addOpenListener(entityPlayer -> {
            ppnpMod.LOGGER.info("trying to invert button");
            if (this.isAnonymous()) {
                buttonAnonymous.setState(0, true);
            } else {
                buttonAnonymous.setState(1, true);
            }
        });
        // server only listeners
        if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
            syncManager.addCloseListener(entityPlayer -> {
                IBlockState blockstate = world.getBlockState(blockPos);
                world.notifyBlockUpdate(blockPos, blockstate, blockstate, 2);
            });
        }

        return panel;
    }

    private void switchText(RichText richText) {
        if (this.isAnonymous()) {
            richText.addLine(IKey.lang("container.present.owner", this.getOwnerPlayer()));
        } else {
            richText.addLine(IKey.lang("container.present.owner", IKey.lang("container.present.owner.anonymous")));
        }
    }
}
