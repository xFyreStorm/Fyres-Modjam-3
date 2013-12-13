package fyresmodjam3.handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {

	public static PacketType[] packetTypes = new PacketType[256];
	
	public static abstract class PacketType {
		private final byte id;
		
		public PacketType(int i) {
			this.id = (byte) i;
			
			if(packetTypes[id] != null) {throw new RuntimeException("Duplicate packet IDs! (" + id + ")");}
			else {packetTypes[id] = this;}
		}
		
		public int getID() {return id;}
		
		public abstract void process(Side side, Player player, DataInputStream inputStream) throws Exception;
	}
	
	/*public static final PacketType PLAY_SOUND = new PacketType(0) {
		public void processClient(Player player, DataInputStream inputStream) throws Exception {}
		
		public void processServer(Player player, DataInputStream inputStream) throws Exception {
			String sound = inputStream.readUTF();
			
			float x = inputStream.readFloat();
			float y = inputStream.readFloat();
			float z = inputStream.readFloat();
			
			Minecraft.getMinecraft().theWorld.playSound(x, y, z, "fyresmodjam3:" + sound, 1.0F, 1.0F, false);
		}
	};*/
	
	public static final PacketType UPDATE_STAT = new PacketType(1) {
		public void process(Side side, Player player, DataInputStream inputStream) throws Exception {
			String statName = inputStream.readUTF();
			String type = inputStream.readUTF();
			
			if(type.equals("int")) {((EntityPlayer) player).getEntityData().setInteger(statName, inputStream.readInt());}
			else if(type.equals("boolean")) {((EntityPlayer) player).getEntityData().setBoolean(statName, inputStream.readBoolean());}
			else if(type.equals("string")) {((EntityPlayer) player).getEntityData().setString(statName, inputStream.readUTF());}
			
			// TODO add more as needed
		}
	};
	
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		
		DataInputStream inputStream = null;
		byte type = 0;
		
		try {
			inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			
			if(inputStream != null) {
				type = inputStream.readByte();
				if(packetTypes[type] != null) {packetTypes[type].process(side, player, inputStream);}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {inputStream.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
	}
	
	public static Packet250CustomPayload newPacket(PacketType type, Object[] data) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		
		try {
			outputStream.writeByte(type.getID());
			
			if(data != null) {
				for(int i = 0; i < data.length; i++) {
					if(data[i] instanceof Integer) {
						outputStream.writeInt((Integer) data[i]);
					} else if(data[i] instanceof int[]) {
						for(int i2 = 0; i2 < ((int[]) data[i]).length; i2++) {outputStream.writeInt(((int[]) data[i])[i2]);}
					} else if(data[i] instanceof Boolean) {
						outputStream.writeBoolean((Boolean) data[i]);
					} else if(data[i] instanceof String) {
						outputStream.writeUTF((String) data[i]);
					} else if(data[i] instanceof Byte) {
						outputStream.writeByte((Byte) data[i]);
					} else if(data[i] instanceof Float) {
						outputStream.writeFloat((Float) data[i]);
					} else if(data[i] instanceof Double) {
						outputStream.writeDouble((Double) data[i]);
					} else if(data[i] instanceof Character) {
						outputStream.writeChar((Character) data[i]);
					}
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "FyresModjam3";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		return packet;
	}

}
