package kr.co.neo.web.config;

import kr.co.neo.web.core.option.service.OptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeansConfig {
	@Autowired
	OptionService optionService;
}
