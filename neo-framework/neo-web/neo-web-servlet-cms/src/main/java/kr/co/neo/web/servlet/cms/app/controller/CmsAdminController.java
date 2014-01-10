package kr.co.neo.web.servlet.cms.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms/admin")
public class CmsAdminController {
	
	@RequestMapping("/index.cms")
	public ModelAndView cmsMain() throws Exception {
		return new ModelAndView("redirect:/cms/admin/menu.cms");
	}
}
