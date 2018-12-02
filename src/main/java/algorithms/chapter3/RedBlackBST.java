package algorithms.chapter3;


/**
 * 红黑树
 * @author yanan
 *
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {

	private static final boolean RED   = true;
	
	private static final boolean BLACK = false;
	
	public Node root;
	
	public class Node{
		Key key;			// 键
		Value val;			// 值
		Node left,right;	// 左右子树
		int N;				// 这颗树中的节点总数
		boolean color;		// 由其父节点指向它的链接的颜色
		public Node(Key key, Value val, int n,boolean color) {
			this.key = key;
			this.val = val;
			N = n;
			this.color = color;
		}
		public Key getKey() {
			return key;
		}
		public void setKey(Key key) {
			this.key = key;
		}
		public Value getVal() {
			return val;
		}
		public void setVal(Value val) {
			this.val = val;
		}
		public Node getLeft() {
			return left;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		public int getN() {
			return N;
		}
		public void setN(int n) {
			N = n;
		}
		public boolean isColor() {
			return color;
		}
		public void setColor(boolean color) {
			this.color = color;
		}
		
	}
	
	private boolean isRed(Node x) {
		if( x == null )
			return false;
		return x.color == RED;
	}
	
	/**
	 * 左旋转{@code h}的右链接
	 * @param h
	 * @return
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	/**
	 * 右旋转{@code h}的左链接
	 * @param h
	 * @return
	 */
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	private int size(Node x) {
		if( x == null )
			return 0;
		return x.N;
	}
	
	public void put(Key key,Value val){
		root = put(root,key,val);
		root.color = BLACK;
			
	}

	private Node put(Node node, Key key, Value val) {
		if(node == null)
			return new Node(key,val,1,RED);
		int cmp = key.compareTo(node.key);
		if( cmp == 0){
			node.val = val;
			return node;
		}
		if( cmp > 0){
			Node n = put(node.right,key, val);
			node.right = n;
		}
		if( cmp < 0){
			Node n = put(node.left,key, val);
			node.left = n;
			
		}
		if( isRed(node.right) && !isRed(node.left) )
			node = rotateLeft(node);
		if( isRed(node.left) && isRed(node.left.left) )
			node = rotateRight(node);
		if( isRed(node.left) && isRed(node.right) )
			flipColors(node);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	private void flipColors(Node node) {
		node.color 		 = RED;
		node.left.color  = BLACK;
		node.right.color = BLACK;
	}
	
	public int height(){
		return height(root);
	}
	
	private int height(Node n){
		if( n == null)
			return 0;
		int leftH = height(n.left);
		int rightH = height(n.right);
		return Math.max(leftH, rightH) + 1;
	}
}
