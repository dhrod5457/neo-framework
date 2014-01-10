package kr.co.neo.web.servlet.cms.app.controller;

import kr.co.neo.nativeness.spring.option.OptionService;
import kr.co.neo.web.servlet.cms.app.domain.CmsSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms/admin")
public class CmsSettingController {
	@Autowired
	private OptionService optionService;

	@RequestMapping(value = "/setting.cms", method = RequestMethod.GET)
	public ModelAndView cmsSetting(CmsSetting cmsSetting) throws Exception {
		ModelAndView mav = new ModelAndView("setting.cms_tiles");

		CmsSetting oldSetting = optionService.getOption("cmsOption", CmsSetting.class);

		if (oldSetting != null) {
			cmsSetting.setTitle(oldSetting.getTitle());
			cmsSetting.setSub_title(oldSetting.getSub_title());
		}

		return mav;
	}

	@RequestMapping(value = "/setting.cms", method = RequestMethod.POST)
	public ModelAndView cmsProcess(CmsSetting cmsSetting) throws Exception {
		optionService.insertOption("cmsOption", cmsSetting);

		return new ModelAndView("redirect:/cms/admin/setting.cms");
	}
}
