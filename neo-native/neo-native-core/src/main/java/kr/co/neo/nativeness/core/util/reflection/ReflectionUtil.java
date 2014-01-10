package kr.co.neo.nativeness.core.util.reflection;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ReflectionUtil {
	public static String stringFirstLower(String data) {

		String transString = data.substring(0, 1);
		transString = transString.toLowerCase();
		transString += data.substring(1);

		return transString;
	}

	/**
	 * 객체를 맵으로 변환한다.
	 * 
	 * @param object
	 * @param includeNullProperty
	 *            true : null포함 flase : 미포함
	 * @return
	 */
	public static HashMap<String, Object> objectToMap(Object object, boolean includeNullProperty) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			Method[] methods = object.getClass().getDeclaredMethods();

			for (Method method : methods) {
				method.setAccessible(true);
				String methodName = method.getName();

				if (methodName.indexOf("get") != -1) {
					String key = methodName.replace("get", "");
					key = stringFirstLower(key);

					Object value = (Object) method.invoke(object);

					if (value == null) {
						value = "";
					}

					if (includeNullProperty) {
						map.put(key, value);
					} else {
						if (!value.equals("")) {
							map.put(key, value);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
}