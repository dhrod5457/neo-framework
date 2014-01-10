package kr.co.neo.nativeness.core.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> implements Iterable<TreeNode<T>> {
	private T data;
	private TreeNode<T> parent;
	private List<TreeNode<T>> children;

	public boolean isRoot() {
		return parent == null;
	}
	
	public boolean isLeaf() {
		return children.size() == 0;
	}

	private List<TreeNode<T>> elementsIndex;

	public TreeNode(T data) {
		this.data = data;
		this.children = new LinkedList<TreeNode<T>>();
		this.elementsIndex = new LinkedList<TreeNode<T>>();
		this.elementsIndex.add(this);
	}

	public TreeNode<T> addChild(T child) {
		TreeNode<T> childNode = new TreeNode<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(TreeNode<T> node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public TreeNode<T> findTreeNode(Comparable<T> cmp) {
		for (TreeNode<T> element : this.elementsIndex) {
			T elData = element.data;
			
			if (cmp.compareTo(elData) == 0)
				return element;
		}

		return null;
	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

	@Override
	public Iterator<TreeNode<T>> iterator() {
		TreeNodeIter<T> iter = new TreeNodeIter<T>(this);
		return iter;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode<T>> children) {
		this.children = children;
	}

	public List<TreeNode<T>> getElementsIndex() {
		return elementsIndex;
	}

	public void setElementsIndex(List<TreeNode<T>> elementsIndex) {
		this.elementsIndex = elementsIndex;
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}
	
	private TreeNode<T> _getRootNode(TreeNode<T> treeNode){
		if(treeNode.isRoot()){
			return treeNode;
		}
		
		return _getRootNode(treeNode.getParent());
	}
	
	public TreeNode<T> getRootNode(){
		return _getRootNode(this);
	}
}
