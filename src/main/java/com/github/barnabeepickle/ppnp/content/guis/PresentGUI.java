package com.github.barnabeepickle.ppnp.content.guis;

import com.github.barnabeepickle.ppnp.Tags;
import com.github.barnabeepickle.ppnp.containers.PresentContainer;
import com.github.barnabeepickle.ppnp.containers.inventory.PresentInventory;
import com.github.barnabeepickle.ppnp.utils.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class PresentGUI extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Tags.MODID, "textures/gui/present.png");
    private final PresentInventory inventory;

    public PresentGUI(InventoryPlayer invPlayer, PresentInventory inventory) {
        super(new PresentContainer(invPlayer, inventory));
        this.inventory = inventory;
        this.xSize = 176;
        this.ySize = 175;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int var1, int var2) {
        this.fontRenderer.drawString(I18n.format("container.present"), 6, 6, ColorUtil.getColor(70, 70, 70));
    }
}
