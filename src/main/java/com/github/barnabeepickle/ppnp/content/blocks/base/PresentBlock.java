package com.github.barnabeepickle.ppnp.content.blocks.base;

import com.cleanroommc.modularui.factory.GuiFactories;
import com.github.barnabeepickle.ppnp.content.blocks.entity.PresentTileEntity;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.github.barnabeepickle.ppnp.content.ModCreativeTabs.primaryCreativeTab;

public abstract class PresentBlock extends ModBlock implements ITileEntityProvider {
    public PresentBlock(MapColor mapColor) {
        this(mapColor, primaryCreativeTab);
    }

    public PresentBlock(MapColor mapColor, CreativeTabs creativeTab) {
        super(Material.WOOD, mapColor);
        this.setHardness(1.0F);
        this.setCreativeTab(creativeTab);
    }

    public static final int USER_TARGET = 0;
    public static final int USER_OWNER = 1;

    public void openPresentGUI(World world, EntityPlayer player, IBlockState blockstate, BlockPos blockPos, int userCase) {
        world.notifyBlockUpdate(blockPos, blockstate, blockstate, 2);
        switch (userCase) {
            case USER_TARGET:
                GuiFactories.tileEntity().open(player, blockPos);
            case USER_OWNER:
                GuiFactories.tileEntity().open(player, blockPos);
            default:
                GuiFactories.tileEntity().open(player, blockPos);
        }
    }

    @Override
    public boolean onBlockActivated(
            World world,
            @Nonnull BlockPos blockPos,
            @Nonnull IBlockState blockstate,
            @Nonnull EntityPlayer player,
            @Nonnull EnumHand hand,
            @Nonnull EnumFacing facing,
            float hintX,
            float hintY,
            float hintZ
    ) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntity tileEntity = world.getTileEntity(blockPos);

            if (tileEntity instanceof PresentTileEntity presentTileEntity) {
                if (!presentTileEntity.hasOwnerPlayer()) {
                    player.sendStatusMessage(new TextComponentTranslation("feedback.present.no_owner"), false);
                } else if (presentTileEntity.isPlayerOwner(player)) {
                    // open owner GUI here
                    this.openPresentGUI(world, player, blockstate, blockPos, USER_OWNER);
                    return true;
                }

                if (!presentTileEntity.hasTargetPlayer()) {
                    player.sendStatusMessage(new TextComponentTranslation("feedback.present.no_target"), false);
                    // open target GUI here
                    this.openPresentGUI(world, player, blockstate, blockPos, USER_TARGET);
                    return true;
                } else if (presentTileEntity.isPlayerTarget(player)) {
                    player.sendStatusMessage(new TextComponentTranslation("feedback.present.you_target"), true);
                    // open target GUI here
                    this.openPresentGUI(world, player, blockstate, blockPos, USER_TARGET);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(
            World world,
            @Nonnull BlockPos blockPos,
            @Nonnull IBlockState blockstate,
            @Nonnull EntityLivingBase entity,
            @Nonnull ItemStack itemStack
    ) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(blockPos);

            if (tileEntity instanceof PresentTileEntity presentTileEntity && entity instanceof EntityPlayer player) {
                world.notifyBlockUpdate(blockPos, blockstate, blockstate, 2);
                if (!presentTileEntity.hasOwnerPlayer()) {
                    player.sendStatusMessage(new TextComponentTranslation("feedback.present.you_owner"), false);
                    presentTileEntity.setOwnerPlayer(player.getGameProfile());
                    presentTileEntity.markDirty();
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public ItemStack getItem(@Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull IBlockState blockstate) {
        ItemStack itemStack = super.getItem(world, blockPos, blockstate);
        PresentTileEntity presentTileEntity = (PresentTileEntity)world.getTileEntity(blockPos);
        assert presentTileEntity != null;
        NBTTagCompound nbt = presentTileEntity.saveToNbt(new NBTTagCompound());

        if (!nbt.isEmpty()) {
            itemStack.setTagInfo("BlockEntityTag", nbt);
        }

        return itemStack;
    }

    @Override
    public void breakBlock(World world, @Nonnull BlockPos blockPos, @Nonnull IBlockState blockstate) {
        TileEntity tileentity = world.getTileEntity(blockPos);

        if (tileentity instanceof PresentTileEntity presentTileEntity) {
            if (presentTileEntity.shouldDrop()) {
                ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
                NBTTagCompound nbt = new NBTTagCompound();
                NBTTagCompound nbt1 = new NBTTagCompound();
                nbt.setTag("BlockEntityTag", presentTileEntity.saveToNbt(nbt1));
                itemstack.setTagCompound(nbt);

                spawnAsEntity(world, blockPos, itemstack);
            }

            world.updateComparatorOutputLevel(blockPos, blockstate.getBlock());
        }

        super.breakBlock(world, blockPos, blockstate);
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new PresentTileEntity();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new PresentTileEntity();
    }

    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }
}
