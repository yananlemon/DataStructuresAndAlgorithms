package algorithms.chapter3.practice;

import algorithms.chapter3.BST;

public class Task_17<Key,Value> {
	
	public static void main(String[] args) {
		BST<String, Integer> bst = new BST<String, Integer>();
		bst.put("E", 1);
		bst.put("A", 1);
		bst.put("S", 1);
		bst.put("Y", 1);
		bst.put("Q", 1);
		bst.put("U", 1);
		bst.put("E", 1);
		bst.put("S", 1);
		bst.put("T", 1);
		bst.put("I", 1);
		bst.put("O", 1);
		bst.put("N", 1);
		bst.printLevel();
		bst.delete("E");
		bst.delete("I");
		bst.delete("N");
		bst.delete("O");
		bst.delete("Q");
		bst.delete("S");
		bst.delete("T");
		bst.delete("U");
		bst.delete("Y");
		bst.delete("A");
		BinaryTreeViewer<String, Integer> viewer = new BinaryTreeViewer<String, Integer>(bst);
		viewer.refresh();
	}

}
