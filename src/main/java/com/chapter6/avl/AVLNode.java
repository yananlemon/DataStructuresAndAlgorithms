package com.chapter6.avl;
/**
 * <p>AVLNode</p>
 * <p>AVL树节点</p>
 * @author yanan  
 * @date 2018年4月3日
 */
class AVLNode<AnyType extends Comparable<AnyType>>{
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
}