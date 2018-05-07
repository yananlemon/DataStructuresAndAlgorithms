package com.chapter6;

/**
 * <p>SecondQuesion</p>
 * <p>习题第2题：编写函数：</p>
 * <p>a.计算二叉树中的节点数目</p>
 * <p>b.计算叶子节点数目</p>
 * <p>c.计算二叉树中右子节点的数目</p>
 * <p>d.计算二叉树的高度</p>
 * <p>e.删除二叉树中的所有叶子节点</p>
 * @author yanan  
 * @date 2018年3月31日
 */
public class SecondQuesion {

	public static void main(String[] args) {
		/**     8
		 *    /  \
		 *   5     10
		 *  / \   /  \
		 * 2   6 11  12
		 */
		BinaryTree left = new BinaryTree(5, new BinaryTree(2, null, null), 
				new BinaryTree(6, null, null));
		BinaryTree right = new BinaryTree(10, new BinaryTree(11, null, null), 
				new BinaryTree(12, null, null));
		BinaryTree root = new BinaryTree(8, left,right);
		System.out.println("节点数量:"+countNode(root));
		System.out.println("叶子节点数量:"+countLeafNode(root));
		BinaryTree test = new BinaryTree(10, null, new BinaryTree(11, null,new BinaryTree(12, null, new BinaryTree(13, null, null))));
		System.out.println("右子节点数量:"+countRightChildren(root));
		System.out.println("树的高度:"+countHeight(test));
		deleteLeafNode(root);
	}
	
	/**
	 * 计算二叉树节点数目
	 * @param root
	 * @return int 节点数目
	 */
	static int countNode(BinaryTree root){
		if(root == null){
			return 0;
		}
		return (countNode(root.getLeft()) + 1 + countNode(root.getRight()));
	}
	
	
	/**
	 * 计算二叉树叶子节点数目
	 * @param root
	 * @return int 叶子节点数目
	 */
	static int countLeafNode(BinaryTree root){
		if(root == null){
			return 0;
		}
		if(root.getLeft() == null && root.getRight() == null){
			return 1;
		}
		int leftLeafNodeCount = 0;
		int rightLeafNodeCount = 0;
		if(root.getLeft() != null){
			leftLeafNodeCount = countLeafNode(root.getLeft());
		}
		if(root.getRight() != null){
			rightLeafNodeCount = countLeafNode(root.getRight());
		}
		return leftLeafNodeCount + rightLeafNodeCount;
	}
	
	/**
	 * 计算二叉树中右子节点的数目
	 * @param root
	 * @return
	 */
	static int countRightChildren(BinaryTree root){
		if(root == null){
			return 0;
		}
		int rightChildrenCount = 0;
		if(root.getLeft() != null){
			rightChildrenCount += countRightChildren(root.getLeft());
		}
		if(root.getRight() != null){
			rightChildrenCount++;
			rightChildrenCount += countRightChildren(root.getRight());
		}
		return rightChildrenCount;
	}
	
	/**
	 * 计算二叉树的高度
	 * 算法：从根节点开始，递归计算左子树高度、右子树高度，取二者较大值＋1
	 * @param root
	 * @return
	 */
	static int countHeight(BinaryTree root){
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
	
	static BinaryTree deleteLeafNode(BinaryTree root){
		if(root == null){
			return null;
		}
		if(root.getLeft() == null && root.getRight() == null){
			return null;
		}
		if(root != null){
			root.setLeft(deleteLeafNode(root.getLeft()));
			root.setRight(deleteLeafNode(root.getRight()));
		}
		return root;
		
	}
	
	

}
