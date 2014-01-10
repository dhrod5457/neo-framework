package kr.co.neo.web.core.file;

import java.io.File;

public class FileUploadResult {
	private File file;
	private boolean isRenamed;
	private String directory;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isRenamed() {
		return isRenamed;
	}

	public void setRenamed(boolean isRenamed) {
		this.isRenamed = isRenamed;
	}

	public String getDirectory() {
		return directory == null ? "" : directory ;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getPathName(){
		return directory + "/" + file.getName();
	}
}
