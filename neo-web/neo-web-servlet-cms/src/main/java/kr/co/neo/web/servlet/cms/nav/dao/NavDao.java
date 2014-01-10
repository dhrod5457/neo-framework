package kr.co.neo.web.servlet.cms.nav.dao;

import java.util.List;

import kr.co.neo.web.servlet.cms.nav.domain.NavMenu;
import kr.co.neo.web.servlet.cms.nav.domain.NavMenuGrp;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class NavDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int insertNavMenu(NavMenu navMenu) {
		sqlSessionTemplate.insert("cmsNav.insertNavMenu", navMenu);

		return navMenu.getID();
	}

	public int updateNavMenu(NavMenu navMenu) {
		int r = sqlSessionTemplate.update("cmsNav.updateNavMenu", navMenu);
		
		return r;
	}

	public int deleteNavMenu(NavMenu navMenu) {
		return sqlSessionTemplate.delete("cmsNav.deleteNavMenu", navMenu);
	}

	public NavMenu getNavMenu(NavMenu navMenu) {
		return sqlSessionTemplate.selectOne("getNavMenu", navMenu);
	}

	public List<NavMenu> getNavMenuList(NavMenu navMenu) {
		sqlSessionTemplate.clearCache();
		return sqlSessionTemplate.selectList("getNavMenuList", navMenu);
	}

	public boolean isExistsNavMenu(NavMenu navMenu) {
		return getNavMenu(navMenu) != null;
	}
	
	public int insertNavMenuGrp(NavMenuGrp navMenuGrp) {
		sqlSessionTemplate.insert("cmsNav.insertNavMenuGrp", navMenuGrp);

		return navMenuGrp.getID();
	}

	public int updateNavMenuGrp(NavMenuGrp navMenuGrp) {
		return sqlSessionTemplate.update("cmsNav.updateNavMenuGrp", navMenuGrp);
	}

	public int deleteNavMenuGrp(NavMenuGrp navMenuGrp) {
		return sqlSessionTemplate.delete("cmsNav.deleteNavMenuGrp", navMenuGrp);
	}
	
	public List<NavMenuGrp> getNavMenuGrpList() {
		return sqlSessionTemplate.selectList("cmsNav.getNavMenuGrpList");
	}
	
	
}
