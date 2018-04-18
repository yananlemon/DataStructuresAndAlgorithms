package com.chapter6;

import com.chapter6.avl.AVLTree;

public class TwelfthQuestion {

	public static void main(String[] args) {
		AVLTree<Character> tree = new AVLTree<Character>();
		for (char i = 'a'; i <= 'e'; i++) {
			tree.insert(i);
		}
		System.out.println("created!");
	}

}
