package kr.co.neo.nativeness.core.util.reflection.log;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class ReflectionLog {
	private final static Logger logger = Logger.getLogger(ReflectionLog.class);

	public static void objectPrint(Object object) {
		try {
			Method[] methods = object.getClass().getDeclaredMethods();

			for (Method method : methods) {
				method.setAccessible(true);
				String methodName = method.getName();

				if (methodName.indexOf("get") != -1) {
					String key = methodName.replace("get", "").toLowerCase();
					Object value = (Object) method.invoke(object);

					if (value == null) {
						value = "";
					}

					logger.info("key : " + key + ", value : " + value);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
