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
                this.addSlotToContainer(new Slot(invPresent, column + row * 9 + 9, 8 + column * 18, 17 + row * 18));
            }
        }

        // player inventory
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlotToContainer(new Slot(invPlayer, column + row * 9 + 9, 8 + column * 18, 93 + row * 18));
            }
        }

        // player hotbar
        for (int column = 0; column < 9; column++) {
            this.addSlotToContainer(new Slot(invPlayer, column, 8 + column * 18, 151));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
