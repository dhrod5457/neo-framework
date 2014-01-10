package kr.co.neo.web.core.node.tree.iterator;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import kr.co.neo.nativeness.core.tree.TreeNodeIterator;

public abstract class JspTreeIterator<T> implements TreeNodeIterator<T>{
	private JspWriter out;

	public JspWriter getOut() {
		return out;
	}

	public void setOut(JspWriter out) {
		this.out = out;
	}

	public JspTreeIterator(JspWriter out) {
		this.out = out;
	}
	
	public void write(Object o){
		try {
			out.println(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
