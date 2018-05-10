package com.chapter6;

/**
 * <p>FourQuesion</p>
 * <p>习题第4题：设计一个算法，测试二叉树是否是二叉查找树</p>
 * @author yanan  
 * @date 2018年3月31日
 */
public class FourQuesion {

	public static void main(String[] args) {
		BinaryTree root = new BinaryTree(6, null, new BinaryTree(8, null, null));
		BinaryTree left = new BinaryTree(2, new BinaryTree(1, null, null), 
				new BinaryTree(9, null, null));
		root.setLeft(left);
		System.out.println(isBST(root));
	}
	
	static boolean isBST(BinaryTree node){
		if(node == null){
			return true;
		}
		if(node.getLeft() != null && findMax(node.getLeft()) > node.getValue()){
			return false;
		}
		if(node.getRight() !=null && findMin(node.getRight()) < node.getValue()){
			return false;
		}
		if(!isBST(node.getLeft()) || !isBST(node.getRight())){
			return false;
		}
		return true;
	}
	
	/**
	 * 找到二叉查找树中最大值
	 * @param node
	 * @return
	 */
	static int findMax(BinaryTree node){
		int root,left,right,max=-1;
		if(node != null){
			root = node.getValue();
			left = findMax(node.getLeft());
			right = findMax(node.getRight());
			if(left > right){
				max = left;
			}else{
				max = right;
			}
			if(max < root){
				max = root;
			}
			
		}
		return max;
	}
	
	/**
	 * 找到二叉查找树中最大值
	 * @param node
	 * @return
	 */
	static int findMin(BinaryTree node){
		int root,left,right,max=-1;
		if(node != null){
			root = node.getValue();
			left = findMax(node.getLeft());
			right = findMax(node.getRight());
			if(left < right){
				max = left;
			}else{
				max = right;
			}
			if(max > root){
				max = root;
			}
			
		}
		return max;
	}

}
class BinaryTree{
	private int value;
	private BinaryTree left;
	private BinaryTree right;
	
	public BinaryTree(int value, BinaryTree left, BinaryTree right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public BinaryTree(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public BinaryTree getLeft() {
		return left;
	}
	public void setLeft(BinaryTree left) {
		this.left = left;
	}
	public BinaryTree getRight() {
		return right;
	}
	public void setRight(BinaryTree right) {
		this.right = right;
	}
	
}
