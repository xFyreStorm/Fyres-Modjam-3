package fyresmodjam3.items;

import java.awt.Color;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemAmulet extends Item {

	public static String[] names = {"Star", "Fire"};
	public static Color[] color = {new Color(255, 251, 175), new Color(255, 82, 5)};
	
	public Icon[] icons = new Icon[names.length];
	public Icon[] overlays = new Icon[names.length];
	
	public ItemAmulet(int par1) {
		super(par1);
		this.hasSubtypes = true;
		this.maxStackSize = 1;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < names.length; i++) {
			icons[i] = par1IconRegister.registerIcon("fyresmodjam3:" + names[i].toLowerCase() + "_amulet");
			overlays[i] = par1IconRegister.registerIcon("fyresmodjam3:" + names[i].toLowerCase() + "_amulet_overlay");
		}
		
        this.itemIcon = icons[0];
    }
	
	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return color[par1ItemStack.getItemDamage() % color.length].getRGB();
    }

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
	
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < names.length; i++) {list.add(new ItemStack(id, 1, i));}
	}
}
