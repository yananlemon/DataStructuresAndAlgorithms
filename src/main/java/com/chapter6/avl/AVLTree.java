package com.chapter6.avl;


/**
 * <p>AVLTree</p>
 * <p>AVL树实现</p>
 * <p>http://www.cnblogs.com/skywang12345/p/3577479.html</p>  
 * @author yanan
 * @date 2018年4月3日
 */
public class AVLTree<AnyType extends Comparable<AnyType>> {

	protected AVLNode<AnyType> root;
	
	
	
	public AVLNode<AnyType> getRoot() {
		return root;
	}

	/**
	 * 将element插入到BST
	 * @param element
	 */
	public void insert(AnyType element){
		root = insert(element,root);
	}
	
	private AVLNode<AnyType> insert(AnyType element,
			AVLNode<AnyType> node) {
		if(node == null){
			node = new AVLNode<AnyType>(element);
		}else{
			if(element.compareTo(node.element) < 0){
				node.left = insert(element, node.left);
				//插入节点后，判断是否需要平衡
				if(height(node.left)-height(node.right) == 2){
					if(element.compareTo(node.left.element) > 0){//先逆时针旋转再顺时针旋转
						node = lrRotation(node);
					}else if(element.compareTo(node.left.element) < 0){//顺时针旋转
						node = llRotation(node);
					}
				}
			}else if(element.compareTo(node.element) > 0){
				node.right = insert(element, node.right);
				//插入节点后，判断是否需要平衡
				if(height(node.right)-height(node.left) == 2){
					if(element.compareTo(node.right.element) > 0){//逆时针旋转
						node = rrRotation(node);
					}else if(element.compareTo(node.right.element) < 0){//先顺时针旋转再逆时针旋转
						node = rlRotation(node);
					}
				}
			}else{
				throw new Error("不可以将相同的值插入到AVL！");
			}
		}
		node.height = max( height(node.left), height(node.right)) + 1;
		return node;
	}
	
	private int height(AVLNode<AnyType> node) {
		if(node != null){
			return node.height;
		}
		return 0;
	}
		
	

	/**
	 * 从BST中删除element
	 * @param element
	 */
	public void delete(AnyType element){
		if(find(element) == null ){
			return;
		}
		root = delete(root,find(root, element));
	}
	
	private AVLNode<AnyType> delete(AVLNode<AnyType> node,
			AVLNode<AnyType> el) {
		if(node == null || el == null){
			return null;
		}else if(node.element.compareTo(el.element) < 0){// 当前节点小于element则递归右子树
			node.right = delete(node.right, el);
			if(height(node.left) - height(node.right) == 2){
				AVLNode<AnyType> left =  node.left;
	            if (height(left.right) > height(left.left)){
	            	node = lrRotation(node);
	            }else{
	            	node = llRotation(node);
	            }
			}
		}else if(node.element.compareTo(el.element) > 0){
			node.left = delete(node.left, el);
			if(height(node.right) - height(node.left) == 2){
				AVLNode<AnyType> right =  node.right;
				if (height(right.left) > height(right.right)){
					node = rlRotation(node);
				}else{
					node = rrRotation(node);
				}
			}
		}else if(node.left != null && node.right != null){
			if (height(node.left) > height(node.right)) {
				AVLNode<AnyType> max = findMax(node.left);
				node.element = max.element;
				node.left = delete(node.left, max);
			}else{
				AVLNode<AnyType> min = findMin(node.right);
				node.element = min.element;
				node.right = delete(node.right,min);
			}
		}else{
			AVLNode<AnyType> tmp = node;
			node = (node.left!=null) ? node.left : node.right;
            tmp = null;
		}
		return node;
	}

	/**
	 * 删除最小的节点
	 */
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	private AVLNode<AnyType> deleteMin(AVLNode<AnyType> node) {
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
		AVLNode<AnyType> rs = find(root,element);
		if(rs == null){
			return null;
		}
		return rs.element;
	}
	
	private AVLNode<AnyType> find(AVLNode<AnyType> node, AnyType element) {
		if(node == null){
			return null;
		}
		if(element == null){
			return null;
		}
		if(node.element.compareTo(element) < 0){
			return find(node.right, element);
		}else if(node.element.compareTo(element) > 0){
			return find(node.left, element);
		}else{
			return node;
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
	
	private AVLNode<AnyType> findMin(AVLNode<AnyType> node) {
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
	
	private AnyType elementAt(AVLNode<AnyType> node){
		return node == null ? null : node.element;
	}
	
	private AVLNode<AnyType> findMax(AVLNode<AnyType> node){
		if(node == null){
			return null;
		}
		if(node.right == null){
			return node;
		}else{
			return findMax(node.right);
		}
	}
	
	public void inorder(AVLNode<AnyType> node){
		if(node != null){
			inorder(node.left);
			System.out.print(node.element+",");
			inorder(node.right);
		}
	}
	
	/**
	 * 顺时针旋转
	 * @param k2
	 * @return
	 */
	private AVLNode<AnyType> llRotation(AVLNode<AnyType> k2){
		AVLNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		return k1;
	}
	
	int max(int a,int b){
		return a>b? a : b;
	}
	
	/**
	 * 逆时针旋转
	 * @param k2
	 * @return
	 */
	private AVLNode<AnyType> rrRotation(AVLNode<AnyType> k1){
		AVLNode<AnyType> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		return k2;
	}
	
	/**
	 * 先逆时针旋转再顺时针旋转
	 * @param k2
	 * @return
	 */
	private AVLNode<AnyType> lrRotation(AVLNode<AnyType> k3){
		k3.left = rrRotation(k3.left);
		return llRotation(k3);
	}
	
	/**
	 * 先顺时针旋转再逆时针旋转
	 * @param k2
	 * @return
	 */
	private AVLNode<AnyType> rlRotation(AVLNode<AnyType> k3){
		k3.right = llRotation(k3.right);
		return rrRotation(k3);
	}
	
}

