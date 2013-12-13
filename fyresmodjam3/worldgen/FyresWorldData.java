package fyresmodjam3.worldgen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class FyresWorldData extends WorldSavedData {

	public static String key = "FyresModjam3";
	
	public FyresWorldData() {
		super(key);
	}
	
	public FyresWorldData(String key) {
		super(key);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}
	
	public static FyresWorldData forWorld(World world) {
		MapStorage storage = world.perWorldStorage;
		FyresWorldData result = (FyresWorldData) storage.loadData(FyresWorldData.class, key);
		if(result == null) {result = new FyresWorldData(); storage.setData(key, result);}
		return result;
	}

}
