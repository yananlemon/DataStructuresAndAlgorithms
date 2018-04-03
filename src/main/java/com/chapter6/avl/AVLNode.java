package com.chapter6.avl;
/**
 * <p>AVLNode</p>
 * <p>AVL树节点</p>
 * @author yanan  
 * @date 2018年4月3日
 */
public class AVLNode<AnyType extends Comparable<AnyType>>{
	AnyType element;
	int height;
	AVLNode<AnyType> left;
	AVLNode<AnyType> right;
	public AVLNode() {
		
	}
	public AVLNode(AnyType element) {
		super();
		this.element = element;
	}
	public AnyType getElement() {
		return element;
	}
	public void setElement(AnyType element) {
		this.element = element;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public AVLNode<AnyType> getLeft() {
		return left;
	}
	public void setLeft(AVLNode<AnyType> left) {
		this.left = left;
	}
	public AVLNode<AnyType> getRight() {
		return right;
	}
	public void setRight(AVLNode<AnyType> right) {
		this.right = right;
	}
	
	
}