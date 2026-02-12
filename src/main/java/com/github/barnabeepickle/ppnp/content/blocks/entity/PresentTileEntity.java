package com.github.barnabeepickle.ppnp.content.blocks.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import jakarta.annotation.Nullable;

import java.util.Objects;

public class PresentTileEntity extends TileEntity {
    private NonNullList<ItemStack> contents = NonNullList.<ItemStack>withSize(18, ItemStack.EMPTY);
    private boolean creativePlayerDestroyed;
    @Nullable
    private String targetPlayer = null;
    @Nullable
    private String ownerPlayer = null;

    public PresentTileEntity() {

    }

    public boolean hasTargetPlayer() {
        return targetPlayer != null;
    }

    public boolean hasOwnerPlayer() {
        return ownerPlayer != null;
    }

    public boolean isPlayerTarget(EntityPlayer player) {
        if (this.hasTargetPlayer()) {
            assert targetPlayer != null;
            return Objects.equals(player.getName(), targetPlayer);
        }
        return false;
    }

    public boolean isPlayerOwner(EntityPlayer player) {
        if (this.hasOwnerPlayer()) {
            assert ownerPlayer != null;
            return Objects.equals(player.getName(), ownerPlayer);
        }
        return false;
    }

    public @Nullable String getTargetPlayer() {
        return this.targetPlayer;
    }

    public @Nullable String getOwnerPlayer() {
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
        ItemStackHelper.saveAllItems(nbt, this.contents, false);
        nbt.setString("target_player", this.targetPlayer);
        nbt.setString("owner_player", this.ownerPlayer);

        return nbt;
    }

    public void loadFromNbt(NBTTagCompound nbt) {
        ItemStackHelper.loadAllItems(nbt, this.contents);
        this.targetPlayer = nbt.getString("target_player");
        this.ownerPlayer = nbt.getString("owner_player");
    }

    protected NonNullList<ItemStack> getItems()
    {
        return this.contents;
    }
}
