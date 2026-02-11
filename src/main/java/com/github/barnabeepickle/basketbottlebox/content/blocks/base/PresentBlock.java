package com.github.barnabeepickle.basketbottlebox.content.blocks.base;

import com.github.barnabeepickle.basketbottlebox.Tags;
import com.github.barnabeepickle.basketbottlebox.bbbMod;
import com.github.barnabeepickle.basketbottlebox.content.blocks.entity.PresentTileEntity;
import com.github.barnabeepickle.basketbottlebox.networking.ModGUIHandler;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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

public abstract class PresentBlock extends ModBlock {
    public PresentBlock(MapColor mapColor) {
        super(Material.WOOD, mapColor);
        this.setHardness(1.0F);
    }

    public void openPresentGUI(World world, EntityPlayer player) {
        player.openGui(bbbMod.INSTANCE, ModGUIHandler.PRESENT_GUI, world, ((int) player.posX), ((int) player.posY), ((int) player.posZ));
    }

    @Override
    public boolean onBlockActivated(
            World world,
            BlockPos blockPos,
            IBlockState blockstate,
            EntityPlayer player,
            EnumHand hand,
            EnumFacing facing,
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
                    player.sendStatusMessage(new TextComponentTranslation("feedback." + Tags.MODID + ".present.no_owner"), false);
                } else if (presentTileEntity.isPlayerOwner(player)) {
                    // open owner GUI here
                    this.openPresentGUI(world, player);
                    return true;
                }

                if (!presentTileEntity.hasTargetPlayer()) {
                    player.sendStatusMessage(new TextComponentTranslation("feedback." + Tags.MODID + ".present.no_target"), false);
                    // open target GUI here
                    this.openPresentGUI(world, player);
                    return true;
                } else if (presentTileEntity.isPlayerTarget(player)) {
                    // open target GUI here
                    this.openPresentGUI(world, player);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(
            World world,
            BlockPos blockPos,
            IBlockState blockstate,
            EntityLivingBase entity,
            ItemStack itemStack
    ) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(blockPos);

            if (tileEntity instanceof PresentTileEntity presentTileEntity && entity instanceof EntityPlayer player) {
                if (!presentTileEntity.hasOwnerPlayer()) {
                    presentTileEntity.setOwnerPlayer(player);
                    presentTileEntity.markDirty();
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemStack getItem(World world, BlockPos blockPos, IBlockState blockstate) {
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
    public void breakBlock(World world, BlockPos blockPos, IBlockState blockstate) {
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
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new PresentTileEntity();
    }

    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
