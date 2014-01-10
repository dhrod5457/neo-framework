package kr.co.neo.web.servlet.cms.app.controller;

import java.util.List;

import javax.validation.Valid;

import kr.co.neo.web.servlet.cms.app.domain.CmsPost;
import kr.co.neo.web.servlet.cms.app.service.CmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms/admin")
public class CmsPostController {
	@Autowired
	private CmsService cmsService;

	@RequestMapping("/content.cms")
	public ModelAndView cmsContent(CmsPost cmsPost) throws Exception {
		ModelAndView mav = new ModelAndView("content.cms_tiles");

		List<CmsPost> cmsPostList = cmsService.getCmsPostsList(cmsPost);
		cmsPost.setTotalSize(cmsService.getCmsPostsCount());

		mav.addObject("cmsPostList", cmsPostList);

		return mav;
	}

	@RequestMapping("/content_detail.cms")
	public ModelAndView cmsContentDetail(CmsPost cmsPost) throws Exception {
		ModelAndView mav = new ModelAndView("content_detail.cms_tiles");

		CmsPost cmsPostDetail = cmsService.getCmsPost(cmsPost);
		mav.addObject("cmsPostDetail", cmsPostDetail);

		return mav;
	}

	@RequestMapping("/content_edit.cms")
	public ModelAndView cmsContentEdit(CmsPost cmsPost) throws Exception {
		ModelAndView mav = new ModelAndView("content_edit.cms_tiles");

		if(cmsPost.getID() != -1){
			CmsPost oldPost = cmsService.getCmsPost(cmsPost);
			cmsPost.setTitle(oldPost.getTitle());
			cmsPost.setContent(oldPost.getContent());
		}
		
		return mav;
	}
	
	@RequestMapping("/contentProcess.cms")
	public ModelAndView ajaxPostProcess(@Valid CmsPost cmsPost, BindingResult result,@RequestParam("cmd") String cmd) throws Exception{
		
		if ("add".equals(cmd)) {
			if(result.hasErrors())
				return cmsContentEdit(cmsPost);
			
			cmsService.insertCmsPosts(cmsPost);
		} else if ("update".equals(cmd)) {
			if(result.hasErrors())
				return cmsContentEdit(cmsPost);
			
			cmsService.updateCmsPosts(cmsPost);
		} else if ("delete".equals(cmd)) {
			cmsService.deleteCmsPosts(cmsPost);
		}

		return new ModelAndView("redirect:/cms/admin/content.cms");
	}
}