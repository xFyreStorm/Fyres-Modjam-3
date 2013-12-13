package fyresmodjam3;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import fyresmodjam3.handlers.ClientTickHandler;
import fyresmodjam3.handlers.FyresKeyHandler;

public class ClientProxy extends CommonProxy {
	@Override
	public void register() {
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		
		KeyBindingRegistry.registerKeyBinding(new FyresKeyHandler()); //Am I even going to use keys?
		
		//Might need this later? MinecraftForge.EVENT_BUS.register(this);
	}
}
