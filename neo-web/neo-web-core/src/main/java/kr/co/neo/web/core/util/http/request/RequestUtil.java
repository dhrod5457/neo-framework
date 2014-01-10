package kr.co.neo.web.core.util.http.request;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static String getHomeUrl(HttpServletRequest request) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(request.getScheme());
		stringBuffer.append("://");
		stringBuffer.append(request.getServerName());

		if (request.getServerPort() != 80) {
			stringBuffer.append(":");
			stringBuffer.append(request.getServerPort());
		}

		stringBuffer.append(request.getContextPath());

		return stringBuffer.toString();
	}
}