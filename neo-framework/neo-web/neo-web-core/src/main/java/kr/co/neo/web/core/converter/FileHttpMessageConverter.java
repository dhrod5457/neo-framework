package kr.co.neo.web.core.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

public class FileHttpMessageConverter implements HttpMessageConverter<File> {
	private List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();

	public FileHttpMessageConverter() {
		this.supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
	}

	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return File.class.equals(clazz);
	}

	public List<MediaType> getSupportedMediaTypes() {
		return supportedMediaTypes;
	}

	public File read(Class<? extends File> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}

	public void write(File t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		if (contentType == null) {
			contentType = MediaType.APPLICATION_OCTET_STREAM;
		}

		outputMessage.getHeaders().setContentType(contentType);
		outputMessage.getHeaders().setContentLength(t.length());
		outputMessage.getHeaders().set("Content-Transfer-Encoding", "binary");
		outputMessage.getHeaders().set("Content-Disposition", "attachment;fileName=\"" + t.getName() + "\";");
		
		FileInputStream fis = new FileInputStream(t);
		FileCopyUtils.copy(fis, outputMessage.getBody());
		fis.close();
	}
}