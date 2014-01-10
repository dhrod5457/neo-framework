package kr.co.neo.nativeness.core.util.properties;

import java.io.Reader;
import java.util.Properties;

public class PropertiesReader {
	private Properties properties;

	public PropertiesReader(Reader reader) {
		try {
			properties = new Properties();
			properties.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public Object getProperty(String key) {
		return properties.getProperty(key);
	}

	public String getString(String key) throws NullPointerException {
		String v = (String) getProperty(key);

		if (v == null)
			throw new NullPointerException();

		return v;
	}

	public int getInteger(String key) {
		int v = Integer.valueOf((String) getProperty(key));

		return v;
	}

	public String[] getList(String key) {
		String v = getString(key);

		return v.split(",");
	}
}
