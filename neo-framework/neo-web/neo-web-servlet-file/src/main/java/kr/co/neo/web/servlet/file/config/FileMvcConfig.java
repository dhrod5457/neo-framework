package kr.co.neo.web.servlet.file.config;

import kr.co.neo.web.core.file.FileProperties;
import kr.co.neo.web.servlet.file.event.FileUploadEventHandler;
import kr.co.neo.web.servlet.file.event.FileUploadEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("kr.co.neo.web.servlet.file")
@EnableWebMvc
public class FileMvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private FileProperties fileProperties;

	@Bean
	public CommonsMultipartResolver multipartResolver() throws Exception {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(Long.valueOf(fileProperties.getMaxFileSize()));

		return commonsMultipartResolver;
	}

	@Bean
	public FileUploadEventHandler fileUploadEventHandler() {
		return new FileUploadEventHandler();
	}

	@Bean
	public FileUploadEventPublisher publisher() {
		return new FileUploadEventPublisher();
	}
}
