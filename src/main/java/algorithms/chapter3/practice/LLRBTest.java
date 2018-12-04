package algorithms.chapter3.practice;

import algorithms.chapter3.RedBlackBST;

public class LLRBTest {

	public static void main(String[] args) {
		RedBlackBST<String, Integer> bst = new RedBlackBST<String, Integer>();
		for( char i = 'A'; i <= 'N'; i++) {
			bst.put(String.valueOf(i), 1);
		}
		bst.deleteMax();
		RedBlackBSTViewer<String, Integer> viewer = 
				new RedBlackBSTViewer<String, Integer>(bst);
		viewer.refresh();
	}

}
