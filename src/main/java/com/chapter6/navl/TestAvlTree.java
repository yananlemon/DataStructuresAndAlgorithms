package com.chapter6.navl;

public class TestAvlTree {

	public static void main(String[] args) {
		// 25，27，30，12，11，18，14，20，15，22
		AvlTree<Integer> avl = new AvlTree<Integer>();
		/*avl.insert(25);
		avl.insert(27);
		avl.insert(30);
		avl.insert(12);
		avl.insert(11);
		avl.insert(18);
		avl.insert(14);
		avl.insert(20);
		avl.insert(15);
		avl.insert(22);*/
		for(int i = 1; i<1000001 ; i++){
			avl.insert(i);
		}
		System.out.println(avl.getHeight());
	}

}