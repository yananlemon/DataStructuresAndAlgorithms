package com.chapter6.avl;

/**
 * <p>TestAVLTree</p>
 * <p>测试AVL树</p>
 * @author yanan  
 * @date 2018年4月3日
 */
public class TestAVLTree {

	public static void main(String[] args) {
		AVLTree<Integer> avl = new AVLTree<Integer>();
		for(int i = 1; i<1000001 ; i++){
			avl.insert(i);
		}
		/*avl.insert(10);
		avl.insert(12);
		avl.insert(15);
		*/
		/*avl.insert(3);
		avl.insert(2);
		avl.insert(1);
		avl.insert(4);
		avl.insert(5);
		avl.insert(6);//6 7 16 15 14 13 12 11 10 8 9 
		avl.insert(7);
		avl.insert(16);
		avl.insert(15);
		avl.insert(14);
		avl.insert(13);
		avl.insert(12);
		avl.insert(11);
		avl.insert(10);
		avl.insert(8);
		avl.insert(9);*/
		System.out.println("max is:"+avl.findMax());
		System.out.println("min is:"+avl.findMin());
		
		System.out.println("二叉搜索树的高度是："+avl.root.height);
		System.out.println("in order iterate:");
		//avl.inorder(avl.root);
		avl.delete(8);
		avl.delete(9);
		avl.delete(10);
		avl.delete(1);
		avl.delete(15);
		avl.delete(19);
		System.out.println("\n");
		System.out.println(avl.find(8));
		System.out.println("二叉搜索树的高度是："+avl.root.height);
	}

}
