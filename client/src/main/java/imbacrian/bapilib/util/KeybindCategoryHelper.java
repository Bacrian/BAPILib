package imbacrian.bapilib.util;

import net.minecraft.client.gui.options.components.KeyBindingComponent;
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.input.InputDevice;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeybindCategoryHelper {

	public KeyBinding registerKey(String translationKey, int defaultKey) {
		return GameSettings.register(
			new KeyBinding(translationKey).setDefault(InputDevice.keyboard, defaultKey)
		);
	}

	private static boolean addedCustomCategory = false;
	public void addCategory(String translationKey, KeyBinding... keyBindings) {
		if (!addedCustomCategory) {
			try {
				OptionsCategory category = new OptionsCategory(translationKey);
				for (KeyBinding kb : keyBindings) {
					category.withComponent(new KeyBindingComponent(kb));
				}
				OptionsPages.CONTROLS.withComponent(category);
				addedCustomCategory = true;
			} catch (Exception e) {
				// Retries or ignores if the controls page is not ready.
			}
		}
	}
}

// To make ts work: You have to make a class in your mod,
// whatever you'd like to name it, and make it have init()
// as a public static void and then, you call all this stuff
// Example:
/* public class testFile {
	private static final KeybindCategoryHelper helper = new KeybindCategoryHelper();
	public static void init() {
		KeyBinding k1 = helper.registerKey("key.test.1", Keyboard.KEY_L);
		KeyBinding k2 = helper.registerKey("key.test.2", Keyboard.KEY_M);

		helper.addCategory("options.category.test", k1, k2);
	}
  }
*/
// Then, on your main entrypoint, go and add it on afterGameStart(),
// really, it's that simple. Go nuts.
// - Bacrian.

