package fyresmodjam3.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;

public class GUICrystalEquipment extends GuiScreen {
	
	public GUICrystalEquipment() {}
	
	public void initButtons() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(1, this.width/2 - 75, 50, 150, 20, "Exit"));
	}
	
	@Override
	public void drawScreen(int i, int i2, float f) {
		Minecraft client = FMLClientHandler.instance().getClient();
		EntityPlayer player = client.thePlayer;
		ScaledResolution resolutionSettings = new ScaledResolution(client.gameSettings, client.displayWidth, client.displayHeight);
		
		int scaledWidth = resolutionSettings.getScaledWidth();
		int scaledHeight = resolutionSettings.getScaledHeight();
		
		if(buttonList.isEmpty()) {initButtons();}
		
		super.drawScreen(i, i2, f);
		
		GL11.glPushMatrix();
		
		this.drawCenteredString(fontRenderer, "Test", scaledWidth/2, 10, Color.WHITE.getRGB());
		
		GL11.glPopMatrix();
	}
	
	public boolean doesGuiPauseGame() {return true;}
	
	public void actionPerformed(GuiButton button) {
		if(button.id == 1) {this.mc.displayGuiScreen(null);}
	}
	
}
