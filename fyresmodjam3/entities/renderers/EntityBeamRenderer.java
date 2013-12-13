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
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBeamRenderer extends RenderArrow {
	
	public static ResourceLocation texture = new ResourceLocation("fyresmodjam3", "textures/beam.png");
	protected ResourceLocation getEntityTexture(Entity entity) {return texture;}
	
	@Override
	protected void bindEntityTexture(Entity entity) {
		super.bindEntityTexture(entity);
		Color color = ItemCrystal.colors[entity.getDataWatcher().getWatchableObjectInt(17)];
		GL11.glColor3f(red, green, blue);
	}

}
