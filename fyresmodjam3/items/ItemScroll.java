package fyresmodjam3.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemScroll extends ItemWritableBook {
	
	public String[][] scrollText = new String[][] {
			{"Author", "Scroll #1", "Words"},
			{"Author", "Scroll #2", "Words"},
			{"Author", "Scroll #3", "Words"},
			{"Author", "Scroll #4", "Words"},
			{"Author", "Scroll #5", "Words"}
	};
	
	// TODO get text from text file to allow for user editting

	public Icon texture;
	
	public ItemScroll(int par1) {
		super(par1);
		this.hasSubtypes = true;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		texture = iconRegister.registerIcon("fyresmodjam3:scroll");
		this.itemIcon = texture;
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par1, boolean b) {
		if(!stack.getTagCompound().hasKey("initialized") || !stack.getTagCompound().getBoolean("initialized")) {
			stack.getTagCompound().setBoolean("initialized", true);
			
			NBTTagList pages = new NBTTagList("pages");
			for(int i = 2; i < scrollText[stack.getItemDamage() % scrollText.length].length - 2; i++) {
				pages.appendTag(new NBTTagString("" + (i - 1), scrollText[stack.getItemDamage() % scrollText.length][i]));
			}
			
			stack.setTagInfo("pages", pages);
			stack.setTagInfo("author", new NBTTagString("author", scrollText[stack.getItemDamage() % scrollText.length][0]));
			stack.setTagInfo("title", new NBTTagString("title", scrollText[stack.getItemDamage() % scrollText.length][1]));
		}
	}
	
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < scrollText.length; i++) {list.add(new ItemStack(id, 1, i));}
	}

}
