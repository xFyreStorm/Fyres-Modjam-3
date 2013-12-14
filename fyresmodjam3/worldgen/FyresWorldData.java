package fyresmodjam3.worldgen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class FyresWorldData extends WorldSavedData {

	public static String key = "FyresModjam3";
	
	public int overworldCrystalChunkX, overworldCrystalChunkZ;
	public int netherCrystalChunkX, netherCrystalChunkZ;
	public int endCrystalChunkX, endCrystalChunkZ;
	
	public FyresWorldData() {
		super(key);
	}
	
	public FyresWorldData(String key) {
		super(key);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		if(nbttagcompound.hasKey("overworldCrystalChunkX")) {overworldCrystalChunkX = nbttagcompound.getInteger("overworldCrystalChunkX");}
		if(nbttagcompound.hasKey("overworldCrystalChunkZ")) {overworldCrystalChunkZ = nbttagcompound.getInteger("overworldCrystalChunkZ");}
		
		if(nbttagcompound.hasKey("netherCrystalChunkX")) {netherCrystalChunkX = nbttagcompound.getInteger("netherCrystalChunkX");}
		if(nbttagcompound.hasKey("netherCrystalChunkZ")) {netherCrystalChunkZ = nbttagcompound.getInteger("netherCrystalChunkZ");}
		
		if(nbttagcompound.hasKey("endCrystalChunkX")) {endCrystalChunkX = nbttagcompound.getInteger("endCrystalChunkX");}
		if(nbttagcompound.hasKey("endCrystalChunkZ")) {endCrystalChunkZ = nbttagcompound.getInteger("endCrystalChunkZ");}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setInteger("overworldCrystalChunkX", overworldCrystalChunkX);
		nbttagcompound.setInteger("overworldCrystalChunkZ", overworldCrystalChunkZ);
		
		nbttagcompound.setInteger("netherCrystalChunkX", netherCrystalChunkX);
		nbttagcompound.setInteger("netherCrystalChunkZ", netherCrystalChunkZ);
		
		nbttagcompound.setInteger("endCrystalChunkX", endCrystalChunkX);
		nbttagcompound.setInteger("endCrystalChunkZ", endCrystalChunkZ);
	}
	
	public void init(World world) {
		overworldCrystalChunkX = 20 + world.rand.nextInt(60);
		overworldCrystalChunkZ = 20 + world.rand.nextInt(60);
		
		System.out.println(overworldCrystalChunkX + ", " + overworldCrystalChunkZ);
		
		// TODO figure out best value for end and nether chunks
	}
	
	public static FyresWorldData forWorld(World world) {
		MapStorage storage = world.perWorldStorage;
		FyresWorldData result = (FyresWorldData) storage.loadData(FyresWorldData.class, key);
		if(result == null) {result = new FyresWorldData(); result.init(world); storage.setData(key, result);}
		return result;
	}

}
