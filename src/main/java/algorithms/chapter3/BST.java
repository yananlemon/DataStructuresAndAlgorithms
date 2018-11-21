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

	private Node root;
	private class Node{
		Key key;		//键
		Value val;		// 值
		Node left,right;// 左子树,右子树
		int size;		// 以该节点为根的子树中的节点数量
		public Node(Key key, Value val, int n) {
			this.key = key;
			this.val = val;
			this.size = n;
		}
		
	}
	
	public boolean isEmpty(){
		return root.size == 0;
	}
	
	public void put(Key key,Value val){
		root = put(root,key,val);
			
	}

	private Node put(Node node, Key key, Value val) {
		if(node == null)
			return new Node(key,val,1);
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
		return len(root)/size(root) + 1;
	}
	
	private int len(Node n) {
		int len = 0;
		int level = 0;
		MyQueue<Node> queue = new MyQueue<Node>();
		queue.enqueue(n);
		while(!queue.isEmpty()) {
			Node currNode = queue.dequeue();
			level++;
			if(currNode.left != null) {
				len += level;
				queue.enqueue(currNode.left);
			}
			if(currNode.right != null) {
				queue.enqueue(currNode.right);
				len += level;
			}
		}
		return len;
	}
	
	private int length(Node n) {
		if( n == null)
			return 0;
		return length(n.left) + length(n.right) + size(n) - 1;
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
		bst.put(10, 1);
		bst.put(20, 10);
		bst.put(30, 2);
		//bst.put(9, 9);
		/*bst.put(3, 3);
		bst.put(8, 8);
		bst.put(4, 8);
		bst.put(7, 8);
		bst.put(6, 8);
		bst.put(5, 8);*/
		System.out.println(bst.size());
		System.out.println(bst.get(10));
		System.out.println("BST 高度为:"+bst.height());
		System.out.println("BST 平均比较次数:"+bst.avgCompares());
	}
}
