package kr.co.neo.web.core.web.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

public class PageLimitTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		int pageLimit = 10; // default = 10;
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException();
		}

		pageLimit = Integer.parseInt(getBodyContent().getString().trim());
		if (pageLimit == 0) {
			pageLimit = 10;
		}
		parent.setPageLimit(pageLimit);

		return SKIP_BODY;
	}
}
