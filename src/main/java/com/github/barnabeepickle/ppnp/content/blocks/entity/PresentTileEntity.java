package com.github.barnabeepickle.ppnp.content.blocks.entity;

import com.cleanroommc.modularui.ModularUI;
import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.RichTextWidget;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.cleanroommc.modularui.widgets.slot.SlotGroup;
import com.github.barnabeepickle.ppnp.Tags;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class PresentTileEntity extends TileEntity implements IGuiHolder<PosGuiData> {
    private static final int SLOT_COUNT = 18;

    private final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_COUNT);

    private boolean creativePlayerDestroyed;
    private String targetPlayer = "";
    private String ownerPlayer = "";

    public PresentTileEntity() {

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

    public void setTargetPlayer(EntityPlayer player) {
        this.targetPlayer = player.getName();
    }

    public void setOwnerPlayer(EntityPlayer player) {
        this.ownerPlayer = player.getName();
    }

    public int getSizeInventory() {
        return SLOT_COUNT;
    }

    public boolean isEmpty() {
        for (int i = 0; i <= SLOT_COUNT; i++) {
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

        return nbt;
    }

    public void loadFromNbt(NBTTagCompound nbt) {
        this.itemHandler.deserializeNBT(nbt.getCompoundTag("present_inv"));
        this.targetPlayer = nbt.getString("target_player");
        this.ownerPlayer = nbt.getString("owner_player");
    }

    // ModularUI stuff below

    @Override
    @SideOnly(Side.CLIENT)
    public ModularScreen createScreen(PosGuiData guiData, ModularPanel mainPanel) {
        return new ModularScreen(Tags.MODID, mainPanel);
    }

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        SlotGroup presentSlots = new SlotGroup("present_slot_group", 9, true);
        syncManager.registerSlotGroup(presentSlots);

        ModularPanel panel = ModularPanel.defaultPanel("present_gui");
        panel.bindPlayerInventory()
                .child(new RichTextWidget()
                        .addLine(I18n.format("container.present"))
                        .size(162, 8)
                        .left(7)
                        .top(5)
                ).child(new ItemSlot().slot(
                        new ModularSlot(this.itemHandler, 0)
                                .slotGroup(presentSlots)
                        )
                );



        return panel;
    }
}
