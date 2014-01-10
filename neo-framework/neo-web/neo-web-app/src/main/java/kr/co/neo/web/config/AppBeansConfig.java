package kr.co.neo.web.config;

import kr.co.neo.nativeness.spring.option.OptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeansConfig {
	@Autowired
	OptionService optionService;
}
