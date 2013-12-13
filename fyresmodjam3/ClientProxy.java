package fyresmodjam3;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
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
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public static String[] sounds = new String[] {"beam1", "beam2", "beamHit"};
	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		for(String s : sounds) {event.manager.addSound("fyresmodjam3:" + s + ".wav");}
	}
}
