package fyresmodjam3.entities.renderers;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import fyresmodjam3.FyresModjam3;
import fyresmodjam3.items.ItemCrystal;
import fyresmodjam3.models.ModelCrystal;
import fyresmodjam3.tileentities.TileEntityCrystal;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBeamRenderer extends Render {
	
	private ModelArrow model = new ModelArrow();
	
	public static ResourceLocation texture = new ResourceLocation("fyresmodjam3", "textures/beam.png");

}
