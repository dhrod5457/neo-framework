package kr.co.neo.web.core.event;

import org.springframework.context.ApplicationEvent;

public interface ApplicationEventHandler {
	public void handle(ApplicationEvent applicationEvent) throws Exception;
}
