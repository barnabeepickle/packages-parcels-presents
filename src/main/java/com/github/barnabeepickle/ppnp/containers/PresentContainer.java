package com.github.barnabeepickle.ppnp.containers;

import com.github.barnabeepickle.ppnp.containers.inventory.PresentInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class PresentContainer extends Container {
    public final PresentInventory invPresent;

    public PresentContainer(InventoryPlayer invPlayer, PresentInventory invPresent) {
        this.invPresent = invPresent;

        // present inventory
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlotToContainer(new Slot(invPresent, column + row * 9 + 9, 6 + column * 18, 6 + row * 18));
            }
        }

        // player inventory
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlotToContainer(new Slot(invPlayer, column + row * 9 + 9, 35 + column * 18, 117 + row * 18));
            }
        }

        // player hotbar
        for (int column = 0; column < 9; column++) {
            this.addSlotToContainer(new Slot(invPlayer, column, 35 + column * 18, 175));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
