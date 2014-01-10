package kr.co.neo.web.core.config;

import java.util.List;

import kr.co.neo.web.core.converter.FileHttpMessageConverter;
import kr.co.neo.web.core.file.FileProperties;
import kr.co.neo.web.core.interceptor.DefaultInterceptor;
import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;

@Configuration
@ComponentScan({ "kr.co.neo" })
@PropertySource({"classpath:/tiles.properties","classpath:/file.properties"})
@EnableWebMvc
@Import({ CoreBeansConfig.class })
public class CoreMvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment environment;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DefaultInterceptor());
	}

	@Bean
	public JsonView jsonView() {
		JsonView jsonView = new JsonView();
		jsonView.setContentType("application/json;charset=UTF-8");
		return jsonView;
	}

	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(0);

		return beanNameViewResolver;
	}

	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setOrder(1);
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tileConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		String[] def = environment.getProperty("tiles.properties").split(",");

		tilesConfigurer.setDefinitions(def);

		return tilesConfigurer;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new FileHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(new MappingJacksonHttpMessageConverter());
	}
	
	@Bean
	public FileProperties fileProperties() {
		FileProperties fileProperties = new FileProperties();
		fileProperties.setEnvironment(environment);

		return fileProperties;
	}
}