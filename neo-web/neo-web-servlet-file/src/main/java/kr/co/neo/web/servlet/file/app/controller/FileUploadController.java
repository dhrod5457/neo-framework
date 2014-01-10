package kr.co.neo.web.servlet.file.app.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.neo.web.core.file.FileProperties;
import kr.co.neo.web.core.file.FileUploadResult;
import kr.co.neo.web.core.file.FileUploader;
import kr.co.neo.web.servlet.file.event.FileUploadEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	@Autowired
	private FileProperties fileProperties;

	@Autowired
	private FileUploadEventPublisher fileUploadEventPublisher;

	@Autowired
	private FileUploader fileUploader;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/upload.file", method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("file") List<MultipartFile> files, 
								  @RequestParam(required = false, value = "callback") String callback) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		
		fileUploader.setUploadPath(request.getSession().getServletContext().getRealPath("/")+"resources/fileupload");
		
		String directory = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		String uploadUrl = request.getContextPath() + fileProperties.getUploadUrl();

		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();

		for (MultipartFile file : files) {
			FileUploadResult fileUploadResult =  fileUploader.saveFile(file, directory);
			
			HashMap<String, String> r = new HashMap<String, String>();
			r.put("fileName", fileUploadResult.getFile().getName());
			r.put("fileType", file.getContentType());
			r.put("uploadUrl", uploadUrl + "/" + fileUploadResult.getPathName());

			fileUploader.insertFile(fileUploadResult.getFile().getName(), r);
			
			result.add(r);
			mav.addObject("length", file.getSize()+"");
		}

		if (callback != null) {
			fileUploadEventPublisher.publish(files, callback, fileProperties);
		}
		
		mav.addObject("uploadUrl", uploadUrl);
		mav.addObject("result", result);

		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/down.file")
	public File fileDownload(String file) throws Exception {
		fileUploader.setUploadPath(request.getSession().getServletContext().getRealPath(""));
		
		return new File(fileUploader.getUploadPath() + "/" + file);
	}
	
}
