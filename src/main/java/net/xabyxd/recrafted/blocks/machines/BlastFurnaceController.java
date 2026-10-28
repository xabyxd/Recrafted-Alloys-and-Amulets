package net.xabyxd.recrafted.blocks.machines;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.xabyxd.recrafted.Recrafted;

public class BlastFurnaceController extends Block {
    
    @SideOnly(Side.CLIENT)
    private IIcon iconSide;
    @SideOnly(Side.CLIENT)
    private IIcon iconFrontOff;
    @SideOnly(Side.CLIENT)
    private IIcon iconFrontOn;

    public BlastFurnaceController() {
        super(Material.rock);
        setBlockName("blast_furnace_controller");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconSide = iconRegister.registerIcon(Recrafted.MODID + ":blast_furnace_side");
        iconFrontOff = iconRegister.registerIcon(Recrafted.MODID + ":blast_furnace_front");
        iconFrontOn = iconRegister.registerIcon(Recrafted.MODID + ":blast_furnace_front_active");
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) return true; // Only the server controls the logic

        ChatComponentText prefix = new ChatComponentText("[INFO] ");
        prefix.getChatStyle().setColor(EnumChatFormatting.YELLOW);

        ChatComponentText message = new ChatComponentText("");
        message.appendSibling(prefix);
        message.appendSibling(new ChatComponentTranslation("chat.recrafted.coke_oven_controller.message"));

        player.addChatMessage(message);

        setActive(world, x, y, z, true);
        world.scheduleBlockUpdate(x, y, z, this, 200); // 200 ticks = 10 seconds
        return true;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            setActive(world, x, y, z, false);
        }
    }
}