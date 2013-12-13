package fyresmodjam3.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fyresmodjam3.tileentities.TileEntityCrystalStand;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrystalStand extends BlockContainer {

	public BlockCrystalStand(int par1) {
		super(par1, Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCrystalStand();
	}

	public boolean hasTileEntity(int meta) {return true;}

	public boolean isOpaqueCube() {return false;}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int i, int i2, int i3, int i4) {
		return false;
	}
}
