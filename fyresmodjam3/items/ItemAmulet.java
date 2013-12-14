package fyresmodjam3.items;

import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemAmulet extends Item {

	public Icon[] icons = new Icon[2];
	public Icon[] overlays = new Icon[2];
	
	public static String[] names = {"Star", "Fire"};
	
	public ItemAmulet(int par1) {
		super(par1);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < names.length; i++) {
			icons[i] = par1IconRegister.registerIcon("fyresmodjam:" + names[i].toLowerCase() + "_amulet");
			overlays[i] = par1IconRegister.registerIcon("fyresmodjam:" + names[i].toLowerCase() + "_amulet_overlay");
		}
		
        this.itemIcon = icons[0];
    }
	
	/*@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return ;
    }*/

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamageForRenderPass(int i, int i2) {
		return i2 > 0 ? overlays[i] : icons[i];
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) {
        return names[par1ItemStack.getItemDamage() % names.length] + " Amulet";
    }
	
}
