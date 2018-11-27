package algorithms.chapter3;
import algorithms.chapter3.practice.BinaryTreeViewer;

import com.chapter4.text.MyQueue;
import com.chapter4.text.StackL;

/**
 * 基于二叉查找树的符号表
 * @author andy
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>,Value> {

	public Node root;
	public class Node{
		Key key;		//键
		Value val;		// 值
		Node left,right;// 左子树,右子树
		int size;		// 以该节点为根的子树中的节点数量
		int level;		// 当前节点的层,根节点为0
		public Node(Key key, Value val, int n,int level) {
			this.key = key;
			this.val = val;
			this.size = n;
			this.level = level;
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
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		
		
	}
	
	
	
	public BST() {

	}
	

	public boolean isEmpty(){
		return root.size == 0;
	}
	
	public void delete(Key key){
		root = delete(root,key);
		
	}
	
	private Node delete(Node x, Key key) {
		int cmp = key.compareTo(x.key);
		if( cmp > 0 )
			x.right = delete(x.right,key);
		if( cmp < 0 )
			x.left = delete(x.left,key);
		// x为即将删除的节点
		if( cmp == 0){
			if( x.left == null )
				return x.right;
			if( x.right == null )
				return x.left;
			// x存在左右两棵子树
			if( x.left !=null && x.right != null ){
				// 获取x右子树中最小的节点
				Node min = min(x.right);
				// 将min的值赋值给x
				x.key = min.key;
				x.val = min.val;
				// 删除x右子树中最小的节点
				x.right = deleteMin(x.right);
			}
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMin(){
		deleteMin(root);
	}
	


	private Node deleteMin(Node n) {
		if( n.left == null)
			return n.right;
		n.left = deleteMin(n.left);
		n.size = size(n.left) + size(n.right) + 1;
		return n;
	}


	public void put(Key key,Value val){
		root = put(root,key,val,0);
			
	}

	private Node put(Node node, Key key, Value val,int level) {
		if(node == null)
			return new Node(key,val,1,level);
		int cmp = key.compareTo(node.key);
		if( cmp == 0){
			node.val = val;
			return node;
		}
		if( cmp > 0){
			Node n = put(node.right,key, val,node.level+1);
			node.right = n;
		}
		if( cmp < 0){
			Node n = put(node.left,key, val,node.level+1);
			node.left = n;
			
		}
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * 习题3.2.13 基于迭代版本的put
	 * @param key
	 * @param val
	 */
	public void putUseItertor(Key key,Value val) {
		Node t = root;
		Node n = null;
		StackL<Node> searchPathStack = new StackL<Node>();
		while( t != null ) {
			searchPathStack.push(t);
			int cmp = key.compareTo(t.key);
			if( cmp == 0){
				t.val = val;
				return;
			}
			else if( cmp > 0 ) {
				if( t.right == null )
					n = new Node(key, val, 1, t.level + 1);
				if( n != null) {
					t.right = n;
					t.size = size(t.left) + size(t.right) + 1;
					break;
				}
				t = t.right;
			}else {
				if( t.left == null )
					n = new Node(key, val, 1, t.level + 1);
				if( n != null) {
					t.left = n;
					t.size = size(t.left) + size(t.right) + 1;
					break;
				}
				t = t.left;
			}
		}
		// 更新节点size
		while( !searchPathStack.isEmpty()) {
			Node temp = searchPathStack.pop();
			temp.size = size(temp.left) + size(temp.right) + 1;
		}
		
	}
	
	public Value get(Key key){
		Node n = get(root,key);
		if( n != null )
			return n.val;
		return null;
	}
	
	private Node get(Node n, Key key) {
		if( n == null )
			return null;
		int cmp = key.compareTo(n.key);
		if( cmp == 0 )
			return n;
		if( cmp > 0 )
			return get(n.right,key);
		return get(n.left,key);
	}
	
	/**
	 * 习题3.2.7 计算二叉查找树一次随机命中查找平均所需的比较次数
	 * @return
	 */
	public double avgCompares(){
		return ipl(root)/size(root) + 1;
	}
	
	private int length(Node n) {
		if( n == null)
			return 0;
		return length(n.left) + length(n.right) + size(n) - 1;
	}
	
	public int ipl(){
		return ipl(root);
	}

	private int ipl(Node n) {
		int len = 0;
		MyQueue<Node> queue = new MyQueue<Node>();
		queue.enqueue(n);
		while(!queue.isEmpty()) {
			Node currNode = queue.dequeue();
			len += currNode.level;
			if(currNode.left != null && currNode.right != null) {
				queue.enqueue(currNode.left);
				queue.enqueue(currNode.right);
			}else if((currNode.left != null && currNode.right == null) || (currNode.left == null && currNode.right != null)){
				queue.enqueue(currNode.left == null ? currNode.right : currNode.left);
			}
		}
		return len;
	}

	/**
	 * 习题3.2.6 递归计算二叉查找树的高度
	 * @return 二叉查找树的高度(单个节点的二叉查找树的高度为1)
	 */
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
	
	/**
	 * 习题3.2.13 基于迭代的get实现
	 * @param key
	 * @return
	 */
	public Value getUseIterator(Key key) {
		Node t = root;
		while( t != null ) {
			int cmp = key.compareTo(t.key);
			if( cmp == 0 )
				return t.val;
			else if( cmp > 0 )
				t = t.right;
			else
				t= t.left;
		}
		return null;
	}
	
	/**
	 * 习题3.2.37
	 */
	public void printLevel(){
		MyQueue<Node> queue = new MyQueue<Node>();
		queue.enqueue(root);
		while( !queue.isEmpty() ){
			Node n = queue.dequeue();
			System.out.print(n.key+"->"+n.val+"\t");
			while( !queue.isEmpty() ){
				if( queue.firstEl().level == n.level ){
					if(n.left != null )
						queue.enqueue(n.left);
					if(n.right != null )
						queue.enqueue(n.right);
					n = queue.dequeue();
					System.out.print(n.key+"->"+n.val+"\t");
				}else{
					break;
				}
					
			}
			System.out.println();
			if(n.left != null )
				queue.enqueue(n.left);
			if(n.right != null )
				queue.enqueue(n.right);
		}
	}
	
	public int size(){
		return root.size;
	}
	
	private int size(Node node) {
		if( node == null)
			return 0;
		return node.size;
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
	
	public Key max() {
		Node n = max(root);
		return n.key;
	}

	private Node max(Node root) {
		if( root.right == null)
			return root;
		else
			return max(root.right);
	}
	
	public Key minUseRecursion() {
		Node rs = root;
		while( rs.left != null ) {
			rs = rs.left;
		}
		return rs.key;
	}
	
	public Key maxUseRecursion() {
		Node rs = root;
		while( rs.right != null ) {
			rs = rs.right;
		}
		return rs.key;
	}
	
	/**
	 * 返回小于等于{@code key}的最大键
	 * @return
	 */
	public Key floor(Key key) {
		Node n = floor(root,key);
		return n.key;
	}
	
	private Node floor(Node n,Key key) {
		if( n == null)
			return null;
		int cmp = key.compareTo(n.key);
		if( cmp == 0)
			return n;
		else if( cmp < 0 )
			return floor(n.left,key);
		else {
			Node r = floor(n.right,key);
			if( r == null )
				return n;
			else
				return r;
		}
	}
	
	public Key floorUseRecursion(Key key) {
		Node t = root;
		Node result = null;
		while( true ) {
			int cmp = key.compareTo(t.key);
			if( cmp == 0)
				return t.key;
			else if( cmp < 0 ) {
				if( result != null && t.left == null)
					return result.key;
				else
					t = t.left;
			}
			else {
				if( t.right == null)
					return t.key;
				else {
					result = t;
					t = t.right;
				}
			}
		}
	}
	
	/**
	 * 返回大于等于{@code key}的最小键
	 * @return
	 */
	public Key ceiling(Key key) {
		Node n = ceiling(root,key);
		return n.key;
	}
	
	private Node ceiling(Node n,Key key) {
		if( n == null)
			return null;
		int cmp = key.compareTo(n.key);
		if( cmp == 0)
			return n;
		else if( cmp > 0 )
			return ceiling(n.right,key);
		else {
			Node l = ceiling(n.left,key);
			return l == null ? n : l;
		}
	}
	
	public Key ceilingUseRecursion(Key key) {
		Node t = root;
		Node result = null;
		while( true ) {
			int cmp = key.compareTo(t.key);
			if( cmp == 0)
				return t.key;
			else if( cmp < 0 ) {
				
				if( t.left == null)
					return t.key;
				else {
					result = t;
					t = t.left;
				}
			}
			else {
				if( result != null && t.right == null)
					return result.key;
				else
					t = t.right;
			}
		}
	}
	
	/**
	 * 返回小于{@code key}的键的数量
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		return rank(root,key);
	}
	
	private int rank(Node n,Key key) {
		if( n == null)
			return 0;
		int cmp = n.key.compareTo(key);
		if( cmp < 0 ) {
			return (n.left == null? 0 :n.left.size) + 1 + rank(n.right,key);
		}
		else if( cmp > 0 )
			return rank(n.left,key);
		return rank(n.left,key);
	}
	
	public int rankUseRecursion(Key key) {
		int count = 0;
		Node n = root;
		while( n != null ) {
			int cmp = n.key.compareTo(key);
			if( cmp < 0 ) {
				count += (n.left == null? 0 :n.left.size) + 1;
				n = n.right;
			}
			else if( cmp > 0 )
				n = n.left;
			else {
				n = n.left;
			}
		}
		return count;
	}
	
	/**
	 * 返回排名为{@code k}的键
	 * @param k
	 * @return
	 */
	public Key select(int k) {
		Node n = select(root, k);
		return n == null ? null : n.key;
	}
	
	
	private Node select(Node n,int k) {
		if( n == null )
			return null;
		int t = size(n.left);
		if( t > k )
			return select(n.left,k);
		else if( t < k)
			return select(n.right,k-t-1);
		else
			return n;
	}
	
	public Key selectUseRecursion(int k) {
		Node n = root;
		while( true ) {
			int t = size(n.left);
			if( t > k)
				n = n.left;
			else if( t < k ) {
				n = n.right;
				k -= t+1;
			}
			else
				return n == null ? null : n.key;
		}
	}
	
	public void preOrder(Node n) {
		if( n == null)
			return;
		preOrder(n.left);
		System.out.println(n.key);
		preOrder(n.right);
	}
	
	public Iterable<Key> keys(){
		MyQueue<Key> queue = new MyQueue<Key>();
		keys(root,queue);
		return queue;
	}
	
	private void keys(Node n,MyQueue<Key> queue) {
		if( n == null)
			return;
		keys(n.left,queue);
		queue.enqueue(n.key);
		keys(n.right,queue);
		
	}


	/**
	 * 习题3.2.33
	 * @return
	 */
	public boolean check(){
		for( int i = 0; i< size() - 1; i++ )
			if( i != rank(select(i)))
				return false;
		for (Key key : keys()) {
			if( !key.equals(select(rank(key))))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		/*BST<String, Integer> bst = new BST<String, Integer>();
		bst.put("E", 5);
		bst.put("A", 1);
		bst.put("S", 19);
		bst.put("Y", 25);
		bst.put("Q", 17);
		bst.put("U", 21);
		bst.put("E", 5);
		bst.put("S", 19);
		bst.put("T", 20);
		bst.put("I", 9);
		bst.put("O", 15);
		bst.put("N", 14);*/
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(20, 1);
		bst.put(15, 10);
		bst.put(30, 2);
		bst.put(10, 2);
		bst.put(18, 2);
		bst.put(9, 2);
		bst.put(12, 2);
		bst.put(16, 2);
		bst.put(19, 2);
		bst.put(22, 2);
		bst.put(26, 2);
		bst.put(24, 2);
		System.out.println(bst.size());
		System.out.println(bst.get(10));
		System.out.println("BST 高度为:"+bst.height());
		System.out.println("BST 内部路径长度为:"+bst.ipl());
		System.out.println("BST 平均比较次数:"+bst.avgCompares());
		System.out.println("按照层级打印:");
		bst.printLevel();
		//viewer.refresh();
		System.out.println("floor(23):"+bst.floor(23));//22
		System.out.println("floorUseRecursion(23)"+bst.floorUseRecursion(23));
		System.out.println("ceiling(17):"+bst.ceiling(17));//24
		System.out.println("ceilingUseRecursion(17):"+bst.ceilingUseRecursion(17));//24
		System.out.println("rank(19):"+bst.rank(19));//5
		System.out.println("rankUseRecursion(19):"+bst.rankUseRecursion(19));
		System.out.println("select(5):"+bst.select(1));
		System.out.println("selectUseRecursion(5):"+bst.selectUseRecursion(1));
		bst.delete(15);
		System.out.println("get with recursion:"+bst.get(24));
		System.out.println("get with itertor:"+bst.getUseIterator(24));
		bst.putUseItertor(50, 2);
		bst.putUseItertor(25, 2);
		//bst.put(11, 11);
		System.out.println("check():"+bst.check());
		BinaryTreeViewer<Integer, Integer> viewer = new BinaryTreeViewer<Integer, Integer>(bst);
		viewer.refresh();
		System.out.println("keys:");
		for (Integer key : bst.keys()) {
			System.out.println(key);
		}
	}
}
