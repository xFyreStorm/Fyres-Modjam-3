package fyresmodjam3.worldgen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class FyresWorldData extends WorldSavedData {

	public static String key = "FyresModjam3";
	public static String[] dimensions = {"_nether", "_overworld", "_end"};
	
	public int crystalChunkX, crystalChunkZ;
	
	/*public int overworldCrystalChunkX, overworldCrystalChunkZ;
	public int netherCrystalChunkX, netherCrystalChunkZ;
	public int endCrystalChunkX, endCrystalChunkZ;*/
	
	public FyresWorldData(int dimension) {
		super(key + dimensions[dimension + 1]);
	}
	
	/*public FyresWorldData(String key) {
		super(key);
	}*/

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		if(nbttagcompound.hasKey("crystalChunkX")) {crystalChunkX = nbttagcompound.getInteger("crystalChunkX");}
		if(nbttagcompound.hasKey("crystalChunkZ")) {crystalChunkZ = nbttagcompound.getInteger("crystalChunkZ");}
		
		/*if(nbttagcompound.hasKey("overworldCrystalChunkX")) {overworldCrystalChunkX = nbttagcompound.getInteger("overworldCrystalChunkX");}
		if(nbttagcompound.hasKey("overworldCrystalChunkZ")) {overworldCrystalChunkZ = nbttagcompound.getInteger("overworldCrystalChunkZ");}
		
		if(nbttagcompound.hasKey("netherCrystalChunkX")) {netherCrystalChunkX = nbttagcompound.getInteger("netherCrystalChunkX");}
		if(nbttagcompound.hasKey("netherCrystalChunkZ")) {netherCrystalChunkZ = nbttagcompound.getInteger("netherCrystalChunkZ");}
		
		if(nbttagcompound.hasKey("endCrystalChunkX")) {endCrystalChunkX = nbttagcompound.getInteger("endCrystalChunkX");}
		if(nbttagcompound.hasKey("endCrystalChunkZ")) {endCrystalChunkZ = nbttagcompound.getInteger("endCrystalChunkZ");}*/
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		
		nbttagcompound.setInteger("crystalChunkX", crystalChunkX);
		nbttagcompound.setInteger("crystalChunkZ", crystalChunkZ);
		
		/*nbttagcompound.setInteger("overworldCrystalChunkX", overworldCrystalChunkX);
		nbttagcompound.setInteger("overworldCrystalChunkZ", overworldCrystalChunkZ);
		
		nbttagcompound.setInteger("netherCrystalChunkX", netherCrystalChunkX);
		nbttagcompound.setInteger("netherCrystalChunkZ", netherCrystalChunkZ);
		
		nbttagcompound.setInteger("endCrystalChunkX", endCrystalChunkX);
		nbttagcompound.setInteger("endCrystalChunkZ", endCrystalChunkZ);*/
	}
	
	public void init(World world) {
		crystalChunkX = 20 + world.rand.nextInt(60);
		crystalChunkZ = 20 + world.rand.nextInt(60);
		
		System.out.println(world.provider.dimensionId + ", " + (crystalChunkX * 16) + ", " + (crystalChunkZ * 16));
		
		// TODO figure out best value for end and nether chunks
	}
	
	public static FyresWorldData forWorld(World world) {
		MapStorage storage = world.perWorldStorage;
		FyresWorldData result = (FyresWorldData) storage.loadData(FyresWorldData.class, key);
		if(result == null && world.provider.dimensionId < 2 && world.provider.dimensionId > -2) {result = new FyresWorldData(world.provider.dimensionId); result.init(world); storage.setData(key, result);}
		return result;
	}

}
