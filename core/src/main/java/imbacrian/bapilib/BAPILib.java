package imbacrian.bapilib;

import java.util.stream.Collectors;

import imbacrian.bapilib.cmd.GamemodeShort;
import net.minecraft.core.net.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class BAPILib implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("bapilib", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandManager.registerCommand(new GamemodeShort());
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		LOGGER.info("BAPILib initialized.");
		logDependingMods();
	}

	private void logDependingMods() {
        var dependentMods = FabricLoader.getInstance().getAllMods().stream()
            .filter(mod -> {
                if (mod.getMetadata().getId().equals(MOD_ID)) {
                    return false;
                }
                boolean usesBapi = false;
				for (net.fabricmc.loader.api.metadata.ModDependency dep : mod.getMetadata().getDependencies()) {
                    if (dep.getModId().equals(MOD_ID)) {
                        usesBapi = true;
                        break;
                    }
                }
                return usesBapi;
            })
            .map(mod -> mod.getMetadata().getName() + " (" + mod.getMetadata().getId() + ")")
            .collect(Collectors.toSet());

        if (dependentMods.isEmpty()) {
            LOGGER.info("No external mods currently using BAPILib.");
        } else {
            LOGGER.info("Current mods using BAPILib ({}):", dependentMods.size());
            for (String modInfo : dependentMods) {
                LOGGER.info(" - {}", modInfo);
            }
        }
    }

	public void beforeGameStart() {

	}

	public void afterGameStart() {
		// Here you can initialize your keybind category helper
		// by calling the init() method of your file class.
	}
}
