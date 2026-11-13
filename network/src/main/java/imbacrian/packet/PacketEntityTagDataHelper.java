package imbacrian.bapilib.packet;

import com.mojang.nbt.tags.CompoundTag;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.handler.PacketHandler;
import net.minecraft.core.net.packet.Packet;

public class PacketEntityTagDataHelper extends Packet {
   public int entityId;
   public CompoundTag tag;

   public PacketEntityTagDataHelper() {
   }

   public PacketEntityTagDataHelper(Entity entity) {
      this.entityId = entity.id;
      this.tag = new CompoundTag();
      entity.addAdditionalSaveData(this.tag);
   }

   @Override
   public void read(DataInputStream dis) throws IOException {
      this.entityId = dis.readInt();
      this.tag = Packet.readCompressedCompoundTag(dis);
   }

   @Override
   public void write(DataOutputStream dos) throws IOException {
      dos.writeInt(this.entityId);
      Packet.writeCompressedCompoundTag(this.tag, dos);
   }

   @Override
   public void handlePacket(PacketHandler packetHandler) {
      // Convert to the game's PacketEntityTagData so the PacketHandler receives the expected type
      net.minecraft.core.net.packet.PacketEntityTagData gamePacket = new net.minecraft.core.net.packet.PacketEntityTagData();
      gamePacket.entityId = this.entityId;
      gamePacket.tag = this.tag;
      packetHandler.handleEntityTagData(gamePacket);
   }

   @Override
   public int getEstimatedSize() {
      return 0;
   }
}
