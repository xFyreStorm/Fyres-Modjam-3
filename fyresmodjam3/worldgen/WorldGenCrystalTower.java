package fyresmodjam3.worldgen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import fyresmodjam3.FyresModjam3;
import fyresmodjam3.handlers.CommonTickHandler;

public class WorldGenCrystalTower implements IWorldGenerator {
	
	public static long seed = 0;
	public static HashMap<Integer, FyresWorldData> worldData = new HashMap<Integer, FyresWorldData>();
	public static ArrayList<Integer> tested = new ArrayList<Integer>();

	public static int[] stairOrientation = {0, 2, 1, 3};
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(seed != world.provider.getSeed()) {seed = world.provider.getSeed(); worldData.clear(); tested.clear();}
		if(worldData.get(world.provider.dimensionId) == null && !tested.contains(world.provider.dimensionId)) {worldData.put(world.provider.dimensionId, FyresWorldData.forWorld(world)); tested.add(world.provider.dimensionId);}
		FyresWorldData fyresWorldData = worldData.get(world.provider.dimensionId);
		
		/*if((world.provider.dimensionId == 0 && chunkX == CommonTickHandler.worldData.overworldCrystalChunkX && chunkZ == CommonTickHandler.worldData.overworldCrystalChunkZ) ||
		   (world.provider.dimensionId == -1 && chunkX == CommonTickHandler.worldData.netherCrystalChunkX && chunkZ == CommonTickHandler.worldData.netherCrystalChunkZ) ||
		   (world.provider.dimensionId == 1 && chunkX == CommonTickHandler.worldData.endCrystalChunkX && chunkZ == CommonTickHandler.worldData.endCrystalChunkZ)) {*/
		
		if(fyresWorldData != null && chunkX == fyresWorldData.crystalChunkX && chunkZ == fyresWorldData.crystalChunkZ) {	
			for(int x = 0; x < 16; x++) {
				for(int z = 0; z < 16; z++) {
					for(int y = 255; y >= 0; y--) {
						
						int xF = chunkX * 16 + x;
						int zF = chunkZ * 16 + z;
						
						int dist = (x - 8) * (x - 8) + (z - 8) * (z - 8);
						
						if(y < 2) {
							world.setBlock(xF, y, zF, Block.bedrock.blockID);
						} else if(dist > 25 && dist <= 49 || (dist <= 49 && (y == 2 || y == 255))) {
							world.setBlock(xF, y, zF, (y == 255 && dist <= 25) ? Block.glass.blockID : (world.provider.dimensionId == 0 ? (random.nextBoolean() ? Block.cobblestoneMossy.blockID : Block.cobblestone.blockID) : (world.provider.dimensionId == -1 ? Block.netherBrick.blockID : Block.obsidian.blockID)));
						} else if(x == 8 && z == 8 && (y == 1 || y == 2)) {
							if(y == 1) {world.setBlock(xF, y, zF, FyresModjam3.crystalStand.blockID);}
							else if(y == 2) {world.setBlock(xF, y, zF, FyresModjam3.crystal.blockID);}
						} else if(world.getBlockId(xF, y + 1, zF) != Block.stairsNetherBrick.blockID && world.getBlockId(xF, y + 1, zF) != Block.stairsCobblestone.blockID && (x == 5 + (y % 6) && z == 5 && (y/6) % 4 == 0) || (z == 5 + (y % 6) && x == 11 && (y/6) % 4 == 1) || (x == 11 - (y % 6) && z == 11 && (y/6) % 4 == 2) || (z == 11 - (y % 6) && x == 5 && (y/6) % 4 == 3)) {
							world.setBlock(xF, y, zF, world.provider.dimensionId == -1 ? Block.stairsNetherBrick.blockID : Block.stairsCobblestone.blockID, stairOrientation[(y/6) % 4], 0);
							world.setBlockToAir(xF, y + 1, zF);
							world.setBlockToAir(xF, y + 2, zF);
						} else if(dist <= 25) {
							world.setBlockToAir(xF, y, zF);
						}

						// TODO Staircase from bottom of world to top? Netherbrick version of this for the end, stone for overworld? :P
					}
				}
			}
		}
	}

}
