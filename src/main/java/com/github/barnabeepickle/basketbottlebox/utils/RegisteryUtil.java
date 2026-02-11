package com.github.barnabeepickle.basketbottlebox.utils;

import com.github.barnabeepickle.basketbottlebox.Tags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegisteryUtil {
    public static <V extends IForgeRegistryEntry<V>> void registerEntry(IForgeRegistry<V> registry, IForgeRegistryEntry<V> o, String name) {
        registerEntry(registry, o, new ResourceLocation(Tags.MODID, name));
    }

    public static <V extends IForgeRegistryEntry<V>> void registerEntry(IForgeRegistry<V> registry, IForgeRegistryEntry<V> o, ResourceLocation name) {
        registry.register(o.setRegistryName(name));
    }

    public static void registerTileEntity(Class<? extends TileEntity> tileEntity, String name) {
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(Tags.MODID, name));
    }
}
