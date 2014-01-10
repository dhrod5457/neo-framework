package kr.co.neo.web.core.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class ApplicationEventAggregator implements ApplicationEventHandler {
	private List<ApplicationEventHandler> handlerList;

	public List<ApplicationEventHandler> getHandlerList() {
		return handlerList;
	}

	public void setHandlerList(List<ApplicationEventHandler> handlerList) {
		this.handlerList = handlerList;
	}

	@Override
	public void handle(ApplicationEvent applicationEvent) throws Exception {
		for (ApplicationEventHandler handler : handlerList) {
			handler.handle(applicationEvent);
		}
	}
}
