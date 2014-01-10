package kr.co.neo.web.core.event;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {
	private Map<String, ApplicationEventHandler> events = new HashMap<String, ApplicationEventHandler>();
	private Logger logger = Logger.getLogger(getClass());
	
	public void setEvents(Map<String, ApplicationEventHandler> events) {
		this.events = events;
	}

	public Map<String, ApplicationEventHandler> getEvents() {
		return events;
	}

	public void addEventHandler(String key, ApplicationEventHandler applicationEventHandler) {
		events.put(key, applicationEventHandler);
	}

	public void removeEventHandler(String key) {
		events.remove(key);
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		ApplicationEventHandler applicationEventHandler = events.get(event.getClass().getName());
		
		if (applicationEventHandler != null) {
			try {
				logger.info("published event " + event.getClass().getSimpleName());
				
				applicationEventHandler.handle(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
