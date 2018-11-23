package algorithms.chapter3;
import com.chapter4.text.MyQueue;

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
		bst.put(25, 2);
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
	}
}
