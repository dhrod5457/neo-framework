package kr.co.neo.web.servlet.cms.config;

import kr.co.neo.web.servlet.cms.app.dao.CmsDao;
import kr.co.neo.web.servlet.cms.app.service.CmsService;
import kr.co.neo.web.servlet.cms.nav.dao.NavDao;
import kr.co.neo.web.servlet.cms.nav.service.NavMenuService;
import kr.co.neo.web.servlet.cms.nav.ui.NavMaker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CmsBeansConfig {
	@Bean
	public CmsDao cmsDao() {
		return new CmsDao();
	}

	@Bean
	public CmsService cmsService() {
		return new CmsService();
	}

	@Bean
	public NavDao navDao() {
		return new NavDao();
	}

	@Bean
	public NavMenuService navMenuService() {
		return new NavMenuService();
	}

	@Bean
	public NavMaker navMaker() {
		return new NavMaker();
	}
}