package com.chapter6;

import com.chapter6.avl.AVLNode;
import com.chapter6.avl.AVLTree;

/**
 * <p>ThreeQuestion</p>
 * <p>习题第3题</p>
 * @author yanan  
 * @date 2018年4月4日
 */
public class ThreeQuestion {

	public static void main(String[] args) {
		AVLTree<Integer> avl = new AVLTree<Integer>();
		/*for(int i = 1; i<20 ; i++){
			avl.insert(i);
		}*/
		avl.insert(1);
		avl.insert(2);
		avl.insert(3);
		System.out.println(countHeight(avl.getRoot()));
		System.out.println(calDepth(avl.getRoot()));
		System.out.println("是否AVL树"+isAVLTree(avl.getRoot()));
		System.out.println("是否完全平衡"+isPerfactBalancedTree(avl.getRoot()));
	}
	
	/**
	 * 计算二叉树的高度
	 * 算法：从根节点开始，递归计算左子树高度、右子树高度，取二者较大值＋1
	 * @param root
	 * @return
	 */
	static public int countHeight(AVLNode<Integer> root){
		if(root == null){
			return 0;
		}
		int leftHeight = 0;
		int rightHeight = 0;
		if(root.getLeft() != null){
			return countHeight(root.getLeft())+1;
		}
		if(root.getRight() != null){
			return countHeight(root.getRight())+1;
		}
		return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
	}
	
	/**
	 * 获取二叉树高度
	 * @param root
	 * @return
	 */
	static int calDepth(AVLNode<Integer> root) {
		if(root == null) {
			return 0;
		}
		return Math.max(calDepth(root.getLeft()), calDepth(root.getLeft()))+1;
	}
	
	static boolean isAVLTree(AVLNode<Integer> root) {
		if(root == null) {
			return true;
		}else {
			int leftHight = countHeight(root.getLeft());
			int rightHight = countHeight(root.getRight());
			if(Math.abs(leftHight - rightHight) >1) {
				return false;
			}else {
				return isAVLTree(root.getLeft()) && isAVLTree(root.getRight());
			}
		}
	}
	
	static boolean isPerfactBalancedTree(AVLNode<Integer> root) {
		if(root == null) {
			return true;
		}else {
			int leftHight = calDepth(root.getLeft());
			int rightHight = calDepth(root.getRight());
			if(Math.abs(leftHight - rightHight) >0) {
				return false;
			}else {
				return isPerfactBalancedTree(root.getLeft()) && isPerfactBalancedTree(root.getRight());
			}
		}
	}

}
