package kr.co.neo.web.servlet.file.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;

public class FileUploadEventHandler implements ApplicationListener<FileUploadEvent> {
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void onApplicationEvent(FileUploadEvent event) {
		logger.info("fileUploadEventPublish");
	}
}
