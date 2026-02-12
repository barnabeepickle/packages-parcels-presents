package com.github.barnabeepickle.ppnp.containers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.text.TextComponentTranslation;

public class PresentInventory extends InventoryBasic {
    public final EntityPlayer player;

    public PresentInventory(EntityPlayer player) {
        super(new TextComponentTranslation("container.present"), 18);

        this.player = player;
    }
}
