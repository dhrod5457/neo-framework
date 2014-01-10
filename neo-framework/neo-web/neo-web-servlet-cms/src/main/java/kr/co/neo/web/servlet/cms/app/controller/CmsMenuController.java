package kr.co.neo.web.servlet.cms.app.controller;

import java.util.List;

import javax.validation.Valid;

import kr.co.neo.nativeness.core.tree.TreeNodeManager;
import kr.co.neo.web.servlet.cms.app.domain.CmsPost;
import kr.co.neo.web.servlet.cms.app.service.CmsService;
import kr.co.neo.web.servlet.cms.nav.domain.NavMenu;
import kr.co.neo.web.servlet.cms.nav.domain.NavMenuGrp;
import kr.co.neo.web.servlet.cms.nav.service.NavMenuService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms/admin")
public class CmsMenuController {
	@Autowired
	private NavMenuService navMenuService;

	@Autowired
	private CmsService cmsService;
	
	@RequestMapping(value="/menu.cms" , method = RequestMethod.GET)
	public ModelAndView cmsMenu(NavMenu navMenu, NavMenuGrp navMenuGrp) throws Exception {
		ModelAndView mav = new ModelAndView("menu.cms_tiles");

		if(!"createMenu".equals(navMenu.getCmd())){
			List<NavMenuGrp> navMenuGrpList = navMenuService.getNavMenuGrpList();
			
			if(navMenu.getGrp() == -1){
				if(navMenuGrpList.size() > 0)
					navMenu.setGrp(navMenuGrpList.get(0).getID());
			}
			
			mav.addObject("navMenuGrpList", navMenuGrpList);
			
			TreeNodeManager menuTree = navMenuService.getNavMenuList(navMenu);
			mav.addObject("menuTree", menuTree);
			
			CmsPost cmsPost = new CmsPost();
			cmsPost.setPageLimit(10000);
			
			List<CmsPost> cmsPostList = cmsService.getCmsPostsList(cmsPost);
			mav.addObject("cmsPostList", cmsPostList);
			
		}
		
		return mav;
	}

	@RequestMapping(value="/menu.cms" , method = RequestMethod.POST)
	public ModelAndView cmsMenuProcess(NavMenuGrp navMenuGrp,@Valid NavMenu navMenu, BindingResult result) throws Exception {
		String cmd = navMenu.getCmd();
		
		if("createLink".equals(cmd)){
			if(result.hasErrors()){
				return cmsMenu(navMenu, navMenuGrp);
			}
			
			navMenu.setOrd(2000);
			navMenuService.insertNavMenu(navMenu);
		}
		
		if("deleteMenu".equals(cmd)){
			navMenuService.deleteNavMenuGrp(navMenuGrp);
			
			return new ModelAndView("redirect:/cms/admin/menu.cms");
		}
		
		return new ModelAndView("redirect:/cms/admin/menu.cms?grp=" + navMenu.getGrp());
	}
	
	@RequestMapping(value="/menuGrpProcess.cms" , method = RequestMethod.POST)
	public String cmsMenuGrpProcess(NavMenu navMenu, @Valid NavMenuGrp navMenuGrp ,BindingResult result ) {
		
		String cmd = navMenuGrp.getCmd();
		
		if ("createMenu".equals(cmd)) {
			if(result.hasErrors()){
				return "menu.cms_tiles";
			}
			
			navMenuService.insertNavMenuGrp(navMenuGrp);
			
			return "redirect:/cms/admin/menu.cms?grp=" + navMenuGrp.getID();
		}
		
		return null;
	}
	
	@RequestMapping(value="/menuAjax.cms" )
	@ResponseBody
	public ModelAndView cmsAjaxProcess(@RequestParam("cmd")String cmd
							    ,@RequestParam(value="menuJson",required=false) String menuJson
							    ,NavMenu navMenu){
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		if("addMenu".equals(cmd)){
			JsonConvert(new JSONArray(menuJson),-1);
		}		
		
		if("updateMenu".equals(cmd)){
			navMenuService.updateNavMenu(navMenu);
		}
		
		if("deleteMenu".equals(cmd)){
			navMenuService.deleteNavMenu(navMenu);
		}
		
		return mav;
	}
	
	private void JsonConvert(JSONArray jsonArray,int parent){
		for(int i=0;i<jsonArray.length();i++){
			JSONObject json = jsonArray.getJSONObject(i);
			NavMenu menu = navMenuService.getNavMenu(new NavMenu(json.getInt("id")));
			
			menu.setOrd(i);
			
			if(parent != -1){
				menu.setParent(parent);
			}else{
				menu.setParent(0);
			}
			
			navMenuService.updateNavMenu(menu);
			
			if(json.has("children")){
				JsonConvert(json.getJSONArray("children") , json.getInt("id"));
			}
		}
	}}