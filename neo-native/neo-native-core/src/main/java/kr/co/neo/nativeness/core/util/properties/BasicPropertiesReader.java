package kr.co.neo.nativeness.core.util.properties;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class BasicPropertiesReader {
	private Class<?> clazz;

	private PropertiesReader propertiesReader;
	private String propertiesPath;

	public void setPropertiesPath(String propertiesPath) {
		this.propertiesPath = propertiesPath;

		initPropertiesReader(propertiesPath);
	}

	public void initPropertiesReader(String propertiesPath) {
		try {
			InputStream is;

			if (this.clazz != null) {
				is = this.clazz.getResourceAsStream(propertiesPath);
			} else {
				
				is = this.getClass().getClassLoader().getResourceAsStream(propertiesPath);
			}
			if (is == null) {
				throw new FileNotFoundException(" cannot be opened because it does not exist");
			}

			Reader reader = new InputStreamReader(is);
			propertiesReader = new PropertiesReader(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PropertiesReader getPropertiesReader() {
		return propertiesReader;
	}

	public String getPropertiesPath() {
		return propertiesPath;
	}
}
