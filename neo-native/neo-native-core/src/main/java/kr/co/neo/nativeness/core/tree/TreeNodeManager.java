package kr.co.neo.nativeness.core.tree;

import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
public class TreeNodeManager {
	private TreeNode<?> rootNode;

	public TreeNodeManager(TreeNode<?> rootNode) {
		this.rootNode = rootNode;
	}

	private void _iterating(List<?> list, TreeNodeIterator treeNodeIterator) {
		try {
			for (int i = 0; i < list.size(); i++) {
				TreeNode<?> treeNode = (TreeNode<?>) list.get(i);
				treeNodeIterator.doStart(treeNode);

				if (treeNode.hasChildren()) {
					treeNodeIterator.startChild(treeNode);

					_iterating(treeNode.getChildren(), treeNodeIterator);

					treeNodeIterator.endChild(treeNode);
				}

				treeNodeIterator.doEnd(treeNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void iterating(TreeNodeIterator treeNodeIterator) {
		_iterating(rootNode.getChildren(), treeNodeIterator);
	}

	public TreeNode<?> getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode<?> rootNode) {
		this.rootNode = rootNode;
	}
	
	public <T> TreeNode findNode(Comparable<T> cmp){
		TreeNodeIter iter =   (TreeNodeIter) rootNode.iterator();
		
		while(iter.hasNext()){
			TreeNode node = iter.next();
			TreeNode result = node.findTreeNode(cmp);
			
			if(result != null)
				return result;
		}
		
		return null;
	}
	
	public boolean isEmpty(){
		return !rootNode.hasChildren();
	}

}
