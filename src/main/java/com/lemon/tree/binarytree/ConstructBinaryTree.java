package com.lemon.tree.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造二叉树
 * @author andy
 *
 */
public class ConstructBinaryTree {

	/**
	 * 测试用例
	 * 1.http://stackoverflow.com/questions/15888466/build-a-binary-tree-from-inorder-and-preorder-data-in-java
	 * 2.http://articles.leetcode.com/construct-binary-tree-from-inorder-and-preorder-postorder-traversal/
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*int[] preOrder={7,10,4,3,1,2,8,11};

		int[] inOrder={4,10,3,1,7,11,8,2};  pass*/
		/*int[] preOrder={1,2,3};

		int[] inOrder={2,1,3}; pass */
		
		/*Object[] preOrder={1,2,4,5,3};

		Object[] inOrder={4,2,5,1,3}; pass */
		
		Object[] preOrder={1,2,4,5,3,6};

		Object[] inOrder={4,2,5,1,3,6};/* pass */
		
		/*Object[] preOrder={234, 98, 45, 124, 678, 1789};
		Object[] inOrder={45, 98, 124, 234, 678, 1789}; pass*/

		/*Object[] preOrder={1,2,3};
		Object[] inOrder={2,1,3}; pass */
		
		BinaryTree root=null;
		root=bulidBinaryTreeFromInOrderAndPreOrder2(preOrder, inOrder);
		//System.out.println(root);
		//BinaryTreeUtil.printBinaryTree(root);
		BTreePrinter.printNode(root);
		
	}
	
	
	/**
	 * 重构二叉树
	 * @param preOrder: 前序遍历结果,例如 :a b d c e f
	 * @param inOrder:  中序遍历结果,例如 :d b a e c f
	 * @return root:返回重构的二叉树
	 */
	static BinaryTree bulidBinaryTreeFromInOrderAndPreOrder(int[] preOrder,int[] inOrder){
		BinaryTree root;
		if((preOrder==null || preOrder.length<=0)&&
				(inOrder==null || inOrder.length<=0)){
			return null;
		}
		if(preOrder.length==1 && inOrder.length==1){
			root=new BinaryTree(preOrder[0]);
			return root;
		}
		if(preOrder.length>1){
			//前序序列中第一个元素就是根
			if(preOrder[0]==3){
				System.out.println();
			}
			root=new BinaryTree(preOrder[0]);
			
			//构造左子树的inOrder数组
			int leftLen=0;
			for(int i=0;i<inOrder.length;i++){
				if(inOrder[i]!=preOrder[0]){
					leftLen++;
				}else{
					break;
				}
			}
			int[] childLeftInOrder=new int[leftLen];//{4,10,3,1};
			for(int i=0;i<leftLen;i++){
				childLeftInOrder[i]=inOrder[i];
			}
			
			//构造左子树的preOrder数组{10,4,3,1}
			int[] childLeftPreOrder=new int[leftLen];
			int temp=0;
			for(int k=0;k<preOrder.length;k++){//{7,10,4,3,1,2,8,11};
				int m=preOrder[k];
				for(int i=0;i<leftLen;i++){
					if(childLeftInOrder[i]==m){
						childLeftPreOrder[temp]=m;
						temp++;
						break;
					}
				}
			}
			
			//构造右子树的inOrder数组
			int rightLen=0;
			int index=0;
			for(int i=0;i<inOrder.length;i++){
				if(inOrder[i]==preOrder[0]){
					rightLen=inOrder.length-i-1;
					index=i+1;
					break;
				}
			}
			int[] childRightInOrder=new int[rightLen];//{11,8,2};
			for(int i=0;i<rightLen;i++){
				childRightInOrder[i]=inOrder[index];
				index++;
			}

			//构造右子树的preOrder数组{2,8,11}
			int[] childRightPreOrder=new int[rightLen];
			List<Integer> indexs=new ArrayList<Integer>();
			for(int k=0;k<childRightInOrder.length;k++){//11,8,2
				int o1=childRightInOrder[k];
				for(int i=0;i<preOrder.length;i++){//[7, 10, 4, 3, 1, 2, 8, 11]
					int o2=preOrder[i];
					if(o1==o2){
						indexs.add(i);
						break;
					}
				}
			}
			
			//indexs:[7,6,5]
			Collections.sort(indexs);
			for(int i=0;i<childRightInOrder.length;i++){
				childRightPreOrder[i]=preOrder[indexs.get(i)];
			}
			root.left=bulidBinaryTreeFromInOrderAndPreOrder(childLeftPreOrder,childLeftInOrder);
			root.right=bulidBinaryTreeFromInOrderAndPreOrder(childRightPreOrder,childRightInOrder);
			return root;
		}
		return null;
	}
	
	/**
	 * 重构二叉树
	 * @param preOrder: 前序遍历结果,例如 :a b d c e f
	 * @param inOrder:  中序遍历结果,例如 :d b a e c f
	 * @return root:返回重构的二叉树
	 */
	static BinaryTree bulidBinaryTreeFromInOrderAndPreOrder2(Object[] preOrder,Object[] inOrder){
		BinaryTree root;
		if((preOrder==null || preOrder.length<=0)&&
				(inOrder==null || inOrder.length<=0)){
			return null;
		}
		if(preOrder.length==1 && inOrder.length==1){
			root=new BinaryTree(preOrder[0]);
			return root;
		}
		if(preOrder.length>1){
			//前序序列中第一个元素就是根
			root=new BinaryTree(preOrder[0]);
			
			//构造左子树的inOrder数组
			int leftLen=0;
			for(int i=0;i<inOrder.length;i++){
				if(!inOrder[i].equals(preOrder[0])){
					leftLen++;
				}else{
					break;
				}
			}
			Object[] childLeftInOrder=new Object[leftLen];//{4,10,3,1};
			for(int i=0;i<leftLen;i++){
				childLeftInOrder[i]=inOrder[i];
			}
			
			//构造左子树的preOrder数组{10,4,3,1}
			Object[] childLeftPreOrder=new Object[leftLen];
			int temp=0;
			for(int k=0;k<preOrder.length;k++){//{7,10,4,3,1,2,8,11};
				Object m=preOrder[k];
				for(int i=0;i<leftLen;i++){
					if(childLeftInOrder[i].equals(m)){
						childLeftPreOrder[temp]=m;
						temp++;
						break;
					}
				}
			}
			
			//构造右子树的inOrder数组
			int rightLen=0;
			int index=0;
			for(int i=0;i<inOrder.length;i++){
				if(inOrder[i].equals(preOrder[0])){
					rightLen=inOrder.length-i-1;
					index=i+1;
					break;
				}
			}
			Object[] childRightInOrder=new Object[rightLen];//{11,8,2};
			for(int i=0;i<rightLen;i++){
				childRightInOrder[i]=inOrder[index];
				index++;
			}

			//构造右子树的preOrder数组{2,8,11}
			Object[] childRightPreOrder=new Object[rightLen];
			List<Integer> indexs=new ArrayList<Integer>();
			for(int k=0;k<childRightInOrder.length;k++){//11,8,2
				Object o1=childRightInOrder[k];
				for(int i=0;i<preOrder.length;i++){//[7, 10, 4, 3, 1, 2, 8, 11]
					Object o2=preOrder[i];
					if(o1.equals(o2)){
						indexs.add(i);
						break;
					}
				}
			}
			
			//indexs:[7,6,5]
			Collections.sort(indexs);
			for(int i=0;i<childRightInOrder.length;i++){
				childRightPreOrder[i]=preOrder[indexs.get(i)];
			}
			root.left=bulidBinaryTreeFromInOrderAndPreOrder2(childLeftPreOrder,childLeftInOrder);
			root.right=bulidBinaryTreeFromInOrderAndPreOrder2(childRightPreOrder,childRightInOrder);
			return root;
		}
		return null;
	}

}
