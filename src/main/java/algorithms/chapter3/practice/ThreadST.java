package algorithms.chapter3.practice;

import com.chapter4.text.MyQueue;


public class ThreadST<Key extends Comparable<Key>,Value> {

	Node root;
	public class Node{
		Key key;		//键
		Value val;		// 值
		Node left,right;// 左子树,右子树
		int size;		// 以该节点为根的子树中的节点数量
		int level;		// 当前节点的层,根节点为0
		Key pred,succ; // 当前节点的前驱,后继节点
		public Node(Key key, Value val, int n,int level) {
			this.key = key;
			this.val = val;
			this.size = n;
			this.level = level;
		}
	}
	
	public void delete(Key key) {
		root = delete(root, key);
		balanceNodeInfo();
	}
	
	private Node delete(Node n,Key key) {
		if( n == null)
			return null;
		int cmp = key.compareTo(n.key);
		if( cmp > 0 )
			n.right =  delete(n.right,key);
		else if( cmp < 0 )
			n.left = delete(n.left,key);
		else {
			if( n.left == null )
				return n.right;
			if( n.right == null )
				return n.left;
			Node min = min(n.right);
			n.key = min.key;
			n.val = min.val;
			n.right = deleteMin(n.right);
		}
		n.size = size(n.left) + size(n.right) + 1;
		return n;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node n) {
		if( n.left == null )
			return n.right;
		n.left = deleteMin(n.left);
		n.size = size(n.left) + size(n.right) + 1;
		return n;
	}

	public void put(Key key,Value val){
		root = put(root,key,val,0);
		balanceNodeInfo();
	}

	private void balanceNodeInfo() {
		for(Key k : keys() ) {
			Node node = get(root, k);
			// 获取node的排名
			int rank = rank(node.key);
			if( rank > 0 && rank < root.size ) {
				node.succ = select(rank + 1);
				node.pred = select(rank - 1);
			}
			if( rank == 0)
				node.succ = select(rank + 1);
			if( rank == root.size - 1)
				node.pred = select(rank - 1);
		}
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
	
	
	
	private int size(Node node) {
		if( node == null)
			return 0;
		return node.size;
	}
	
	public Key prev(Key key){
		if( key.equals(min()))
			return null;
		return get(root, key).pred;
	}
	
	public Key next(Key key){
		if( key.equals(max()))
			return null;
		return get(root, key).succ;
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
	
	public Key min() {
		Node n = min(root);
		return n.key;
	}

	private Node min(Node root) {
		if( root == null || root.left == null)
			return root;
		else
			return min(root.left);
	}
	
	public Key max() {
		Node n = max(root);
		return n.key;
	}

	private Node max(Node root) {
		if( root == null || root.right == null)
			return root;
		else
			return max(root.right);
	}

	public static void main(String[] args) {
		
		ThreadST<Integer, Integer> bst = new ThreadST<Integer, Integer>();
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
		System.out.println("添加之后，各个key对应的前驱和后继节点如下：");
		for(Integer key : bst.keys() ) {
			System.out.printf("Key:%d,prev key :%d,next key:%d\n",key,bst.prev(key),bst.next(key));
		}
		bst.delete(20);
		bst.delete(24);
		System.out.println("删除若干key之后，各个key对应的前驱和后继节点如下：");
		for(Integer key : bst.keys() ) {
			System.out.printf("Key:%d,prev key :%d,next key:%d\n",key,bst.prev(key),bst.next(key));
		}
	}
	
}
