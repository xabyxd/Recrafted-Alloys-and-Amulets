package net.xabyxd.recrafted.blocks.machines;

import static net.xabyxd.recrafted.CommonProxy.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.xabyxd.recrafted.Recrafted;

public class CokeOvenController extends Block {
    
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
}