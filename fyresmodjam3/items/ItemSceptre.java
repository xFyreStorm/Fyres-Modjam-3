package fyresmodjam3.items;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fyresmodjam3.entities.EntityBeam;
import fyresmodjam3.handlers.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemSceptre extends Item {

	public Icon texture;
	
	public ItemSceptre(int par1) {
		super(par1);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		texture = iconRegister.registerIcon("fyresmodjam3:sceptre");
		this.itemIcon = texture;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			EntityBeam beam = new EntityBeam(world, player, 2.0F);
			world.spawnEntityInWorld(beam);
		} else {
			PacketDispatcher.sendPacketToServer(PacketHandler.newPacket(PacketHandler.PLAY_SOUND, new Object[] {"beam1", player.posX, player.posY, player.posZ}));
		}
		
		//world.playSoundAtEntity(player, "fyresmodjam3:beam1", 1.0F, 1.0F);
		player.playSound("fyresmodjam3:beam1", 1.0F, 1.0F);
		player.playSound("random.xporb", 1.0F, 1.0F);
		
		return stack;
	}
	
}
