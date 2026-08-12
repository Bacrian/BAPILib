package imbacrian.bapilib.util;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.world.World;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// PORT NOTE (BTA 7.3): esta versión no tiene la clase TilePos (llegó en versiones
// posteriores). Acá las posiciones son x,y,z sueltos (así los maneja TileEntity/World
// en 7.3), así que la helper quedó adaptada a esa firma en vez de envolver un objeto.
public class TilePosHelper {

	// Quick write to stream
	public static void write(DataOutputStream dos, int x, int y, int z) throws IOException {
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
	}

	// Quick read from stream
	public static int[] read(DataInputStream dis) throws IOException {
		return new int[]{dis.readInt(), dis.readInt(), dis.readInt()};
	}

	 // Obtains and safe casts a TileEntity in a given position.
	@SuppressWarnings("unchecked")
	public static <T extends TileEntity> T getAs(World world, int x, int y, int z, Class<T> clazz) {
		if (world == null) return null;
		TileEntity te = world.getTileEntity(x, y, z);
		if (clazz.isInstance(te)) {
			return (T) te;
		}
		return null;
	}
}
