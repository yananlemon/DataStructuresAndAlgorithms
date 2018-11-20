package algorithms.chapter3;

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
	
	public int size(){
		return root.size;
	}
	
	private int size(Node node) {
		if( node == null)
			return 0;
		return node.size;
	}

	public static void main(String[] args) {
		BST<String, Integer> bst = new BST<String, Integer>();
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
		bst.put("N", 14);
		System.out.println(bst.size());
	}
}
