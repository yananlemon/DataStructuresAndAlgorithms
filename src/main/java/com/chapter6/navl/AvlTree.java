package com.chapter6.navl;

/**
 * <p>AvlTree</p>
 * @author yanan
 * @date 2018年4月22日
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
	
	private AvlNode<AnyType> root;//根节点

	private static class AvlNode<AnyType>{
		private AnyType 			element;	//数据
		private AvlNode<AnyType> 	left;		//左子树
		private AvlNode<AnyType> 	right;		//右子树
		private int 				height;		//树的高度
		public AvlNode(AnyType element, AvlNode<AnyType> left, AvlNode<AnyType> right) {
			super();
			this.element = element;
			this.left = left;
			this.right = right;
		}
		
		public AvlNode(AnyType element) {
			this(element, null, null);
		}
		
	}
	
	public int getHeight() {
		return height(root);
	}
	
	public void insert(AnyType element) {
		root = insert(root,element);
	}

	/**
	 * 将数据插入以root为根节点的Avl树中
	 * @param root
	 * @param element
	 */
	private AvlNode<AnyType> insert(AvlNode<AnyType> root, AnyType element) {
		if(root == null)
			root = new AvlNode<AnyType>(element);
		else {
			int compareResult = element.compareTo(root.element);
			if(compareResult == 0)
				throw new Error("不能将相同的数据添加到AVl树中！");
			else if(compareResult < 0) {// 插入到左子树
				root.left = insert(root.left, element);
				
				// 如果左子树的高度减去右子树的高度等于2
				if(height(root.left)-height(root.right) == 2){
					
					// 如果插入到了root节点的左子树的右子树上则先逆时针旋转再顺时针旋转
					if(element.compareTo(root.left.element) > 0){
						root = doubleWithLeftChild(root);
					}
					
					// 如果插入到了root节点的左子树的左子树上则进行顺时针旋转
					else if(element.compareTo(root.left.element) < 0){
						root = rotateWithLeftChild(root);
					}
				}
			}
			else {
				root.right = insert(root.right, element);
				
				// 如果右子树的高度减去左子树的高度等于2
				if(height(root.right)-height(root.left) == 2){
					
					// 如果插入到了root节点的右子树的右子树上则进行逆时针旋转
					if(element.compareTo(root.right.element) > 0){
						root = rotateWithRightChild(root);
					}
					
					// 如果插入到了root节点的右子树的左子树上则先逆时针旋转再顺时针旋转
					else if(element.compareTo(root.right.element) < 0){
						root = doubleWithRightChild(root);
					}
				}
			}
		}
		root.height = Math.max( height(root.left), height(root.right)) + 1;
		return root;
	}

	/**
	 * 先顺时针旋转再逆时针旋转
	 * @param root
	 * @return
	 */
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> root) {
		root.right = rotateWithLeftChild(root.right);
		return rotateWithRightChild(root);
	}


	/**
	 * 先逆时针旋转再顺时针旋转
	 * @param root
	 * @return
	 */
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> root) {
		root.left = rotateWithRightChild(root.left);
		return rotateWithLeftChild(root);
	}

	/**
	 * 逆时针旋转
	 * @param root
	 * @return
	 */
	private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> root) {
		AvlNode<AnyType> node = root.right;
		root.right = node.left;
		node.left = root;
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		return node;
	}

	/**
	 * 顺时针旋转
	 * @param root
	 * @return
	 */
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> root) {
		AvlNode<AnyType> node = root.left;
		root.left = node.right;
		node.right = root;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		return node;
		
	}

	private int height(AvlNode<AnyType> t) {
		return t == null ? 0 : t.height;
	}
	
	
}
