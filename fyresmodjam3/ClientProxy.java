package fyresmodjam3;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import fyresmodjam3.entities.EntityBeam;
import fyresmodjam3.entities.renderers.EntityBeamRenderer;
import fyresmodjam3.handlers.ClientTickHandler;
import fyresmodjam3.handlers.FyresKeyHandler;
import fyresmodjam3.tileentities.TileEntityCrystal;
import fyresmodjam3.tileentities.renderers.TileEntityCrystalRenderer;

public class ClientProxy extends CommonProxy {
	@Override
	public void register() {
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		
		KeyBindingRegistry.registerKeyBinding(new FyresKeyHandler()); //Am I even going to use keys?
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new TileEntityCrystalRenderer());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new EntityBeamRenderer());
		
		//Might need this later? MinecraftForge.EVENT_BUS.register(this);
	}
}
