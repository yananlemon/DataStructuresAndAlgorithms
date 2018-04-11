package com.chapter6.bst;

/**
 * <p>BinarySearchTree</p>
 * <p>BST测试</p>
 * @author yanan
 * @date 2018年4月2日
 */
public class TestBinarySearchTree {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		/*for(int i = 1; i<101 ; i++){
			bst.insert(i);
		}
		System.out.println("max is:"+bst.findMax());
		System.out.println("min is:"+bst.findMin());
		System.out.println("in order iterate:");
		bst.inorder(bst.root);
		System.out.println("\n二叉搜索树高度"+bst.countHeight(bst.root));*/
		bst.insert(6);
		bst.insert(2);
		bst.insert(8);
		bst.insert(1);
		bst.insert(4);
		bst.insert(3);
		bst.inorder(bst.root);
		bst.delete(2);
		bst.inorder(bst.root);
	}

}
