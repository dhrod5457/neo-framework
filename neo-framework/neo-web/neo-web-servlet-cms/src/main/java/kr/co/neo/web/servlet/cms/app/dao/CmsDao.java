package kr.co.neo.web.servlet.cms.app.dao;

import java.util.List;

import kr.co.neo.web.servlet.cms.app.domain.CmsPost;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class CmsDao {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List<CmsPost> getCmsPostsList(CmsPost cmsPost) {
		return sessionTemplate.selectList("cms.getCmsPostsList",cmsPost);
	}
	
	public Integer getCmsPostsCount(){
		return sessionTemplate.selectOne("cms.getCmsPostsCount");
	}
	
	public CmsPost getCmsPost(CmsPost cmsPost){
		return sessionTemplate.selectOne("cms.getCmsPost",cmsPost);
	}
	
	public int updateCmsPosts(CmsPost cmsPost) {
		return sessionTemplate.update("cms.updateCmsPosts", cmsPost);
	} 
	
	public int insertCmsPosts(CmsPost cmsPost) {
		return sessionTemplate.insert("cms.insertCmsPosts", cmsPost);
	}

	public int deleteCmsPosts(CmsPost cmsPost) {
		return sessionTemplate.delete("cms.deleteCmsPosts", cmsPost);
	}
}
