package fyresmodjam3.items;

import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fyresmodjam3.entities.EntityBeam;
import fyresmodjam3.handlers.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemStaffs extends Item {

	public static String[] textureNames = {"basic_staff", "sceptre"}, names = {"Basic Staff", "Sceptre"};
	public Icon[] textures = new Icon[textureNames.length];
	
	public ItemStaffs(int par1) {
		super(par1);
		this.hasSubtypes = true;
		this.maxStackSize = 1;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for(int i = 0; i < textureNames.length; i++) {
			textures[i] = iconRegister.registerIcon("fyresmodjam3:" + textureNames[i]);
		}
	
		this.itemIcon = textures[0];
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			EntityBeam beam = new EntityBeam(world, player, 2.0F);
			if(player.getEntityData().hasKey("equippedCrystal")) {beam.setCrystalType(player.getEntityData().getInteger("equippedCrystal"));}
			world.spawnEntityInWorld(beam);
			
			world.playSoundAtEntity(player, "fyresmodjam3:beamB", 0.5F, 1.0F);
		}/* else {
			PacketDispatcher.sendPacketToServer(PacketHandler.newPacket(PacketHandler.PLAY_SOUND, new Object[] {"beamB", player.posX, player.posY, player.posZ}));
		}*/
		
		//world.playSoundAtEntity(player, "fyresmodjam3:beamB", 0.75F, 0.5F);
		//player.playSound("fyresmodjam3:beam1", 1.0F, 1.0F);
		//player.playSound("random.xporb", 1.0F, 1.0F);
		
		return stack;
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par1, boolean b) {
		if(!world.isRemote) {
			if(!stack.hasTagCompound()) {stack.stackTagCompound = new NBTTagCompound();}
			
			if(!stack.getTagCompound().hasKey("initialized") || !stack.getTagCompound().getBoolean("initialized")) {
				stack.getTagCompound().setBoolean("initialized", true);
				stack.getTagCompound().setInteger("Level", 1);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return textures[par1];
	}
	
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < textureNames.length; i++) {list.add(new ItemStack(id, 1, i));}
	}
	
	public String getItemDisplayName(ItemStack itemStack) {return names[itemStack.getItemDamage() % names.length];}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(EnumChatFormatting.GRAY + "Level: " + (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("Level") ? par1ItemStack.getTagCompound().getInteger("Level") : "--"));
    }
	
}
