package fyresmodjam3.handlers;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class FyresKeyHandler extends KeyHandler {

	public static KeyBinding[] keyBindings = new KeyBinding[] {};
	public static boolean[] repeat = new boolean[] {};
	
	public FyresKeyHandler() {
		super(keyBindings, repeat);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		// TODO Auto-generated method stub
	}

	@Override
	public EnumSet<TickType> ticks() {
		return null;
	}

}
