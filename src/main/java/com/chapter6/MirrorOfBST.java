package com.chapter6;


import com.chapter6.bst.BinaryNode;
import com.chapter6.bst.BinarySearchTree;
/**
 * <p>MirrorOfBST</p>
 * <p>编写一个函数，创建二叉查找树的镜像</p>
 * @author yanan
 * @date 2018年4月27日
 */
public class MirrorOfBST {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		// 第一种情况：
		// 	 	 10						 10
		//		/  \       镜像                 /   \
		//     5    20    ------>     20    5 
		//    / \   / \              / \   / \
		//   1   6 15 30            30 15 6   1 
		bst.insert(10);
		bst.insert(5);
		bst.insert(20);
		bst.insert(1);
		bst.insert(6);
		bst.insert(15);
		bst.insert(30);
		
		// 第二种情况
		// 10                        10
		//  \            镜像                         /
		//   20			----->   	20 
		//    \                    /
		//     30                 30 
		/*bst.insert(10);
		bst.insert(20);
		bst.insert(30);*/
		
		// 第三种情况
		//    10                    10
		//    /            镜像                    \
		//   5			----->   	  5
		//  /                          \
		// 1                            1
		/*bst.insert(10);
		bst.insert(5);
		bst.insert(1);*/
		
		// 第四种情况：
		// 	 	 10						 10
		//		/  \       镜像                          /   \
		//     5    20    ------>     20    5 
		//    /      \               /       \
		//   1       30             30        1 
		/*bst.insert(10);
		bst.insert(5);
		bst.insert(20);
		bst.insert(1);
		bst.insert(30);*/
		BinaryNode<Integer> t = recursion(bst.root);
		System.out.println(t);
	}
	
	static BinaryNode<Integer> recursion(BinaryNode<Integer> t){
		if(t == null)
			return null;
		if(t.left != null && t.right != null){
			BinaryNode<Integer> tmp = t.right;
			t.right = recursion(t.left);
			t.left  = recursion(tmp);
		}
		if(t.left == null && t.right == null)
			return t;
		if(t.left == null && t.right != null){
			t.left  = recursion(t.right);
			t.right = null;
			return t;
		}
		if(t.left != null && t.right == null){
			t.right = recursion(t.left);
			t.left  = null;
			return t;
		}
		return t;
	}

}
