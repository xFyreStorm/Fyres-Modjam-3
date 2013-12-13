package fyresmodjam3.entities;

import cpw.mods.fml.common.network.PacketDispatcher;
import fyresmodjam3.handlers.PacketHandler;
import fyresmodjam3.items.ItemCrystal;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityBeam extends EntityArrow {

	public EntityBeam(World par1World) {
		super(par1World);
	}
	
	public EntityBeam(World par1World, double d, double d2, double d3) {
		super(par1World, d, d2, d3);
	}
	
	public EntityBeam(World par1World, EntityLivingBase par2EntityLivingBase, float par3) {
		super(par1World, par2EntityLivingBase, par3);
	}
	
	public EntityBeam(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par2EntityLivingBase2, float par3, float f2) {
		super(par1World, par2EntityLivingBase, par2EntityLivingBase2, par3, f2);
	}
	
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {}
	
	public void onUpdate() {
		super.onUpdate();
		
		if(!worldObj.isRemote && this.arrowShake != 0) {this.setDead();}
	}
	
	@Override
	public void applyEntityCollision(Entity entity) {
		super.applyEntityCollision(entity);
		
		if(!worldObj.isRemote && getCrystalType() == 2) {entity.setFire(100);}
	}
	
	public void setDead() {
		super.setDead();
		
		if(!worldObj.isRemote && getCrystalType() == 2 && worldObj.isAirBlock((int) posX, (int) posY, (int) posZ)) {
			this.worldObj.setBlock((int) posX, (int) posY, (int) posZ, Block.fire.blockID);
		}
	}
	
	@Override
	public void entityInit() {super.entityInit(); this.dataWatcher.addObject(17, -1);}
	
	public int getCrystalType() {return this.dataWatcher.getWatchableObjectInt(17);}
	public void setCrystalType(int i) {this.dataWatcher.updateObject(17, i);}
	
	// TODO After modjam, or if I have time, make this actually a beam, rather than a single projectile :P
}
