package algorithms.chapter3;


/**
 * 基于AVL树实现的符号表
 * @author andy
 * @param <Key>
 * @param <Value>
 */
public class AVLBST<Key extends Comparable<Key>,Value> {

	public Node root;
	
	// 允许失衡的最大值
	private final static int ALLOWED_IMBALANXE = 1;
	
	public class Node{
		Key key;
		Value val;
		Node left,right;
		int height; 
		int bf;
		public Node(Key key, Value val, AVLBST<Key, Value>.Node left, AVLBST<Key, Value>.Node right, int height) {
			this.key = key;
			this.val = val;
			this.left = left;
			this.right = right;
			this.height = height;
		}
		public Key getKey() {
			return key;
		}
		
		public Value getVal() {
			return val;
		}
		
		public Node getLeft() {
			return left;
		}
	
		public Node getRight() {
			return right;
		}
		
		public int getHeight() {
			return height;
		}
		
		
	}
	
	public Value get(Key key){
		Node n = get(root, key);
		return n == null ? null : n.val;
	}
	
	private Node get(Node n,Key key) {
		if( n == null )
			return null;
		int cmp = key.compareTo(n.key);
		if( cmp > 0 )
			return get(n.right, key);
		if( cmp < 0 )
			return get(n.left, key);
		return n;
	}
	
	public void put(Key key,Value val){
		root = put(root,key,val);
	}
	
	private Node put(Node n, Key key, Value val) {
		if( n == null )
			n = new Node(key, val, null, null, 0);
		int cmp = key.compareTo(n.key);
		if( cmp > 0 )
			n.right = put(n.right, key, val);
		if( cmp < 0 )
			n.left = put(n.left, key, val);
		
		//return n;
		return balance(n);
	}
	
	

	public void delete(Key key){
		if( get(root, key) == null ) {
			return;
		}
		root = delete(root,key);
	}
	
	private Node delete(Node t, Key key) {
		// 树为空
		if(t == null)
			return null;
		int compareResult = key.compareTo(t.key);
		// 递归左子树
		if(compareResult < 0){
			t.left = delete(t.left, key);
			
		}
		// 递归右子树
		else if(compareResult > 0){
			t.right = delete(t.right, key);
			
		}
		// 待删除的节点有两个子节点那么找到其右子树中最小值代替被删除节点的值并删除该节点
		else if(t.left != null && t.right != null){
			t.key = findMin(t.right).key;
			t.right = delete(t.right, t.key);
		}
		// 待删除的节点只有一个子节点
		else {
			t = (t.left != null ) ? t.left : t.right;
		}
		
		if(t != null)
			t.height = Math.max(height(t.left), height(t.right)) + 1;
		return balance(t);
	}
	
	private Node findMin(Node node) {
		if(node == null){
			return null;
		}
		if(node.left == null){
			return node;
		}
		return findMin(node.left);
	}

	public void deleteMin(){
		
	}
	
	public void deleteMax(){
		
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(Node n) {
		if( n == null )
			return -1;
		return n.height;
	}
	
	/**
	 * 恢复节点平衡
	 * @param n
	 * @return
	 */
	private Node balance(Node n) {
		if( n == null )
			return null;
		if( height( n.right) - height( n.left ) > ALLOWED_IMBALANXE) {
			if(height( n.right.right) >= height( n.right.left) ) {
				n = rotateLeft(n);
			}else {
				n = doubleRotateRL(n);
			}
		}else {
			
			if( height( n.left) - height( n.right ) > ALLOWED_IMBALANXE) {
				if(height( n.left.left) >= height( n.left.right) ) {
					n = rotateRight(n);
				}else {
					n = doubleRotateLR(n);
				}
			}
		}
		n.height = Math.max(height(n.left), height(n.right)) + 1;
		return n;
	}
	
	/**
	 * 逆时针旋转
	 * @param h
	 * @return
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		h.height = Math.max(height(h.left), height(h.right)) + 1;
		return x;
	}
	
	/**
	 * 顺时针旋转
	 * @param h
	 * @return
	 */
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		h.height = Math.max(height(h.left), height(h.right)) + 1;
		return x;
	}
	
	/**
	 * 先逆时针旋转再顺时针旋转
	 * @param h
	 * @return
	 */
	private Node doubleRotateLR(Node h) {
		h.left = rotateLeft(h.left);
		return rotateRight(h);
	}
	
	/**
	 * 先顺时针旋转再逆时针旋转
	 * @param h
	 * @return
	 */
	private Node doubleRotateRL(Node h) {
		h.right = rotateRight(h.right);
		return rotateLeft(h);
	}
	
	public boolean isAVL() {
		return isAVL(root);
	}
	
	/* 获取二叉树高度
	 * @param root
	 * @return
	 */
	int calDepth(Node n) {
		if(n == null) {
			return 0;
		}
		return Math.max(calDepth(n.left), calDepth(n.right))+1;
	}
	
	public int bf(Node n) {
		return calDepth(n.left) - calDepth(n.right);
	}
	
	private boolean isAVL(Node n) {
		if(root == null) {
			return true;
		}else {
			int leftHight = calDepth(root.left);
			int rightHight = calDepth(root.right);
			if(Math.abs(leftHight - rightHight) >1) {
				return false;
			}else {
				return isAVL(root.left) && isAVL(root.right);
			}
		}
	}
}
