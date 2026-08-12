package imbacrian.bapilib;

import imbacrian.bapilib.cmd.GamemodeShort;
import net.minecraft.core.net.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class BAPILib implements ModInitializer, GameStartEntrypoint {
	public static final String MOD_ID = "bapilib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandManager.registerCommand(new GamemodeShort());
		LOGGER.info("BAPILib initialized.");
	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {
	}
}
