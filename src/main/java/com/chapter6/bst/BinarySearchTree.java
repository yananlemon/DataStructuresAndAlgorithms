package com.chapter6.bst;


/**
 * <p>BinarySearchTree</p>
 * <p>BSt实现</p>
 * @author yanan
 * @date 2018年4月2日
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	
	protected BinaryNode<AnyType> root = null;
	
	public BinarySearchTree(){
		
	}
	
	/**
	 * 将element插入到BST
	 * @param element
	 */
	public void insert(AnyType element){
		root = insert(element,root);
	}
	
	private BinaryNode<AnyType> insert(AnyType element,
			BinaryNode<AnyType> node) {
		if(node == null){
			return new BinaryNode<AnyType>(element);
		}
		int compare = element.compareTo(node.element);
		if(compare < 0){
			node.left = insert(element, node.left);
		}else if(compare > 0){
			node.right = insert(element, node.right);
		}else{
			throw new Error("不可以将相同的值插入到BST！");
		}
		return node;
	}

	/**
	 * 计算二叉树的高度
	 * 算法：从根节点开始，递归计算左子树高度、右子树高度，取二者较大值＋1
	 * @param root
	 * @return
	 */
	public int countHeight(BinaryNode<AnyType> root){
		if(root == null){
			return 0;
		}
		int leftHeight = 0;
		int rightHeight = 0;
		if(root.left != null){
			return countHeight(root.left)+1;
		}
		if(root.right != null){
			return countHeight(root.right)+1;
		}
		return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
	}
	
	/**
	 * 从BST中删除element
	 * @param element
	 */
	public void delete(AnyType element){
		root = delete(root,element);
	}
	
	private BinaryNode<AnyType> delete(BinaryNode<AnyType> node,
			AnyType element) {
		if(node == null){
			return null;
		} 
		int compareResult = node.element.compareTo(element);
		if(compareResult < 0){
			node.right = delete(node.right, element);
		}else if(compareResult > 0){
			node.left = delete(node.left, element);
		}
		
		// 待删除的节点有两个子节点那么找到其右子树中最小值代替被删除节点的值并删除该节点
		else if(node.left != null && node.right != null){
			node.element = findMin(node.right).element;
			node.right = deleteMin(node.right);
		}
		
		// 待删除的节点只有一个子节点
		else {
			node = (node.left != null ) ? node.left : node.right;
		}
		return node;
	}

	/**
	 * 删除最小的节点
	 */
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	/**
	 * 删除最小节点
	 * @param node
	 * @return
	 */
	private BinaryNode<AnyType> deleteMin(BinaryNode<AnyType> node) {
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

	/**
	 * 在BST中搜索element
	 * @param element
	 * @return
	 */
	public AnyType find(AnyType element){
		return find(root,element);
	}
	
	private AnyType find(BinaryNode<AnyType> node, AnyType element) {
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
	
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> node) {
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
	 * 测试当前BST是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return root == null ? true : false;
	}
	
	/**
	 * 清空BST中所有节点
	 * @param element
	 * @return
	 */
	public void clear(){
		
	}
	
	private AnyType elementAt(BinaryNode<AnyType> node){
		return node == null ? null : node.element;
	}
	
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> node){
		if(node == null){
			return null;
		}
		if(node.right == null){
			return node;
		}else{
			return findMax(node.right);
		}
	}
	
	public void inorder(BinaryNode<AnyType> node){
		if(node != null){
			inorder(node.left);
			System.out.print(node.element+",");
			inorder(node.right);
		}
	}
	
	
	
}

class BinaryNode<AnyType>{
	AnyType element;
	BinaryNode<AnyType> left;
	BinaryNode<AnyType> right;
	BinaryNode(AnyType element){
		this.element = element;
		left = null;
		right = null;
	}
}
