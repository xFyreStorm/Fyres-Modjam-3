package fyresmodjam3.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemScroll extends ItemEditableBook {
	
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
		if(!stack.hasTagCompound()) {stack.stackTagCompound = new NBTTagCompound();}
		
		if(!stack.getTagCompound().hasKey("initialized") || !stack.getTagCompound().getBoolean("initialized")) {
			stack.getTagCompound().setBoolean("initialized", true);
			
			ItemStack book = new ItemStack(Item.writtenBook, 1, 0);
			
			NBTTagList pages = new NBTTagList("pages");
			for(int i = 2; i < scrollText[book.getItemDamage() % scrollText.length].length - 2; i++) {
				pages.appendTag(new NBTTagString("" + (i - 1), scrollText[book.getItemDamage() % scrollText.length][i]));
			}
			
			book.setTagInfo("pages", pages);
			book.setTagInfo("author", new NBTTagString("author", scrollText[book.getItemDamage() % scrollText.length][0]));
			book.setTagInfo("title", new NBTTagString("title", scrollText[book.getItemDamage() % scrollText.length][1]));
			
			NBTTagCompound bookTag = new NBTTagCompound();
			book.writeToNBT(bookTag);
			stack.setTagInfo("book", bookTag);
		}
	}
	
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < scrollText.length; i++) {list.add(new ItemStack(id, 1, i));}
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		NBTTagCompound bookTag = par1ItemStack.getTagCompound().getCompoundTag("book");
        if(bookTag != null) {par3EntityPlayer.displayGUIBook(ItemStack.loadItemStackFromNBT(bookTag));}
        return par1ItemStack;
    }
}
