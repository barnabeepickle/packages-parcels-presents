package com.github.barnabeepickle.ppnp.content.guis;

import com.github.barnabeepickle.ppnp.Tags;
import com.github.barnabeepickle.ppnp.containers.PresentContainer;
import com.github.barnabeepickle.ppnp.containers.inventory.PresentInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class PresentGUI extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Tags.MODID, "textures/gui/present.png");
    private final PresentInventory inventory;

    public PresentGUI(InventoryPlayer invPlayer, PresentInventory inventory)
    {
        super(new PresentContainer(invPlayer, inventory));
        this.inventory = inventory;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
