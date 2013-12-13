package fyresmodjam3;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import fyresmodjam3.blocks.*;
import fyresmodjam3.entities.EntityBeam;
import fyresmodjam3.handlers.CommonTickHandler;
import fyresmodjam3.handlers.GUIHandler;
import fyresmodjam3.handlers.PacketHandler;
import fyresmodjam3.items.*;
import fyresmodjam3.tileentities.TileEntityCrystal;
import fyresmodjam3.tileentities.TileEntityCrystalStand;

@Mod(modid = "fyresmodjam3", name = "Fyres Modjam 3", version = "0.0.1a")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"FyresModjam3"}, packetHandler = PacketHandler.class)
public class FyresModjam3 implements IPlayerTracker {
	
	//Theme... laser shooting sceptres, lore, crystals which modify player and sceptre?
	
	//reminders
	public static Item sceptre;
	public static Item crystalItem;
	public static Item scroll;
	
	public static Block crystal; //not yet sure how many blocks I'll need
	public static Block crystalStand;
	
	public static int blockID = 1888, itemID = 1888;
	
	@SidedProxy(clientSide = "fyresmodjam3.ClientProxy", serverSide = "fyresmodjam3.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("fyresmodjam3")
	public static FyresModjam3 instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		blockID = config.get(config.CATEGORY_GENERAL, "blockIDStart", blockID, "Sets the starting number for block IDs.").getInt();
		itemID = config.get(config.CATEGORY_GENERAL, "itemIDStart", itemID, "Sets the starting number for items IDs.").getInt();
		
		config.save();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.register();
		
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		NetworkRegistry.instance().registerGuiHandler(this, new GUIHandler());
		GameRegistry.registerPlayerTracker(this);
		
		//Blocks
		
		crystal = new BlockCrystal(blockID).setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerBlock(crystal, "crystal");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "Crystal Tile Entity");
		LanguageRegistry.addName(crystal, "Crystal");
		
		crystalStand = new BlockCrystalStand(blockID + 1).setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(crystalStand, "crystalStand");
		GameRegistry.registerTileEntity(TileEntityCrystalStand.class, "Crystal Stand Tile Entity");
		LanguageRegistry.addName(crystalStand, "Crystal Stand");
		
		
		//Items
		
		crystalItem = new ItemCrystal(crystal.blockID - 256);
		
		sceptre = new ItemSceptre(itemID).setUnlocalizedName("sceptre").setCreativeTab(CreativeTabs.tabCombat).setFull3D();
		LanguageRegistry.addName(sceptre, "Sceptre");
		
		scroll = new ItemScroll(itemID + 1).setUnlocalizedName("scroll").setCreativeTab(CreativeTabs.tabMisc);
		LanguageRegistry.addName(scroll, "Scroll");
		
		EntityRegistry.registerGlobalEntityID(EntityBeam.class, "Beam", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityBeam.class, "Beam", 0, instance, 128, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.Beam.name", "en_US", "Beam");
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

	
	public void onPlayerLogin(EntityPlayer player) {
		if(!player.getEntityData().hasKey("equippedCrystal")) {player.getEntityData().setInteger("equippedCrystal", -1);}
		PacketDispatcher.sendPacketToPlayer(PacketHandler.newPacket(, new Object[] {"equippedCrystal", player.getEntityData().getInteger("equippedCrystal")}), (Player) player);
	}

	public void onPlayerLogout(EntityPlayer player) {}
	
	public void onPlayerChangedDimension(EntityPlayer player) {}

	public void onPlayerRespawn(EntityPlayer player) {}
}
