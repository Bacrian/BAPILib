package imbacrian.bapilib.util;

import com.mojang.logging.LogUtils;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.packet.PacketEntityTagData;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;

public class EntitySyncHelper {
	private static final Logger LOGGER = LogUtils.getLogger();

	/*
	 * Marks an entity as dirty and syncs its NBT data with every client following it.
	 */
	public static void syncEntityData(MinecraftServer server, Entity entity) {
		if (entity == null || server == null) return;

		try {
			entity.additionalDataChanged = true;
			entity.sendAdditionalData = true;

			int dimensionId = entity.world.dimension.id;

			server.getEntityTracker(dimensionId).sendPacketToTrackedPlayers(
				entity,
				new PacketEntityTagData(entity)
			);
		} catch (Exception e) {
			LOGGER.warn("Failed to sync entity data for entity ID: {}", entity.id, e);
		}
	}
}
