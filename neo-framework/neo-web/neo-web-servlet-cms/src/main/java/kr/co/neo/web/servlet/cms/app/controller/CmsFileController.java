package kr.co.neo.web.servlet.cms.app.controller;

import java.util.HashMap;
import java.util.List;

import kr.co.neo.web.core.file.FileUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms/admin")
public class CmsFileController {
	@Autowired
	private FileUploader fileUploader;
	
	@RequestMapping(value="/file.cms",method=RequestMethod.GET)
	public ModelAndView cmsFile() throws Exception {
		ModelAndView mav = new ModelAndView("file.cms_tiles");
		
		List<HashMap<String, String>> fileList = fileUploader.getFlieList();
		mav.addObject("fileList", fileList);
		
		return mav;
	}
	
	@RequestMapping(value="/file_upload.cms",method=RequestMethod.GET)
	public ModelAndView cmsFileUpload() throws Exception {
		ModelAndView mav = new ModelAndView("file_upload.cms_tiles");
		
		return mav;
	}
	
	@RequestMapping(value="/popup_file_mce.cms")
	public ModelAndView cmsPopupFileMce() throws Exception {
		ModelAndView mav = new ModelAndView("popup/file_upload_popup");
		
		List<HashMap<String, String>> fileList = fileUploader.getFlieList();
		mav.addObject("fileList", fileList);
		
		return mav;
	}
	
	
	@RequestMapping(value="/fileAjax.cms",method=RequestMethod.POST)
	public ModelAndView cmsFileProcess() throws Exception {
		ModelAndView mav = new ModelAndView("file.cms_tiles");
		
		return mav;
	}	
}
