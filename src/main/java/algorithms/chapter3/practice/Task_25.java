package algorithms.chapter3.practice;

import algorithms.chapter3.BST;

public class Task_25<Key,Value> {
	
	static BST<Integer, Integer> bst = new BST<Integer, Integer>();
	
	/**
	 * 根据有序数组{@code keys}构造出和二分查找等价的BST.
	 * @param keys
	 */
	public static  BST<Integer, Integer> buildBST(int[] keys) {
		perfect(keys, 0, keys.length - 1);
		return bst;
	}
	
	static void  perfect(int[] keys,int lo,int hi){
		if( lo > hi )
			return;
		int mid = lo + (hi - lo)/2;
		bst.put(keys[mid], null);
		perfect(keys,lo,mid-1);
		perfect(keys,mid+1,hi);
	}
	
	public static void main(String[] args) {
		int[] keys = {1,2,3,4,5};
		BST<Integer, Integer> bst = buildBST(keys);
		bst.printLevel();
		BinaryTreeViewer<Integer, Integer> viewer = new BinaryTreeViewer<Integer, Integer>(bst);
		viewer.refresh();
	}

}
