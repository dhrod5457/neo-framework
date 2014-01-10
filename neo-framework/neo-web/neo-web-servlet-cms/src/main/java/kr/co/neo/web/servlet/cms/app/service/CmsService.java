package kr.co.neo.web.servlet.cms.app.service;

import java.util.List;

import kr.co.neo.web.servlet.cms.app.dao.CmsDao;
import kr.co.neo.web.servlet.cms.app.domain.CmsPost;

import org.springframework.beans.factory.annotation.Autowired;

public class CmsService {
	@Autowired
	private CmsDao cmsDao;

	public List<CmsPost> getCmsPostsList(CmsPost cmsPost) {
		return cmsDao.getCmsPostsList(cmsPost);
	}

	public Integer getCmsPostsCount() {
		return cmsDao.getCmsPostsCount();
	}

	public CmsPost getCmsPost(CmsPost cmsPost) {
		return cmsDao.getCmsPost(cmsPost);
	}

	public int updateCmsPosts(CmsPost cmsPost) {
		return cmsDao.updateCmsPosts(cmsPost);
	}

	public int insertCmsPosts(CmsPost cmsPost) {
		return cmsDao.insertCmsPosts(cmsPost);
	}

	public int deleteCmsPosts(CmsPost cmsPosts) {
		return cmsDao.deleteCmsPosts(cmsPosts);
	}
}
