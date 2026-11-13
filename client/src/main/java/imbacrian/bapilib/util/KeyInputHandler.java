package imbacrian.bapilib.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.KeyBinding;

public class KeyInputHandler {

    //Generic utility method - GUI
    public static void handleKeyInputGUIScreen(Minecraft mc, KeyBinding keyBinding, net.minecraft.client.gui.Screen targetScreen) {
        if (mc.thePlayer != null && keyBinding.isPressed()) {
            if (mc.currentScreen == null) {
                mc.displayScreen(targetScreen);
            }
        }
    }

	// I don't recommend using this, specially since it's for some cases
	// and not exactly the best way to handle keybinds.
	// I'd recommend adding the checks manually, looking for whatever you need.
}
