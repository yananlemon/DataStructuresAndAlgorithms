package com.lemon.tree.binarytree;

/**
 * 二叉树节点
 * @author andy
 *
 */
public class BinaryTree {
	BinaryTree left;
	BinaryTree right;
	Object value;
	public BinaryTree(BinaryTree left, BinaryTree right, Object value) {
		this.left = left;
		this.right = right;
		this.value = value;
	}
	public BinaryTree(Object value) {
		this.value = value;
	}
}
