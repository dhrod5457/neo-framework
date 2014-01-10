package kr.co.neo.web.servlet.file.event;

import java.util.List;

import kr.co.neo.web.core.file.FileProperties;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadEvent extends ApplicationEvent {
	private List<MultipartFile> files;
	private String callback;
	private FileProperties fileProperties;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public FileUploadEvent(Object source) {
		super(source);
	}

	public FileProperties getFileProperties() {
		return fileProperties;
	}

	public void setFileProperties(FileProperties fileProperties) {
		this.fileProperties = fileProperties;
	}

	private static final long serialVersionUID = 1L;
}
