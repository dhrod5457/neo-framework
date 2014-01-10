package kr.co.neo.web.servlet.cms.nav.ui;

import kr.co.neo.nativeness.core.tree.TreeNode;
import kr.co.neo.nativeness.core.tree.TreeNodeIterator;
import kr.co.neo.nativeness.core.tree.TreeNodeManager;
import kr.co.neo.web.servlet.cms.nav.domain.NavMenu;
import kr.co.neo.web.servlet.cms.nav.service.NavMenuService;

import org.springframework.beans.factory.annotation.Autowired;

public class NavMaker {
	@Autowired
	private NavMenuService navMenuService;

	public void renderNavMenu(int grpId, TreeNodeIterator<NavMenu> treeNodeIterator) {
		NavMenu navMenu = new NavMenu();
		navMenu.setGrp(grpId);

		TreeNodeManager tm = navMenuService.getNavMenuList(navMenu);

		tm.iterating(treeNodeIterator);
	}

	public void renderSubMenu(int grpId, String url, TreeNodeIterator<NavMenu> treeNodeIterator) {
		NavMenu navMenu = new NavMenu();
		navMenu.setGrp(grpId);
		
		TreeNodeManager tm = navMenuService.getNavMenuList(navMenu);
		TreeNode<NavMenu> node = navMenuService.findNodeByUrl(url, tm);
		
		if(node != null){
			node = navMenuService.findOneDepthMenu(node);
			
			TreeNodeManager treeNodeManager = new TreeNodeManager(node);
			treeNodeManager.iterating(treeNodeIterator);	
		}
	}
}
