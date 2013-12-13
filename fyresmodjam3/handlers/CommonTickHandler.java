package fyresmodjam3.handlers;

import java.util.EnumSet;

import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import fyresmodjam3.worldgen.FyresWorldData;

public class CommonTickHandler implements ITickHandler {

	public static FyresWorldData worldData = null;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.WORLDLOAD))) {
			for(int i = 0; i < tickData.length; i++) {
				if(tickData[i] instanceof World && ((World) tickData[i]).provider.dimensionId == 0) {
					worldData = FyresWorldData.forWorld((World) tickData[i]);
					worldData.markDirty();
				}
			}
		} else if(type.equals(EnumSet.of(TickType.SERVER))) {tick();}
	}
	
	public void tick() {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER, TickType.WORLDLOAD);
	}

	@Override
	public String getLabel() {
		return "FyresModjam3CommonTicker";
	}

}
