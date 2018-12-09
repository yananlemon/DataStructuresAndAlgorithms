package algorithms.chapter3;

import com.chapter4.text.MyQueue;



/**
 * 基于AVL树实现的符号表
 * @author andy
 * @param <Key>
 * @param <Value>
 */
public class AVLBST<Key extends Comparable<Key>,Value> {

	public Node root;
	
	public class Node{
		Key key;
		Value val;
		Node left,right;
		int height; 
		int size;
		public Node(Key key, Value val, int height,int size) {
			this.key = key;
			this.val = val;
			this.height = height;
			this.size = size;
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
		assert check();
	}
	
	private Node put(Node n, Key key, Value val) {
		if( n == null )
			return new Node(key, val, 0,1);
		int cmp = key.compareTo(n.key);
		if( cmp > 0 )
			n.right = put(n.right, key, val);
		else if( cmp < 0 )
			n.left = put(n.left, key, val);
		else {
			n.val = val;
			return n;
		}
		n.size = 1 + size(n.left) + size(n.right);;
		n.height = 1 + Math.max(height(n.left), height(n.right));
		return balance(n);
	}
	
	

	public void delete(Key key){
		if( get(root, key) == null ) {
			return;
		}
		root = delete(root,key);
	}
	
	private Node delete(Node x, Key key) {
		int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }
        else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            else if (x.right == null) {
                return x.left;
            }
            else {
                Node y = x;
                x = findMin(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
	}
	
	public Key findMin() {
		Node n = findMin(root);
		return n == null ? null : n.key;
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
	public Key findMax() {
		Node n = findMax(root);
		return n == null ? null : n.key;
	}
	private Node findMax(Node node) {
		if(node == null){
			return null;
		}
		if(node.right == null){
			return node;
		}
		return findMax(node.right);
	}

	public void deleteMin(){
		root = deleteMin(root);
		assert check();
	}
	
	private Node deleteMin(Node n){
		if( n.left == null )
			return n.right;
		n.left = deleteMin(n.left);
		n.size = 1 + size(n.left) + size(n.right);
		n.height = 1 + Math.max(height(n.left), height(n.right));
		return balance(n);
	}
	
	public void deleteMax(){
		root = deleteMax(root);
		assert check();
	}
	
	private Node deleteMax(Node n){
		if( n.right == null )
			return n.left;
		n.right = deleteMax(n.right);
		n.size = 1 + size(n.left) + size(n.right);
		n.height = 1 + Math.max(height(n.left), height(n.right));
		return balance(n);
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
	 * 返回{@code n}的平衡因子，在AVL树中，平衡因子只能是-1，0，1。
	 * @param n
	 * @return n的平衡因子
	 */
	public int balanceFactor(Node n) {
		return height( n.left) - height( n.right );
	}
	
	/**
	 * 恢复节点平衡
	 * @param n
	 * @return
	 */
	private Node balance(Node n) {
		if( balanceFactor(n) < -1 ){
			if( balanceFactor(n.right) > 0 ) {
				n.right = rotateRight(n.right);
			}
			n = rotateLeft(n);
		}
		else if( balanceFactor(n) > 1 ) {
			if( balanceFactor(n.left) < 0 ) {
				n.left = rotateLeft(n.left);
			}
			n = rotateRight(n);
		} 
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
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		h.height = 1 + Math.max(height(h.left), height(h.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
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
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		h.height = 1 + Math.max(height(h.left), height(h.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}
	 /**
     * Returns the number key-value pairs in the symbol table.
     * 
     * @return the number key-value pairs in the symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Returns the number of nodes in the subtree.
     * 
     * @param x the subtree
     * 
     * @return the number of nodes in the subtree
     */
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }
	
	 /**
     * Checks if the AVL tree invariants are fine.
     * 
     * @return {@code true} if the AVL tree invariants are fine
     */
    private boolean check() {
        if (!isBST()) System.out.println("Symmetric order not consistent");
        if (!isAVL()) System.out.println("AVL property not consistent");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) System.out.println("Ranks not consistent");
        return isBST() && isAVL() && isSizeConsistent() && isRankConsistent();
    }

    /**
     * Checks if AVL property is consistent.
     * 
     * @return {@code true} if AVL property is consistent.
     */
    public boolean isAVL() {
        return isAVL(root);
    }

    /**
     * Checks if AVL property is consistent in the subtree.
     * 
     * @param x the subtree
     * @return {@code true} if AVL property is consistent in the subtree
     */
    private boolean isAVL(Node x) {
        if (x == null) return true;
        int bf = balanceFactor(x);
        if (bf > 1 || bf < -1) return false;
        return isAVL(x.left) && isAVL(x.right);
    }

    /**
     * Checks if the symmetric order is consistent.
     * 
     * @return {@code true} if the symmetric order is consistent
     */
    private boolean isBST() {
        return isBST(root, null, null);
    }

    /**
     * Checks if the tree rooted at x is a BST with all keys strictly between
     * min and max (if min or max is null, treat as empty constraint) Credit:
     * Bob Dondero's elegant solution
     * 
     * @param x the subtree
     * @param min the minimum key in subtree
     * @param max the maximum key in subtree
     * @return {@code true} if if the symmetric order is consistent
     */
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    /**
     * Checks if size is consistent.
     * 
     * @return {@code true} if size is consistent
     */
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    /**
     * Checks if the size of the subtree is consistent.
     * 
     * @return {@code true} if the size of the subtree is consistent
     */
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    /**
     * Checks if rank is consistent.
     * 
     * @return {@code true} if rank is consistent
     */
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
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
     * Returns the kth smallest key in the symbol table.
     * 
     * @param k the order statistic
     * @return the kth smallest key in the symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *             {@code size() -1 }
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
        Node x = select(root, k);
        return x.key;
    }

    /**
     * Returns the node with key the kth smallest key in the subtree.
     * 
     * @param x the subtree
     * @param k the kth smallest key in the subtree
     * @return the node with key the kth smallest key in the subtree
     */
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    /**
     * Returns the number of keys in the symbol table strictly less than
     * {@code key}.
     * 
     * @param key the key
     * @return the number of keys in the symbol table strictly less than
     *         {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    /**
     * Returns the number of keys in the subtree less than key.
     * 
     * @param key the key
     * @param x the subtree
     * @return the number of keys in the subtree less than key
     */
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }
}
