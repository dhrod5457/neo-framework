package kr.co.neo.web.servlet.cms.nav.ui;

import javax.servlet.jsp.JspWriter;

import kr.co.neo.nativeness.core.tree.TreeNode;
import kr.co.neo.web.core.node.tree.iterator.JspTreeIterator;
import kr.co.neo.web.servlet.cms.nav.domain.NavMenu;

public class DefaultListNavMenuIterator extends JspTreeIterator<NavMenu>{
	public DefaultListNavMenuIterator(JspWriter out) {
		super(out);
	}

	@Override
	public void doStart(TreeNode<NavMenu> treeNode) throws Exception {
		NavMenu navMenu = treeNode.getData();
		
		write("<li class=\"nav-menu-item nav-menu-item-"+treeNode.getData().getID()+"\">");
		write("<a href=\""+navMenu.getUrl()+"\" target=\""+navMenu.getTarget()+"\">");
		write(navMenu.getName());
		write("</a>");
	}

	@Override
	public void startChild(TreeNode<NavMenu> node) throws Exception {
		write("<ul class=\"nav-menu-child nav-menu-depth="+node.getLevel()+" \">");
	}

	@Override
	public void endChild(TreeNode<NavMenu> node) throws Exception {
		write("</ul>");
	}

	@Override
	public void doEnd(TreeNode<NavMenu> treeNode) throws Exception {
		write("</li>");
	}
}
