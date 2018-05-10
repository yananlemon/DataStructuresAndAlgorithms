package com.chapter6;

import java.util.ArrayList;

import com.chapter6.bst.BinaryNode;
import com.chapter6.bst.BinarySearchTree;

/**
 * <p>TwentyFiveQuestion</p>
 * <p>将二叉查找树分割为两棵树，其中一棵树的键值小于K，另一棵树的键值大于等于K。此处K为树种某个键值</p>
 * @author yanan
 * @date 2018年5月10日
 */
public class TwentyFiveQuestion {

	public static void main(String[] args) {
		// 构造原始的BST
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(10);
		tree.insert(8);
		tree.insert(7);
		tree.insert(9);
		tree.insert(12);
		tree.insert(11);
		tree.insert(13);
		
		splitBSTWithKey(tree, 10);
		System.out.println(tree1);
		System.out.println(tree2);
	}
	
	static BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
	static BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>();
	static Integer[] array1,array2;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	
	static void splitBSTWithKey(BinarySearchTree<Integer> t,Integer k){
		if(t.find(k) == null){
			throw new Error(k + "在BST中不存在！");
		}
		// 转换为数组
		toArray(t.root);
		
		// 记录K所在数组的索引
		int positionIndex = 0;
		for (;positionIndex < list.size(); positionIndex++) {
			if(list.get(positionIndex).equals(k)){
				break;
			}
		}
		for(int i = 0; i < positionIndex; i++){
			tree1.insert(list.get(i));
		}
		for(int i = positionIndex; i < list.size(); i++){
			tree2.insert(list.get(i));
		}
		
	}
	
	
	static void toArray(BinaryNode<Integer> t){
		if(t != null){
			toArray(t.left);
			list.add(t.element);
			toArray(t.right);
		}
	}

}
