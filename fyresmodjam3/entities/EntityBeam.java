package fyresmodjam3.entities;

import net.minecraft.entity.EntityLivingBase;
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
	
	// TODO After modjam, or if I have time, make this actually a beam, rather than a single projectile :P

}
