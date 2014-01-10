package kr.co.neo.web.core.web.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

public class PageTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		int page = 0;
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("(PagingTag.class).");
		}

		page = Integer.parseInt(getBodyContent().getString().trim());
		parent.setCurrentPage(page);

		return SKIP_BODY;
	}
}
