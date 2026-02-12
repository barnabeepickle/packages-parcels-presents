package com.github.barnabeepickle.ppnp.content.blocks.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import jakarta.annotation.Nullable;

import java.util.UUID;

public class PresentTileEntity extends TileEntity {
    private NonNullList<ItemStack> contents = NonNullList.<ItemStack>withSize(18, ItemStack.EMPTY);
    private boolean creativePlayerDestroyed;
    @Nullable
    private UUID targetPlayer = null;
    @Nullable
    private UUID ownerPlayer = null;

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
            return EntityPlayer.getUUID(player.getGameProfile()).compareTo(targetPlayer) == 0;
        }
        return false;
    }

    public boolean isPlayerOwner(EntityPlayer player) {
        if (this.hasOwnerPlayer()) {
            assert ownerPlayer != null;
            return EntityPlayer.getUUID(player.getGameProfile()).compareTo(ownerPlayer) == 0;
        }
        return false;
    }

    public @Nullable UUID getTargetPlayer() {
        return this.targetPlayer;
    }

    public @Nullable UUID getOwnerPlayer() {
        return this.ownerPlayer;
    }

    public void setTargetPlayer(EntityPlayer player) {
        this.targetPlayer = player.getPersistentID();
    }

    public void setOwnerPlayer(EntityPlayer player) {
        this.ownerPlayer = player.getPersistentID();
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

    public void setCreativePlayerDestroyed(boolean p_190579_1_) {
        this.creativePlayerDestroyed = p_190579_1_;
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
        nbt.setUniqueId("target_player", this.targetPlayer);
        nbt.setUniqueId("owner_player", this.ownerPlayer);

        return nbt;
    }

    public void loadFromNbt(NBTTagCompound nbt) {
        ItemStackHelper.loadAllItems(nbt, this.contents);
        this.targetPlayer = nbt.getUniqueId("target_player");
        this.ownerPlayer = nbt.getUniqueId("owner_player");
    }

    protected NonNullList<ItemStack> getItems()
    {
        return this.contents;
    }
}
