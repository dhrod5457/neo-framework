package kr.co.neo.web.core.config;

import kr.co.neo.web.core.event.ApplicationEventListener;
import kr.co.neo.web.core.file.FileProperties;
import kr.co.neo.web.core.file.FileUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreBeansConfig {
	@Autowired
	private FileProperties fileProperties;

	@Bean
	public ApplicationEventListener applicationEventListener() {
		return new ApplicationEventListener();
	}

	@Bean
	public FileUploader fileUploader() {
		FileUploader uploader = new FileUploader();
		uploader.setUploadPath(fileProperties.getUploadDir());

		return uploader;
	}
}