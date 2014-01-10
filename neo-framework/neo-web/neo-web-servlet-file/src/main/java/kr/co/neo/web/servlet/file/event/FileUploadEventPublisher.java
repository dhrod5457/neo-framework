package kr.co.neo.web.servlet.file.event;

import java.util.List;

import kr.co.neo.web.core.file.FileProperties;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadEventPublisher implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher publisher;
	private FileUploadEvent fileUploadEvent;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public void publish(List<MultipartFile> files, String callback, FileProperties fileProperties) {
		fileUploadEvent = new FileUploadEvent(this);
		fileUploadEvent.setCallback(callback);
		fileUploadEvent.setFiles(files);
		fileUploadEvent.setFileProperties(fileProperties);

		publisher.publishEvent(fileUploadEvent);
	}

	public FileUploadEvent getFileUploadEvent() {
		return fileUploadEvent;
	}
}
