package imbacrian.bapilib.util;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TilePosHelper {

	// Quick write to stream
	public static void write(DataOutputStream dos, TilePos pos) throws IOException {
		dos.writeInt(pos.x);
		dos.writeInt(pos.y);
		dos.writeInt(pos.z);
	}

	// Quick read from stream
	public static TilePos read(DataInputStream dis) throws IOException {
		return new TilePos(dis.readInt(), dis.readInt(), dis.readInt());
	}


	 // Obtains and safe casts a TileEntity in a given position.
	@SuppressWarnings("unchecked")
	public static <T extends TileEntity> T getAs(World world, TilePos pos, Class<T> clazz) {
		if (world == null || pos == null) return null;
		TileEntity te = world.getTileEntity(pos);
		if (clazz.isInstance(te)) {
			return (T) te;
		}
		return null;
	}
}
