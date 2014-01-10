package kr.co.neo.nativeness.core.tree;

public interface TreeNodeIterator <T>{
	public void doStart(TreeNode<T> treeNode) throws Exception;

	public void startChild(TreeNode<T> node) throws Exception;

	public void endChild(TreeNode<T> node) throws Exception;

	public void doEnd(TreeNode<T> treeNode) throws Exception;
}
