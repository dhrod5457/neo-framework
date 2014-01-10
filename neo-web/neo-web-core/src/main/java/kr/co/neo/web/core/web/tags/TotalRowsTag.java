package kr.co.neo.web.core.web.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

public class TotalRowsTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		int totalPage = 0;
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("(PagingTag).");
		}

		totalPage = Integer.parseInt(getBodyContent().getString().trim());
		parent.setTotalRows(totalPage);

		return SKIP_BODY;
	}
}
