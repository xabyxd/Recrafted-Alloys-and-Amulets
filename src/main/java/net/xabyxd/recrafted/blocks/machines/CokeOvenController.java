package net.xabyxd.recrafted.blocks.machines;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.gui.GuiHandler;
import net.xabyxd.recrafted.tileentity.TileEntityCokeOven;

public class CokeOvenController extends BlockContainer {
    
    @SideOnly(Side.CLIENT)
    private IIcon iconSide;
    @SideOnly(Side.CLIENT)
    private IIcon iconFrontOff;
    @SideOnly(Side.CLIENT)
    private IIcon iconFrontOn;

    public CokeOvenController() {
        super(Material.rock);
        setBlockName("coke_oven_controller");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 1);
    }

    @Override public TileEntity createNewTileEntity(World w, int meta) {
        return new TileEntityCokeOven();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconSide = iconRegister.registerIcon(Recrafted.MODID + ":coke_oven_bricks");
        iconFrontOff = iconRegister.registerIcon(Recrafted.MODID + ":coke_oven_controller_front_off");
        iconFrontOn = iconRegister.registerIcon(Recrafted.MODID + ":coke_oven_controller_front_on");
        blockIcon = iconSide;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int facing = meta & 7;
        if (facing < 2) facing = 4;
        if (side == facing) {
            return (meta & 8) != 0 ? iconFrontOn : iconFrontOff;
        }
        return iconSide;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int dir = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int facing = dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4;
        world.setBlockMetadataWithNotify(x, y, z, facing, 2);
    }

    /** Call this to set the controller's active state. */
    public static void setActive(World world, int x, int y, int z, boolean active) {
        int meta = world.getBlockMetadata(x, y, z);
        int newMeta = active ? (meta | 8) : (meta & 7);
        if (newMeta != meta) {
            world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hx, float hy, float hz) {
        if (!world.isRemote) {
            player.openGui(Recrafted.instance, GuiHandler.COKE_OVEN, world, x, y, z);
        }
        return true;
    }

    @Override
    public void breakBlock(World w, int x, int y, int z, Block b, int meta) {
        TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof TileEntityCokeOven) {
            IInventory inv = (IInventory) te;
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack s = inv.getStackInSlot(i);
                if (s != null) w.spawnEntityInWorld(new EntityItem(w, x + 0.5, y + 0.5, z + 0.5, s));
            }
        }
        super.breakBlock(w, x, y, z, b, meta);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            setActive(world, x, y, z, false);
        }
    }
}