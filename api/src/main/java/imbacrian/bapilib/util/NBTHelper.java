package imbacrian.bapilib.util;

import com.mojang.nbt.tags.CompoundTag;

public class NBTHelper {

	public static float getFloatOrDefault(CompoundTag tag, String key, float defaultValue) {
		if (tag != null && tag.containsKey(key)) {
			return tag.getFloat(key);
		}
		return defaultValue;
	}

	public static String getStringOrDefault(CompoundTag tag, String key, String defaultValue) {
		if (tag != null && tag.containsKey(key)) {
			return tag.getString(key);
		}
		return defaultValue;
	}
}
