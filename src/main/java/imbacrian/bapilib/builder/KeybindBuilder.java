package imbacrian.bapilib.builder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.input.InputDevice;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.KeyBinding;

public class KeybindBuilder {

    public static KeyBinding registerKey(String translationKey, int defaultKey) {
        return GameSettings.register(
            new KeyBinding(translationKey).setDefault(InputDevice.keyboard, defaultKey)
        );
    }

    //Generic utility method - GUI
    public static void handleKeyInputGUIScreen(Minecraft mc, KeyBinding keyBinding, net.minecraft.client.gui.Screen targetScreen) {
        if (mc.thePlayer != null && keyBinding.isPressed()) {
            if (mc.currentScreen == null) {
                mc.displayScreen(targetScreen);
            }
        }
    }
}
