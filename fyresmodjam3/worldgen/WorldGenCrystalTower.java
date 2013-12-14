package fyresmodjam3.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import fyresmodjam3.FyresModjam3;
import fyresmodjam3.handlers.CommonTickHandler;

public class WorldGenCrystalTower implements IWorldGenerator {

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.isSurfaceWorld()) {
			if(chunkX == CommonTickHandler.worldData.overworldCrystalChunkX && chunkZ == CommonTickHandler.worldData.overworldCrystalChunkZ) {
				for(int x = 4; x < 10; x++) {
					for(int z = 4; z < 10; z++) {
						for(int y = 0; y < 256; y++) {
							if(z == 4 || z == 9 || x == 4 || x == 9 || y == 0 || y == 255) {
								world.setBlock(chunkX * 16 + x, y, chunkZ * 16 + z, Block.obsidian.blockID);
							} else if(x == 7 && z == 7 && (y == 1 || y == 2)) {
								if(y == 1) {world.setBlock(chunkX * 16 + x, y, chunkZ * 16 + z, FyresModjam3.crystalStand.blockID);}
								else if(y == 2) {world.setBlock(chunkX * 16 + x, y, chunkZ * 16 + z, FyresModjam3.crystal.blockID);}
							} else {
								world.setBlockToAir(chunkX * 16 + x, y, chunkZ * 16 + z);
							}
							
							// TODO Staircase from bottom of world to top? Netherbrick version of this for the end, stone for overworld? :P
						}
					}
				}
			}
			
		}
		
		/*if(world.provider.isHellWorld && !CommonTickHandler.worldData.netherCrystalSpawned) {
			
		}*/
	}

}
