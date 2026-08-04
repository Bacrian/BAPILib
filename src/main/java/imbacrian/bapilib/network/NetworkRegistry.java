package imbacrian.bapilib.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import net.minecraft.core.net.packet.PacketCustomPayload;

public class NetworkRegistry {
	private static final Map<String, BiConsumer<byte[], Object[] /* context */>> PACKET_HANDLERS = new HashMap<>();

	public static <T> void registerChannel(String channel, PacketDecoder<T> decoder, PacketHandler<T> handler) {
		PACKET_HANDLERS.put(channel, (data, context) -> {
			try {
				T packetData = decoder.decode(data);
				handler.handle(packetData, context);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public static void handlePayload(PacketCustomPayload payload, Object... context) {
		if (payload != null && PACKET_HANDLERS.containsKey(payload.channel)) {
			PACKET_HANDLERS.get(payload.channel).accept(payload.data, context);
		}
	}

	@FunctionalInterface
	public interface PacketDecoder<T> {
		T decode(byte[] data) throws IOException;
	}

	@FunctionalInterface
	public interface PacketHandler<T> {
		void handle(T data, Object... context);
	}
}
