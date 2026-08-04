package imbacrian.bapilib.util;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import java.lang.reflect.Method;

public class ReflectionHelper {
	private static final Logger LOGGER = LogUtils.getLogger();

	/*
	 * Invoke method with no arguments, safe way through class.
	 */
	public static boolean invokeMethod(Object target, String methodName) {
		if (target == null) return false;
		Class<?> clazz = target.getClass();

		while (clazz != null && clazz != Object.class) {
			try {
				Method method = clazz.getDeclaredMethod(methodName);
				method.setAccessible(true);
				method.invoke(target);
				return true;
			} catch (NoSuchMethodException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				LOGGER.debug("Error invoking method {} on {}", methodName, target.getClass().getSimpleName(), e);
				break;
			}
		}
		return false;
	}

	/*
	 * Invoke method with arguments
	 */
	public static boolean invokeMethodWithArg(Object target, String methodName, Class<?> argType, Object argValue) {
		if (target == null) return false;
		Class<?> clazz = target.getClass();

		while (clazz != null && clazz != Object.class) {
			try {
				Method method = clazz.getDeclaredMethod(methodName, argType);
				method.setAccessible(true);
				method.invoke(target, argValue);
				return true;
			} catch (NoSuchMethodException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				LOGGER.debug("Error invoking method {} with arg on {}", methodName, target.getClass().getSimpleName(), e);
				break;
			}
		}
		return false;
	}
}
