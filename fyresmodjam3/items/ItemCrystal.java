package fyresmodjam3.items;

import java.awt.Color;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fyresmodjam3.FyresModjam3;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemCrystal extends ItemBlock {

	public Icon texture;
	
	public static String[] names = new String[] {"Shining", "Void", "Firey"};
	public static Color[] colors = new Color[] {new Color(255, 255, 173), new Color(33, 0, 73), new Color(255, 55, 0)};
	
	public ItemCrystal(int par1) {
		super(par1);
		this.hasSubtypes = true;
	}
	
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < names.length; i++) {list.add(new ItemStack(id, 1, i));}
	}
	
	public String getItemDisplayName(ItemStack itemStack) {
		return names[itemStack.getItemDamage() % names.length] + " Crystal";
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		texture = iconRegister.registerIcon("fyresmodjam3:crystal_item");
		this.itemIcon = texture;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int i) {
		return colors[itemStack.getItemDamage() % colors.length].getRGB();
	}
	
	public int getBlockID() {
		return FyresModjam3.crystal.blockID;
	}
}
