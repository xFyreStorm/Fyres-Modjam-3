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
				for(int x = 0; x < 16; x++) {
					for(int z = 0; z < 16; z++) {
						for(int y = 0; y < 256; y++) {
							int dist = (x - 8) * (x - 8) + (z - 8) * (z - 8);
							if(dist > 25 && dist <= 49 || (dist <= 49 && (y == 0 || y == 255))) {
								world.setBlock(chunkX * 16 + x, y, chunkZ * 16 + z, (y == 255 && dist <= 25) ? Block.glass.blockID : (random.nextBoolean() ? Block.cobblestoneMossy.blockID : Block.cobblestone.blockID));
							} else if(x == 8 && z == 8 && (y == 1 || y == 2)) {
								if(y == 1) {world.setBlock(chunkX * 16 + x, y, chunkZ * 16 + z, FyresModjam3.crystalStand.blockID);}
								else if(y == 2) {world.setBlock(chunkX * 16 + x, y, chunkZ * 16 + z, FyresModjam3.crystal.blockID);}
							} else if(dist <= 25) {
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
