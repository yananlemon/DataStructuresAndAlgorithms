package algorithms.chapter3.practice;

import algorithms.chapter3.BST;

public class Task_29 {

	public static boolean isBinayTree(BST<Integer,Integer>.Node n){
		int totalCount = calNodeCount(n);
		return totalCount == n.getSize() ? true : false;
	}

	private static int calNodeCount(BST<Integer, Integer>.Node n) {
		if( n == null)
			return 0;
		return calNodeCount(n.getLeft()) + calNodeCount(n.getRight()) +1;
	}
	public static void main(String[] args) {
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
		System.out.println(isBinayTree(bst.root));
	}
}
