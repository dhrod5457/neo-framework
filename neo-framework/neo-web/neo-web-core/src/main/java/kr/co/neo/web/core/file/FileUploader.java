package kr.co.neo.web.core.file;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import kr.co.neo.nativeness.spring.option.OptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class FileUploader {
	@Autowired
	private OptionService optionService;

	private String uploadPath;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public static String UPLOAD_GROUP_NAME = "UPLOAD_FILE";
	public static String UPLOAD_FILE_PREFIX = "UPLOAD_FILE_";

	public void insertFile(String name, HashMap<String, String> fileMap) throws Exception {
		optionService.insertOption(UPLOAD_FILE_PREFIX + name, fileMap, UPLOAD_GROUP_NAME);
	}

	public List<HashMap<String, String>> getFlieList() throws Exception {
		return optionService.getOptionListByGroup(UPLOAD_GROUP_NAME, HashMap.class);
	}

	public HashMap<String, Object> getFileMap(String name) throws Exception {
		return optionService.getOption(UPLOAD_FILE_PREFIX + name);
	}

	public File getFile(String name) throws Exception {
		HashMap<String, Object> m = getFileMap(name);

		if (m != null) {
			String n = (String) m.get("fileName");
			String fileName = uploadPath + "/" + n;

			return new File(fileName);
		}

		return null;
	}

	public void deleteFile(String name) throws Exception {
		File f = getFile(name);
		
		if(f.isFile()){
			f.delete();
			optionService.deleteOption(UPLOAD_FILE_PREFIX + name);
		}
	}

	public FileUploadResult saveFile(MultipartFile file, String directory) throws Exception {
		FileUploadResult fileUploadResult = new FileUploadResult();
		fileUploadResult.setDirectory(directory);

		String fileName = file.getOriginalFilename();
			   fileName = fileName.replaceAll("\\s", "");
	    
		if (directory != null) {
			fileName = directory + "/" + fileName;
		}
		
		String uploadFileName = uploadPath + "/" + fileName;

		File saveFile = new File(uploadFileName);

		if (saveFile.isFile()) {
			fileName = System.currentTimeMillis() + "_" + fileName;
			
			fileUploadResult.setRenamed(true);
		}
		
		if (!saveFile.getParentFile().isDirectory()) {
			saveFile.mkdirs();
		}

		file.transferTo(saveFile);

		fileUploadResult.setFile(saveFile);

		return fileUploadResult;
	}

	public boolean isExsitsFile(String name) {
		return optionService.isOptionExists(UPLOAD_FILE_PREFIX + name);
	}

}