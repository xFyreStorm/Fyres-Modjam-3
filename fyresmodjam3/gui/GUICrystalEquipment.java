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
	
	public GUICrystalEquipment() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(1, this.width/2 - 75, this.height - 75, this.width/2 + 150, 50, "Exit"));
	}
	
	@Override
	public void drawScreen(int i, int i2, float f) {
		Minecraft client = FMLClientHandler.instance().getClient();
		EntityPlayer player = client.thePlayer;
		ScaledResolution resolutionSettings = new ScaledResolution(client.gameSettings, client.displayWidth, client.displayHeight);
		
		int scaledWidth = resolutionSettings.getScaledWidth();
		int scaledHeight = resolutionSettings.getScaledHeight();
		
		GL11.glPushMatrix();
		
		this.drawCenteredString(fontRenderer, "Test", scaledWidth/2, 50, Color.WHITE.getRGB());
		
		super.drawScreen(i, i2, f);
		
		GL11.glPopMatrix();
	}
	
	public boolean doesGuiPauseGame() {return true;}
	
	public void actionPerformed(GuiButton button) {
		if(button.id == 1) {this.mc.displayGuiScreen(null);}
	}
	
}
