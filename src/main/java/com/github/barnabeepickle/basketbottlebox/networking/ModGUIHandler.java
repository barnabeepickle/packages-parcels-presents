package com.github.barnabeepickle.basketbottlebox.networking;

import com.github.barnabeepickle.basketbottlebox.containers.PresentContainer;
import com.github.barnabeepickle.basketbottlebox.containers.inventory.PresentInventory;
import com.github.barnabeepickle.basketbottlebox.content.guis.PresentGUI;
import jakarta.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGUIHandler implements IGuiHandler {
    public static final int PRESENT_GUI = 1;

    @Override
    public @Nullable Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return switch (id) {
            case PRESENT_GUI ->
                    new PresentContainer(player.inventory, new PresentInventory(player));
            default -> null;
        };
    }

    @Override
    public @Nullable Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return switch (id) {
            case PRESENT_GUI ->
                    new PresentGUI(player.inventory, new PresentInventory(player));
            default -> null;
        };
    }
}