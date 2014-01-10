package kr.co.neo.web.core.file;

import org.springframework.core.env.Environment;

public class FileProperties {
	private Environment environment;

	public String getUploadDir() {
		return environment.getProperty("uploadDir");
	}

	public String getUploadUrl() {
		return environment.getProperty("uploadUrl");
	}

	public long getMaxFileSize() {
		return Long.valueOf(environment.getProperty("maxUploadSize"));
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
