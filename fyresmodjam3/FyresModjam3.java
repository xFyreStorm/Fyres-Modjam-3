package fyresmodjam3;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import fyresmodjam3.blocks.BlockCrystal;
import fyresmodjam3.handlers.CommonTickHandler;
import fyresmodjam3.handlers.PacketHandler;
import fyresmodjam3.items.ItemCrystal;
import fyresmodjam3.tileentities.TileEntityCrystal;

@Mod(modid = "fyresmodjam3", name = "Fyres Modjam 3", version = "0.0.1a")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"FyresModjam3"}, packetHandler = PacketHandler.class)
public class FyresModjam3 {
	
	//Theme... laser shooting sceptres, lore, crystals which modify player and sceptre?
	
	//reminders
	public static Item sceptre;
	public static Item crystalItem;
	
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
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		proxy.register();
		
		crystalItem = new ItemCrystal(itemID);
		
		crystal = new BlockCrystal(itemID + 256 /*for itemblock purposes*/).setCreativeTab(CreativeTabs.tabMaterials); //Remember to remove from creative tabs later!
		GameRegistry.registerBlock(crystal, "crystal");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "Crystal Tile Entity");
		LanguageRegistry.addName(crystal, "Crystal");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
