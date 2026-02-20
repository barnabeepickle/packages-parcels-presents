package com.github.barnabeepickle.ppnp.content.blocks.entity;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IGuiAction;
import com.cleanroommc.modularui.drawable.text.RichText;
import com.cleanroommc.modularui.drawable.text.StringKey;
import com.cleanroommc.modularui.drawable.text.StyledText;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.BooleanSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.StringSyncValue;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.RichTextWidget;
import com.cleanroommc.modularui.widgets.ToggleButton;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.cleanroommc.modularui.widgets.slot.SlotGroup;
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget;
import com.github.barnabeepickle.ppnp.Tags;
import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlock;
import com.github.barnabeepickle.ppnp.networking.NetworkHandler;
import com.github.barnabeepickle.ppnp.networking.messages.PresentOpenMessage;
import com.github.barnabeepickle.ppnp.ui.AssetsUI;
import com.github.barnabeepickle.ppnp.utils.ChristmasUtil;
import com.github.barnabeepickle.ppnp.utils.ColorUtil;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Predicate;

public class PresentTileEntity extends TileEntity implements IGuiHolder<PosGuiData> {
    private static final int SLOT_COUNT = 18;
    private final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_COUNT);

    private boolean creativePlayerDestroyed;

    private boolean anonymous = true;

    private String targetPlayer = "";
    private String ownerPlayer = "";

    @Nullable
    private EntityPlayer userPlayer = null;

    private boolean beingOpened = false;

    public PresentTileEntity() {

    }


    public @Nullable EntityPlayer getUserPlayer() {
        return userPlayer;
    }


    @SuppressWarnings("unused")
    public void toggleAnonymous() {
        this.anonymous = !this.isAnonymous();
    }

    @SuppressWarnings("unused")
    public void makeAnonymous() {
        this.anonymous = true;
    }

    @SuppressWarnings("unused")
    public void makeNotAnonymous() {
        this.anonymous = false;
    }

    public boolean isAnonymous() {
        return this.anonymous;
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


    @SuppressWarnings("unused")
    public void setTargetPlayer(String name) {
        this.targetPlayer = name;
    }

    @SuppressWarnings("unused")
    public void setTargetPlayer(EntityPlayer player) {
        this.setOwnerPlayer(player.getName());
    }

    @SuppressWarnings("unused")
    public void setTargetPlayer(GameProfile profile) {
        this.setOwnerPlayer(profile.getName());
    }


    public void setOwnerPlayer(String name) {
        this.ownerPlayer = name;
    }

    @SuppressWarnings("unused")
    public void setOwnerPlayer(EntityPlayer player) {
        this.setOwnerPlayer(player.getName());
    }

    public void setOwnerPlayer(GameProfile profile) {
        this.setOwnerPlayer(profile.getName());
    }


    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    public void setCreativePlayerDestroyed(boolean destroy) {
        this.creativePlayerDestroyed = destroy;
    }

    public boolean shouldDrop() {
        if (this.beingOpened) {
            return false;
        }
        return !this.didCreativePlayerDestroyed() && !this.isEmpty();
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
        nbt.setBoolean("anonymous", this.isAnonymous());

        return nbt;
    }

    public void loadFromNbt(NBTTagCompound nbt) {
        this.itemHandler.deserializeNBT(nbt.getCompoundTag("present_inv"));
        this.targetPlayer = nbt.getString("target_player");
        this.ownerPlayer = nbt.getString("owner_player");
        this.anonymous = nbt.getBoolean("anonymous");
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

    public void openPresentNetwork(World world, BlockPos blockPos, EntityPlayer target, ModularPanel panel) {
        NetworkHandler.INSTANCE.sendToServer(new PresentOpenMessage(blockPos));
        this.openPresent(world, blockPos, target);
        panel.closeIfOpen();
    }

    public void openPresent(World world, BlockPos blockPos, EntityPlayer target) {
        if (world.isBlockLoaded(blockPos)) {
            this.beingOpened = true;
            for (int i = 0; i < SLOT_COUNT; i++) {
                target.addItemStackToInventory(this.itemHandler.getStackInSlot(i));
                this.itemHandler.extractItem(i, this.itemHandler.getStackInSlot(i).getCount(), false);
            }
            IBlockState blockstate = world.getBlockState(blockPos);
            if (blockstate.getBlock() instanceof PresentBlock block) {
                block.breakBlock(world, blockPos, blockstate);
            }
            world.destroyBlock(blockPos, false);
            if (FMLCommonHandler.instance().getSide().isServer()) {
                world.notifyBlockUpdate(blockPos, blockstate, world.getBlockState(blockPos), 2);
            }
            target.playSound(new SoundEvent(new ResourceLocation("block.end_gateway.spawn")), 0.5F, 0.75F);
        }
    }

    // ModularUI stuff below

    private final BooleanSyncValue anonymousSync = new BooleanSyncValue(() -> !this.anonymous, val -> this.anonymous = !val);

    private final StringSyncValue targetPlayerSync = new StringSyncValue(() -> this.targetPlayer, val -> this.targetPlayer = val);

    @Override
    @SideOnly(Side.CLIENT)
    public ModularScreen createScreen(PosGuiData guiData, ModularPanel mainPanel) {
        return new ModularScreen(Tags.MODID, mainPanel);
    }

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        World world = guiData.getWorld();
        BlockPos blockPos = guiData.getBlockPos();
        Block block = world.getBlockState(blockPos).getBlock();
        EntityPlayer user = guiData.getPlayer();

        boolean userOwner = this.hasOwnerPlayer() && this.isPlayerOwner(user);
        boolean userTarget = !this.hasTargetPlayer() || this.isPlayerTarget(user);

        SlotGroup presentSlotGroup = new SlotGroup("present_slot_group", 9, true);
        syncManager.registerSlotGroup(presentSlotGroup);

        ModularPanel panel = ModularPanel.defaultPanel("present_gui");

        // add the name to the top of the UI
        RichTextWidget containerTitle = new RichTextWidget()
                .size(162, 8)
                .pos(7, 6)
                .addLine(block.getLocalizedName());
        panel.child(containerTitle);

        // add the present's inventory
        for (int i = 0; i < SLOT_COUNT; i++) {
            int x = i % 9;
            int y = i / 9;
            ItemSlot currentSlot = new ItemSlot().slot(
                    new ModularSlot(this.itemHandler, i)
                            .slotGroup(presentSlotGroup)
            ).pos((x * 18) + 7, (y * 18) + 16);
            if (!userOwner) {
                currentSlot.getSlot().accessibility(false, false);
            }
            panel.child(currentSlot);
        }

        // owner player display text
        //ppnpMod.LOGGER.info(IKey.lang("container.present.owner", this.getOwnerPlayer()) + " | anyonymous: " + this.isAnonymous());
        RichTextWidget ownerRichText = new RichTextWidget()
                .size(126, 8)
                .pos(7, 57);
        // this try statement handles not being able to get the UUID this is often
        // because your in an offline instance of the game (like the dev environment)
        try {
            ownerRichText.tooltipBuilder(tooltip -> {
                //noinspection DataFlowIssue
                tooltip.addLine(FMLCommonHandler
                        .instance()
                        .getMinecraftServerInstance()
                        .getPlayerList()
                        .getPlayerByUsername(this.getOwnerPlayer()).getUniqueID().toString()
                );
                tooltip.textColor(ColorUtil.getColor(85, 255, 85));
            });
        } catch (NullPointerException ignored) { }
        // adds the text dependent on if this is an anonymous gift
        ownerRichText.textBuilder(this::switchText);
        panel.child(ownerRichText);

        // target player display text
        //ppnpMod.LOGGER.info(IKey.lang("container.present.target", this.getTargetPlayer()));
        RichTextWidget targetRichText = new RichTextWidget()
                .addLine(IKey.lang("container.present.target", this.getTargetPlayer()))
                .size(126, 8)
                .pos(7, 70);
        // same as above
        try {
            //noinspection DataFlowIssue
            targetRichText.addTooltipLine(FMLCommonHandler
                    .instance()
                    .getMinecraftServerInstance()
                    .getPlayerList()
                    .getPlayerByUsername(this.getTargetPlayer()).toString()
            );
        } catch (NullPointerException ignored) { }
        targetRichText.setEnabled(!userOwner);
        panel.child(targetRichText);

        // target player text box (the owner sets the target here)
        TextFieldWidget targetTextBox = new TextFieldWidget()
                .size(126, 13)
                .pos(7, 67)
                .setMaxLength(16)
                .value(targetPlayerSync);
        targetTextBox.setEnabled(userOwner);
        panel.child(targetTextBox);

        // toggle button for changing if the present is anonymous or not (disabled for non-owner players)
        ToggleButton buttonAnonymous = new ToggleButton()
                .size(13, 12)
                .pos(134, 55)
                .value(anonymousSync);
        buttonAnonymous.setEnabled(userOwner);
        buttonAnonymous.overlay(AssetsUI.EYE_ACTIVE);
        buttonAnonymous.hoverOverlay(AssetsUI.EYE_INACTIVE);
        // the button is pressed we toggle the anonymous boolean and mark the text as dirt so it gets updated
        buttonAnonymous.listenGuiAction((IGuiAction.MousePressed) mouseButton -> {
            if (buttonAnonymous.isBelowMouse()) {
                ownerRichText.markDirty();
                return true;
            }
            return false;
        });
        panel.child(buttonAnonymous);

        panel.child(new ButtonWidget<>()
                .size(24)
                .pos(148,55)
                .overlay(AssetsUI.PRESENT_CLOSED)
                .hoverOverlay(AssetsUI.PRESENT_OPEN)
                .tooltipBuilder(text -> text.addLine(IKey.lang("container.present.open.tooltip")))
                .onMousePressed(mouseButton -> {
                    this.openPresentNetwork(world, blockPos, user, panel);
                    return true;
                })
        );
        panel.getChildren().get(panel.getChildren().size() - 1).setEnabled(!userOwner && userTarget);

        // add the player inventory
        panel.bindPlayerInventory();

        // listeners for various actions`
        // client & server listeners

        // client only

        // server only listeners
        syncManager.addOpenListener(entityPlayer -> userPlayer = user);
        syncManager.addCloseListener(entityPlayer -> userPlayer = null);
        if (FMLCommonHandler.instance().getSide().isServer()) {
            IBlockState blockstate = world.getBlockState(blockPos);
            syncManager.addOpenListener(entityPlayer -> world.notifyBlockUpdate(blockPos, blockstate, blockstate, 2));
            syncManager.addCloseListener(entityPlayer -> world.notifyBlockUpdate(blockPos, blockstate, blockstate, 2));
        }

        return panel;
    }

    private void switchText(RichText richText) {
        if (this.isAnonymous()) {
            richText.addLine(IKey.lang("container.present.owner", this.getOwnerPlayer()));
        } else {
            IKey anonymousText;
            if (ChristmasUtil.isChristmas()) { // says "Secret Santa" on christmas
                anonymousText = IKey.lang("container.present.owner.secret");
            } else { // and "Anonymous User" the rest of the year
                anonymousText = IKey.lang("container.present.owner.anonymous");
            }
            richText.addLine(IKey.lang("container.present.owner", anonymousText));
        }
    }
}
