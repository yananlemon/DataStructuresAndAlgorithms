package com.chapter6.bst;
public class BinaryNode<AnyType>{
	public AnyType element;
	public BinaryNode<AnyType> left;
	public BinaryNode<AnyType> right;
	public BinaryNode(AnyType element){
		this.element = element;
		left = null;
		right = null;
	}
}