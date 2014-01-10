package kr.co.neo.web.core.web.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

public class ModeTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		String mode = "";
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("(Mode.class).");
		}

		mode = getBodyContent().getString().trim();
		parent.setMode(mode);

		return SKIP_BODY;
	}
}
