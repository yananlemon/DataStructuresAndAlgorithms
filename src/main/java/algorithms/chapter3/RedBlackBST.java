package algorithms.chapter3;

import java.util.NoSuchElementException;

import com.chapter4.text.MyQueue;



/**
 * 红黑树
 * 基于左倾红黑树实现的符号表
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

	/**
	 * 反转{@code node}和它两个孩子节点的颜色
	 * @param node
	 */
	private void flipColors(Node node) {
		node.color 		 = !node.color;
		node.left.color  = !node.left.color;
		node.right.color = !node.right.color;
	}

	public int height(){
		return height(root);
	}
	
	/***************************************************************************
    *  检查红黑树数据结构的完整性。
    ***************************************************************************//*
    private boolean check() {
        if (!isBST())            System.out.println("Not in symmetric order");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) System.out.println("Ranks not consistent");
        if (!is23())             System.out.println("Not a 2-3 tree");
        if (!isBalanced())       System.out.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }*/

	private int height(Node n){
		if( n == null)
			return 0;
		int leftH = height(n.left);
		int rightH = height(n.right);
		return Math.max(leftH, rightH) + 1;
	}

	/***************************************************************************
	 *  红黑树删除
	 ***************************************************************************/

	/**
	 * 从符号表中删除最小键及其相关联的值
	 * @throws NoSuchElementException 如果符号表为空
	 */
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// 如果根节点的左右子节点都是黑色,那么设置根节点为红色
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
	}

	// 删除以h为根节点的树中的最小键
	private Node deleteMin(Node h) { 
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}


	/**
	 * 从符号表中删除最大键及其相关联的值
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// 如果根节点的左右子节点都是黑色,那么设置根节点为红色
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMax(root);
		if (!isEmpty()) root.color = BLACK;
	}

	// 删除以h为根节点的树中的最大键
	private Node deleteMax(Node h) { 
		if (isRed(h.left))
			h = rotateRight(h);

		if (h.right == null)
			return null;

		if (!isRed(h.right) && !isRed(h.right.left))
			h = moveRedRight(h);

		h.right = deleteMax(h.right);

		return balance(h);
	}


	public void delete(Key key){
		
		// 如果根节点的左右子节点都是黑色,那么设置根节点为红色
		if ( !isRed(root.left) && !isRed(root.right) )
			root.color = RED;
		root = delete(root, key);
		if (!isEmpty()) 
			root.color = BLACK;

	}

	private Node delete(Node x, Key key) {
		if( key.compareTo(x.key) < 0 ){
			if( !isRed(x.left) && !isRed(x.left.left) ){
				x = moveRedLeft(x);
			}
			x.left = delete(x.left, key);
		}else{
			if( isRed(x.left) )
				x= rotateRight(x);
			if( key.compareTo(x.key) == 0 && (x.right == null))
				return null;
			if (!isRed(x.right) && !isRed(x.right.left))
				x = moveRedRight(x);
			if (key.compareTo(x.key) == 0)
			{
				// 获取x右子树中最小的节点
				Node min = min(x.right);
				// 将min的值赋值给x
				x.key = min.key;
				x.val = min.val;
				// 删除x右子树中最小的节点
				x.right = deleteMin(x.right);
			}
			else x.right = delete(x.right, key);
		}

		x.N = size(x.left) + size(x.right) + 1;
		return balance(x);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Key min() {
		Node n = min(root);
		return n.key;
	}

	private Node min(Node root) {
		if( root.left == null)
			return root;
		else
			return min(root.left);
	}

	private Node moveRedLeft(Node h){
		flipColors(h);
		if( isRed(h.right.left) ){
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}

	private Node moveRedRight(Node h)
	{
		flipColors(h);
		if (isRed(h.left.left))
		{
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}



	/**
	 * 恢复红黑树的性质
	 * @param node
	 * @return
	 */
	private Node balance(Node node) {
		
		// 修复右倾红色节点
		if( isRed(node.right) )
			node = rotateLeft(node);
			
		// 检测左侧是否存在4-节点(两条连续的红链接)
		if( isRed(node.left) && isRed(node.left.left) )
			node = rotateRight(node);
		
		if( isRed(node.left) && isRed(node.right) )
			flipColors(node);
		return node;
	}

	public boolean is23(){
		return is23(root);
	}

	public boolean is23(Node h){
		if( h == null )
			return true;
		if( isRed(h.right) )
			return false;
		if( h != root && isRed(h) && isRed(h.left))
			return false;
		return is23(h.left) && is23(h.right);
	}
	
	/**
	 * 检查从根节点到所有空链接的路径上的黑链接的数量是否相同
	 * @return
	 */
	public boolean isBalanced(){
		int black = 0;
		Node h = root;
		while( h != null ){
			if( !isRed( h ) )
				black ++;
			h = h.right;
		}
		return isBalanced( root ,black);
	}

	private boolean isBalanced(Node h,int black) {
		if( h == null )
			return black == 0;
		if( !isRed(h) )
			black --;
		return isBalanced(h.left, black) && isBalanced(h.right,black); 
	}
	

	/**
	 * 检查当前红黑查找树是否是2-3树(练习3.3.33)
	 * @return
	 */
	public boolean is23WithIterator(){
		Node h = root;
		if( !isEmpty() ){
			MyQueue<Node> queue = new MyQueue<Node>();
			queue.enqueue(h);
			while( true ){
				h = queue.dequeue();
				if( queue.isEmpty() )
					return true;
				if( h.left != null )
					queue.enqueue(h.left);
				if( h.right != null )
					queue.enqueue(h.right);
				if( isRed(h.right))
					return false;
				if( isRed(h.left) && isRed(h.left.left) )
					return false;

			}
		}
		return false;
	}

}
