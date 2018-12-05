package com.chapter6.navl;

/**
 * <p>AvlTree</p>
 * @author yanan
 * @date 2018年4月22日
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
	
	public AvlNode<AnyType> root;//根节点

	public AvlNode<AnyType> getRoot() {
		return root;
	}

	public class AvlNode<AnyType>{
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

		public AnyType getElement() {
			return element;
		}

		public void setElement(AnyType element) {
			this.element = element;
		}

		public AvlNode<AnyType> getLeft() {
			return left;
		}

		public void setLeft(AvlNode<AnyType> left) {
			this.left = left;
		}

		public AvlNode<AnyType> getRight() {
			return right;
		}

		public void setRight(AvlNode<AnyType> right) {
			this.right = right;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
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
					if(element.compareTo(root.left.element) > 0)
						root = doubleWithLeftChild(root);
					
					// 如果插入到了root节点的左子树的左子树上则进行顺时针旋转
					else if(element.compareTo(root.left.element) < 0)
						root = rotateWithLeftChild(root);
				}
			}
			else {
				root.right = insert(root.right, element);
				
				// 如果右子树的高度减去左子树的高度等于2
				if(height(root.right)-height(root.left) == 2){
					
					// 如果插入到了root节点的右子树的右子树上则进行逆时针旋转
					if(element.compareTo(root.right.element) > 0)
						root = rotateWithRightChild(root);
					
					// 如果插入到了root节点的右子树的左子树上则先逆时针旋转再顺时针旋转
					else if(element.compareTo(root.right.element) < 0)
						root = doubleWithRightChild(root);
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

	public int height() {
		return height(root);
	}
	
	private int height(AvlNode<AnyType> t) {
		return t == null ? 0 : t.height;
	}
	
	public void delete(AnyType element){
		if(find(element) == null ){
			return;
		}
		root = delete(root,element);
	}

	private AvlNode<AnyType> delete(AvlNode<AnyType> t, AnyType element) {
		// 树为空
		if(t == null)
			return null;
		int compareResult = element.compareTo(t.element);
		// 递归左子树
		if(compareResult < 0){
			t.left = delete(t.left, element);
			// 判断是否平衡
			if(height(t.right) - height(t.left) == 2){
				AvlNode<AnyType> currNode = t.right;
				//判断需要进行哪种旋转
				if(height(currNode.left) > height(currNode.right)){
					t = doubleWithRightChild(t);
				}else{
					t = rotateWithRightChild(t);
				}
			}
		}
		// 递归右子树
		else if(compareResult > 0){
			t.right = delete(t.right, element);
			// 判断是否平衡
			if(height(t.left) - height(t.right) == 2){
				AvlNode<AnyType> currNode = t.left;
				//判断需要进行哪种旋转
				if(height(currNode.right) > height(currNode.left)){
					t = doubleWithLeftChild(t);
				}else{
					t = rotateWithLeftChild(t);
					
				}
			}
		}
		// 待删除的节点有两个子节点那么找到其右子树中最小值代替被删除节点的值并删除该节点
		else if(t.left != null && t.right != null){
			t.element = findMin(t.right).element;
			//t.right = deleteMin(t.right); fix a bug on 2018/4/27
			t.right = delete(t.right, t.element);
		}
		// 待删除的节点只有一个子节点
		else {
			t = (t.left != null ) ? t.left : t.right;
		}
		
		if(t != null)
			t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	/**
	 * 在BST中搜索element
	 * @param element
	 * @return
	 */
	public AnyType find(AnyType element){
		return find(root,element);
	}
	
	private AnyType find(AvlNode<AnyType> node, AnyType element) {
		if(node == null || element == null){
			return null;
		}
		int compare = node.element.compareTo(element);
		if(compare < 0){
			return find(node.right, element);
		}else if(compare > 0){
			return find(node.left, element);
		}else{
			return node.element;
		}
	}

	/**
	 * 在BST中搜索最小的element
	 * @param element
	 * @return
	 */
	public AnyType findMin(){
		return elementAt(findMin(root));
	}
	
	private AvlNode<AnyType> findMin(AvlNode<AnyType> node) {
		if(node == null){
			return null;
		}
		if(node.left == null){
			return node;
		}
		return findMin(node.left);
	}

	/**
	 * 在BST中搜索最大的element
	 * @param element
	 * @return
	 */
	public AnyType findMax(){
		return elementAt(findMax(root));
	}
	
	/**
	 * 删除最小的节点
	 */
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	private AnyType elementAt(AvlNode<AnyType> node){
		return node == null ? null : node.element;
	}
	
	private AvlNode<AnyType> findMax(AvlNode<AnyType> node){
		if(node == null){
			return null;
		}
		if(node.right == null){
			return node;
		}else{
			return findMax(node.right);
		}
	}
	
	/**
	 * 删除最小节点
	 * @param node
	 * @return
	 */
	private AvlNode<AnyType> deleteMin(AvlNode<AnyType> node) {
		if(node != null){
			if(node.left == null){
				return node.right;
			}else{
				node.left = deleteMin(node.left);
				return node;
			}
		}
		return null;
	}

	
	/* 获取二叉树高度
	 * @param root
	 * @return
	 */
	int calDepth(AvlNode<AnyType> root) {
		if(root == null) {
			return 0;
		}
		return Math.max(calDepth(root.left), calDepth(root.right))+1;
	}
	public boolean isAVL() {
		return isAVLTree(root);
	}
	boolean isAVLTree(AvlNode<AnyType> root) {
		if(root == null) {
			return true;
		}else {
			int leftHight = calDepth(root.left);
			int rightHight = calDepth(root.right);
			if(Math.abs(leftHight - rightHight) >1) {
				return false;
			}else {
				return isAVLTree(root.left) && isAVLTree(root.right);
			}
		}
	}
	
	public void inorder(AvlNode<AnyType> root){
		if(root != null){
			inorder(root.left);
			System.out.print(root.element+",");
			inorder(root.right);
		}
	}
	
}
