package fyresmodjam3.tileentities.renderers;

import org.lwjgl.opengl.GL11;

import fyresmodjam3.FyresModjam3;
import fyresmodjam3.models.ModelCrystal;
import fyresmodjam3.tileentities.TileEntityCrystal;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCrystalRenderer extends TileEntitySpecialRenderer {
	
	private ModelCrystal modelCrystal = new ModelCrystal();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d0, (float) d1, (float) d2);
		
		TileEntityCrystal crystal = (TileEntityCrystal) tileentity;
		Block block = FyresModjam3.crystal;
		World world = crystal.worldObj;
		
		
		Tessellator tessellator = Tessellator.instance;
		float f2 = block.getBlockBrightness(world, crystal.xCoord, crystal.yCoord, crystal.zCoord);
		int l = world.getLightBrightnessForSkyBlocks(crystal.xCoord, crystal.yCoord, crystal.zCoord, 0);
		int l1 = l % 65536;
		int l2 = l / 65536;
		tessellator.setColorOpaque_F(f2, f2, f2);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) l2);
		
		GL11.glPushMatrix();
		
		this.tileEntityRenderer.renderEngine.bindTexture(texture);
		modelCrystal.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}

}
