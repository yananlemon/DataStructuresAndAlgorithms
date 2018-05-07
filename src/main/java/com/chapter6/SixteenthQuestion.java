package com.chapter6;

import com.chapter6.navl.AvlTree;

/**
 * <p>SixteenthQuesion</p>
 * <p>第十六题</p>
 * @author yanan
 * @date 2018年4月24日
 */
public class SixteenthQuestion {

	public static void main(String[] args) {
		AvlTree<Character> tree = new AvlTree<Character>();
		for (char i = 'a'; i <= 'j'; i++) {
			tree.insert(i);
		}
		System.out.println("created!");
		
	}

}
