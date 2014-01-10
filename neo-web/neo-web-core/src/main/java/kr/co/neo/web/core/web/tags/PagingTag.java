package kr.co.neo.web.core.web.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class PagingTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String function;
	private String mode = "";
	private int currentPage;
	private int totalRows;
	private int pageSize;
	private int pageLimit;

	public PagingTag() {}

	public int doStartTag() {
		return 1;
	}

	public int doEndTag() {
		StringBuffer uri = new StringBuffer();
		int totalPage = totalRows / pageSize;
		if (totalRows % pageSize > 0)
			totalPage++;
		
		int nowGroup = ((currentPage % pageLimit) == 0) ? (currentPage / pageLimit) - 1 : currentPage / pageLimit;
		
		int totalGroup = totalPage / pageLimit;
		
		if(mode.equals("list")){
			if (totalPage % pageLimit > 0)
				totalGroup++;
				uri.append("<div class=\"pagination\">");
				uri.append("<ul>");
			if (nowGroup <= 0) {
				//uri.append("<span class=\"paging_prev\">이전 목록으로</span>");
			} else
				uri.append(
						(new StringBuilder()).append("<li class=\"paging_first\"><a href=\"JavaScript:")
								.append(function).append("('")
								.append(((nowGroup - 1) * pageLimit) + 1).toString())
						.append((new StringBuilder()).append("','")
								.append(nowGroup - 1).append("')\">").toString())
						.append("이전</a></li>");
			
			int doStart = nowGroup * pageLimit;
			doStart = ((currentPage % pageLimit) == 0) ? currentPage
					- (pageLimit - 1) : ((currentPage / pageLimit) * pageLimit) + 1;

			int doEnd = doStart + pageLimit;
			if (totalRows == 0)
				doEnd = 1 + 1;
			if (totalGroup - 1 == nowGroup)
				doEnd = totalPage + 1;
			for (int i = doStart; i < doEnd; i++)
				if ((i) == currentPage)
					uri.append((new StringBuilder())
							.append("<li class='on active'><a href='#' title=\"현재페이지\" >")
							.append(i).append("</a></li>").toString());
				else
					uri.append(
							(new StringBuilder()).append("<li><a href=\"JavaScript:")
									.append(function).append("('").append(i)
									.toString()).append(
							(new StringBuilder()).append("',").append("this")
									.append(")\">").append(i).append("</a></li>")
									.toString());
			
			//uri.append("<span class=\"next\">");
			if (nowGroup < totalGroup - 1){
				uri.append(
						(new StringBuilder()).append("<li  class=\"paging_last\" ><a href=\"JavaScript:")
								.append(function).append("('")
								.append(((nowGroup + 1) * pageLimit) + 1).toString())
						.append((new StringBuilder()).append("','")
								.append(nowGroup + 1).append("')\">").toString())
						.append("다음</a></li>");
			}
				
			else {
				//uri.append("<span class=\"paging_next\" >다음 목록으로</span>");
			}
			
			uri.append("</ul>");
			
		}else{
			if (totalPage % pageLimit > 0)
				totalGroup++;
				uri.append("<div class=\"pagination\">");
			if (nowGroup <= 0) {
				//uri.append("<span class=\"paging_prev\">이전 목록으로</span>");
			} else
				uri.append(
						(new StringBuilder()).append("<a  class=\"paging_first\" href=\"JavaScript:")
								.append(function).append("('")
								.append(((nowGroup - 1) * pageLimit) + 1).toString())
						.append((new StringBuilder()).append("','")
								.append(nowGroup - 1).append("')\">").toString())
						.append("이전</a>");
			int doStart = nowGroup * pageLimit;
			doStart = ((currentPage % pageLimit) == 0) ? currentPage
					- (pageLimit - 1) : ((currentPage / pageLimit) * pageLimit) + 1;

			int doEnd = doStart + pageLimit;
			if (totalRows == 0)
				doEnd = 1 + 1;
			if (totalGroup - 1 == nowGroup)
				doEnd = totalPage + 1;
			for (int i = doStart; i < doEnd; i++)
				if ((i) == currentPage)
					uri.append((new StringBuilder())
							.append("<a href='#' class='on active' title=\"현재페이지\" >")
							.append(i).append("</a>").toString());
				else
					uri.append(
							(new StringBuilder()).append("<a href=\"JavaScript:")
									.append(function).append("('").append(i)
									.toString()).append(
							(new StringBuilder()).append("',").append("this")
									.append(")\">").append(i).append("</a>")
									.toString());
			
			//uri.append("<span class=\"next\">");
			if (nowGroup < totalGroup - 1){
				uri.append(
						(new StringBuilder()).append("<a  class=\"paging_last\" href=\"JavaScript:")
								.append(function).append("('")
								.append(((nowGroup + 1) * pageLimit) + 1).toString())
						.append((new StringBuilder()).append("','")
								.append(nowGroup + 1).append("')\">").toString())
						.append("다음</a>");
			}
				
			else {
				//uri.append("<span class=\"paging_next\" >다음 목록으로</span>");
			}
		}
		
		
		
		uri.append("</div>");

		JspWriter out = pageContext.getOut();
		
		try {
			out.print(uri.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
