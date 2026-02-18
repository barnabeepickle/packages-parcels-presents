package com.github.barnabeepickle.ppnp.ui;

import com.cleanroommc.modularui.drawable.UITexture;
import com.github.barnabeepickle.ppnp.Tags;

public class AssetsUI {
    public static UITexture EYE_ACTIVE = UITexture.builder()
            .location(Tags.MODID, "textures/gui/eye.png")
            .imageSize(12, 24)
            .iconColorType()
            .name("eye_active")
            .subAreaUV(0.0F, 0.0F, 1.0F, 0.5F)
            .build();

    public static UITexture EYE_INACTIVE = UITexture.builder()
            .location(Tags.MODID, "textures/gui/eye.png")
            .imageSize(12, 24)
            .iconColorType()
            .name("eye_inactive")
            .subAreaUV(0.0F, 0.5F, 1.0F, 1.0F)
            .build();

    public static UITexture PRESENT_OPEN = UITexture.builder()
            .location(Tags.MODID, "textures/gui/present_icon.png")
            .imageSize(192, 128)
            .iconColorType()
            .nonOpaque()
            .name("present_open")
            .subAreaUV(0.0F, 0.5F, 1.0F / 3, 1.0F)
            .build();

    public static UITexture PRESENT_OPEN_SHADOW = UITexture.builder()
            .location(Tags.MODID, "textures/gui/present_icon.png")
            .imageSize(192, 128)
            .iconColorType()
            .nonOpaque()
            .name("present_open_shadow")
            .subAreaUV(1.0F / 3, 0.5F, 2.0F / 3, 1.0F)
            .build();

    public static UITexture PRESENT_OPEN_LIGHT = UITexture.builder()
            .location(Tags.MODID, "textures/gui/present_icon.png")
            .imageSize(192, 128)
            .iconColorType()
            .nonOpaque()
            .name("present_open_light")
            .subAreaUV(2.0F / 3, 0.5F, 1.0F, 1.0F)
            .build();

    public static UITexture PRESENT_CLOSED = UITexture.builder()
            .location(Tags.MODID, "textures/gui/present_icon.png")
            .imageSize(192, 128)
            .iconColorType()
            .nonOpaque()
            .name("present_open")
            .subAreaUV(0.0F, 0.0F, 1.0F / 3, 0.5F)
            .build();

    public static UITexture PRESENT_CLOSED_SHADOW = UITexture.builder()
            .location(Tags.MODID, "textures/gui/present_icon.png")
            .imageSize(192, 128)
            .iconColorType()
            .nonOpaque()
            .name("present_open_shadow")
            .subAreaUV(1.0F / 3, 0.0F, 2.0F / 3, 0.5F)
            .build();

    public static UITexture PRESENT_CLOSED_LIGHT = UITexture.builder()
            .location(Tags.MODID, "textures/gui/present_icon.png")
            .imageSize(192, 128)
            .iconColorType()
            .nonOpaque()
            .name("present_open_light")
            .subAreaUV(2.0F / 3, 0.0F, 0.5F, 0.5F)
            .build();
}
