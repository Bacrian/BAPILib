package imbacrian.bapilib.util;

import net.minecraft.client.resource.language.I18n;

public class LangHelper {

	/*
	 * Translate a key using Minecraft's I18n system in a simplified way.
	 *
	 * key        The translation key (e.g: "gui.bapilib.title").
	 * return     The translated text, or the same key if it cannot be found.
	 */
	public static String get(String key) {
		try {
			return I18n.getInstance().translateKey(key);
		} catch (Exception e) {
			return key;
		}
	}

	/*
	 * Translate a key by applying dynamic formatting with arguments (type String.format).
	 *
	 * key       The translation key with arguments (e.g.: "gui.bapilib.hello=Hi %s").
	 * args      The values that'll replace the arguments.
	 * return    The translated and formatted text.
	 */
	public static String getFormatted(String key, Object... args) {
		String translated = get(key);
		try {
			return String.format(translated, args);
		} catch (Exception e) {
			return translated;
		}
	}

	/*
	 * Check if a translation key exists or if it returns the same key by default.
	 *
	 * key             The key to check.
	 * return true     If the key has a registered translation other than itself.
	 */
	public static boolean hasTranslation(String key) {
		String translated = I18n.getInstance().translateKey(key);
		return translated != null && !translated.equals(key);
	}
}


/// EXAMPLES OF USE

/// Simple translation
//String title = LangHelper.get("bapilib.title");

/// Dynamic translation with variables (e.g: "User: %s | Points: %d")
// String scoreText = LangHelper.getFormatted("gui.stats.score", "Bacrian", 100);

/// Checking if a key exists before using it to avoid visual errors.
// if (LangHelper.hasTranslation("mod.custom.tooltip")) {
// 	String tooltip = LangHelper.get("mod.custom.tooltip");
// }
