package kr.co.neo.web.servlet.cms.nav.service;

import java.util.List;

import kr.co.neo.nativeness.core.tree.TreeNode;
import kr.co.neo.nativeness.core.tree.TreeNodeManager;
import kr.co.neo.web.servlet.cms.nav.dao.NavDao;
import kr.co.neo.web.servlet.cms.nav.domain.NavMenu;
import kr.co.neo.web.servlet.cms.nav.domain.NavMenuGrp;

import org.springframework.beans.factory.annotation.Autowired;

public class NavMenuService {
	@Autowired
	private NavDao navDao;

	public int insertNavMenu(NavMenu navMenu) {
		return navDao.insertNavMenu(navMenu);
	}

	public int updateNavMenu(NavMenu n) {
		NavMenu navMenu = getNavMenu(n);
		
		navMenu.setParent(n.getParent());
		navMenu.setOrd(n.getOrd());
		navMenu.setID(n.getID());
		
		return navDao.updateNavMenu(navMenu);
	}

	public int deleteNavMenu(NavMenu navMenu) {
		return navDao.deleteNavMenu(navMenu);
	}

	public NavMenu getNavMenu(NavMenu navMenu) {
		return navDao.getNavMenu(navMenu);
	}

	@SuppressWarnings("unchecked")
	public TreeNode<NavMenu> findNodeById(final int ID, TreeNodeManager treeNodeManager) {
		return treeNodeManager.findNode(new Comparable<NavMenu>() {
			@Override
			public int compareTo(NavMenu o) {
				if (o.getID() == ID) {
					return 0;
				}

				return 1;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public TreeNode<NavMenu> findNodeByUrl(final String url, TreeNodeManager treeNodeManager) {
		return treeNodeManager.findNode(new Comparable<NavMenu>() {
			@Override
			public int compareTo(NavMenu o) {
				if(o.getUrl() == null)
					return 1;
				
				if (url.contains(o.getUrl())) {
					return 0;
				}

				return 1;
			}
		});
	}
	
	public TreeNode<NavMenu> findOneDepthMenu(TreeNode<NavMenu> tree){
		TreeNode<NavMenu> parentNode = tree.getParent();
		
		NavMenu menu = parentNode.getData();
		
		if(menu.getParent() == 0){
			if(parentNode.getData().getName() == null){
				return tree;
			}
			
			return parentNode;
		}
		
		return findOneDepthMenu(parentNode);
	}

	public void _makeNavMenuList(List<NavMenu> navMenuList, TreeNodeManager treeNodeManager) {
		for (int i = 0; i < navMenuList.size(); i++) {
			NavMenu nav = navMenuList.get(i);

			if (nav.getParent() != 0) {
				TreeNode<NavMenu> parent = findNodeById(nav.getParent(), treeNodeManager);

				if (parent != null) {
					parent.addChild(nav);
					navMenuList.remove(nav);
					_makeNavMenuList(navMenuList, treeNodeManager);
				}
			}
		}
	}

	public TreeNodeManager getNavMenuList(NavMenu navMenu) {
		List<NavMenu> navMenuList = navDao.getNavMenuList(navMenu);

		TreeNode<NavMenu> rootNode = new TreeNode<NavMenu>(new NavMenu());
		TreeNodeManager treeNodeManager = new TreeNodeManager(rootNode);

		for (NavMenu nav : navMenuList) {
			if (nav.getParent() == 0) {
				rootNode.addChild(nav);
			}
		}

		_makeNavMenuList(navMenuList, treeNodeManager);

		return treeNodeManager;
	}
	
	public boolean isExistsNavMenu(NavMenu navMenu) {
		return navDao.isExistsNavMenu(navMenu);
	}

	public int insertNavMenuGrp(NavMenuGrp navMenuGrp) {
		return navDao.insertNavMenuGrp(navMenuGrp);
	}

	public int updateNavMenuGrp(NavMenuGrp navMenuGrp) {
		return navDao.updateNavMenuGrp(navMenuGrp);
	}

	public int deleteNavMenuGrp(NavMenuGrp navMenuGrp) {
		return navDao.deleteNavMenuGrp(navMenuGrp);
	}

	public List<NavMenuGrp> getNavMenuGrpList() {
		return navDao.getNavMenuGrpList();
	}
}