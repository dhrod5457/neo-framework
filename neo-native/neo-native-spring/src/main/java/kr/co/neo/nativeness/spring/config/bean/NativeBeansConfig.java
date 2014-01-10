package kr.co.neo.nativeness.spring.config.bean;

import javax.sql.DataSource;

import kr.co.neo.nativeness.spring.option.OptionDao;
import kr.co.neo.nativeness.spring.option.OptionService;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@PropertySource("classpath:/database.properties")
public class NativeBeansConfig {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private Environment env;

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);

		return objectMapper;
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));

		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());

		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/sqlmappers/*.xml");
		
		for(int i=0;i<resources.length;i++){
			Resource resource = resources[i];
			
			logger.info("Add sqlmapper xml : " + resource.getFilename());
		}
		
		sqlSessionFactoryBean.setMapperLocations(resources);
				
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		sqlSessionTemplate.getConfiguration().setCacheEnabled(false);
		
		return sqlSessionTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
		return dataSourceTransactionManager;
	}
	
	@Bean
	public OptionDao optionDao(){
		return new OptionDao();
	}
	
	@Bean
	public OptionService optionService(){
		return new OptionService();
	}
}