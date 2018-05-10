package com.chapter6;

/**
 * <p>TwentySixQuestion</p>
 * <p>将两棵二叉树合并为一棵，不要将其中一棵树的节点一个一个地插入到另一棵中。</p>
 * @author yanan
 * @date 2018年5月10日
 */
public class TwentySixQuestion {

	public static void main(String[] args) {
		/**
		 *    10
		 *   /  \
		 *  20   5 
		 */
		BinaryTree tree1 = new BinaryTree(10, new BinaryTree(20), new BinaryTree(5));
		
		/**
		 *    100
		 *   /   \
		 *  200   50 
		 */
		BinaryTree tree2 = new BinaryTree(100, new BinaryTree(200), new BinaryTree(50));
		BinaryTree result = merge(tree1, tree2);
		
		/**
		 *    100
		 *   /   \
		 *  200  50
		 *         \
		 *         10
		 *         / \
		 *        20  5 
		 */
		System.out.println(result);
	}
	
	static BinaryTree merge(BinaryTree tree1,BinaryTree tree2){
		if(tree1 == null && tree2 == null){
			return null;
		}
		if(tree1 == null && tree2 != null){
			return tree2;
		}
		if(tree1 != null && tree2 == null){
			return tree1;
		}
		// 获取第二棵树最右节点
		BinaryTree rightNode = getRightNode(tree2);
		rightNode.setRight(tree1);
		return tree2;
		
	}
	
	/**
	 * 获取二叉树中最右边的节点
	 * @param t
	 * @return
	 */
	static BinaryTree getRightNode(BinaryTree t){
		if(t == null){
			return null;
		}
		if(t.getRight() == null){
			return t;
		}
		return getRightNode(t.getRight());
	}
	
	
	
	

}
