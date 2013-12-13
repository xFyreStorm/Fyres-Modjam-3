package fyresmodjam3.blocks;

import fyresmodjam3.tileentities.TileEntityCrystal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCrystal extends BlockContainer {

	public BlockCrystal(int par1) {
		super(par1, Material.glass);
	}

	public boolean canHarvestBlock(EntityPlayer player, int i) {
		return false; //player will get it through activating
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {return new TileEntityCrystal();}
	
	public boolean hasTileEntity(int meta) {return true;}
	
	
}
