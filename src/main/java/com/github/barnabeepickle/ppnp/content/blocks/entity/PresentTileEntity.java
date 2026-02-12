package com.github.barnabeepickle.ppnp.content.blocks.entity;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

import java.util.Objects;

public class PresentTileEntity extends TileEntity implements IGuiHolder<PosGuiData> {
    private NonNullList<ItemStack> contents = NonNullList.<ItemStack>withSize(18, ItemStack.EMPTY);
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
        return 18;
    }

    public boolean isEmpty() {
        for (ItemStack itemstack : this.contents) {
            if (!itemstack.isEmpty()) {
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
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        return this.saveToNbt(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.loadFromNbt(nbt);

    }

    public NBTTagCompound saveToNbt(NBTTagCompound nbt) {
        ItemStackHelper.saveAllItems(nbt, this.contents);
        nbt.setString("target_player", this.targetPlayer);
        nbt.setString("owner_player", this.ownerPlayer);

        return nbt;
    }

    public void loadFromNbt(NBTTagCompound nbt) {
        ItemStackHelper.loadAllItems(nbt, this.contents);
        this.targetPlayer = nbt.getString("target_player");
        this.ownerPlayer = nbt.getString("owner_player");
    }

    protected NonNullList<ItemStack> getItems() {
        return this.contents;
    }

    // ModularUI stuff below

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        ModularPanel panel = ModularPanel.defaultPanel("tutorial_gui");
        panel.bindPlayerInventory();
        return panel;
    }
}
