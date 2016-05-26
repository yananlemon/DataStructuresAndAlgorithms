package com.lemon.tree.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeUtil {
	
	/**
	 * 获取二叉树节点个数
	 * @param root
	 * @return
	 */
	public static int countBinaryTree(BinaryTree root){
		if(root==null){
			return 0;
		}
		int count=1;
		if(root.left!=null){
			count+=countBinaryTree(root.left);
		}
		if(root.right!=null){
			count+=countBinaryTree(root.right);
		}
		
		return count;
		
	}
	
	/**
	 * 打印指定level的二叉树节点
	 * @param root
	 * @param level
	 * @return
	 */
	public static int printBinaryTreeAtLevel(BinaryTree root,int level){
		if(root==null || level<1){
			return 0;
		}
		if(level==1){
			//int count=getBinaryTreeNodeAtLevel(root, level).size();
//			StringBuffer sb=new StringBuffer();
//			for(int i=0;i<level*count;i++){
//				sb.append(" ");
//			}
//			System.out.print(sb.toString()+ root.value+sb.toString());
			System.out.print(root.value);
			return 1;
		}else{
			return printBinaryTreeAtLevel(root.left, level-1)+printBinaryTreeAtLevel(root.right, level-1);
		}
	}
	
	private static List<String> levelNodeList=new ArrayList<String>();
	
	/**
	 * 获取指定层级的所有节点:
	 * 例如，对于如下二叉树而言：
	 * 			+
	 *		1		*
	 *			2		5 
	 * 当level=1时,返回[+]
	 * 当level=3时,返回[2,5]
	 * @param root
	 * @param level
	 * @return
	 */
	public static List<String> getBinaryTreeNodeAtLevel(BinaryTree root,int level){
		if(root==null || level<1){
			return null;
		}
		if(level==1){
			levelNodeList.add(root.value.toString());
		}else{
			getBinaryTreeNodeAtLevel(root.left, level-1);
			getBinaryTreeNodeAtLevel(root.right, level-1);
		}
		return levelNodeList;
	}
	
	
	
	/**
	 * 计算二叉树深度
	 * @return
	 */
	public static int calculateBinaryDepth(BinaryTree tree){
	    if(tree==null)//如果pRoot为NULL，则深度为0，这也是递归的返回条件
	        return 0;
	    //如果pRoot不为NULL，那么深度至少为1，所以left和right=1
	    int left=1;
	    int right=1;
	    left+=calculateBinaryDepth(tree.left);//求出左子树的深度
	    right+=calculateBinaryDepth(tree.right);//求出右子树深度

	    return left>right?left:right;//返回深度较大的那一个
	}
	
	/**
	 * 根据层次结构打印二叉树
	 * @param btree
	 */
	public static void printBinaryTree(BinaryTree btree){
		int depth=BinaryTreeUtil.calculateBinaryDepth(btree);
		for(int i=1;i<=depth;i++){
			BinaryTreeUtil.printBinaryTreeAtLevel(btree, i);
			System.out.println();
			
		}
	}
	
	/**
	 * 获取二叉树的宽度:即遍历二叉树每一层，叶子数最多的那一层，其数量就是二叉树的宽度。
	 * @param btree
	 * @return
	 */
	public static int calculateWidth(BinaryTree btree){
		if(btree==null){
			return 0;
		}
		List<Integer> tempList=new ArrayList<Integer>();
		int depth=BinaryTreeUtil.calculateBinaryDepth(btree);
		for(int i=1;i<=depth;i++){
			int width=getLeafNumByLevel(btree, i);
			tempList.add(width);
		}
		Collections.sort(tempList);
		
		return tempList.get(tempList.size()-1);
		
	}
	
	
	static void width(BinaryTree btree ,int level){
	    Map<Integer,Integer> mp = new HashMap<Integer,Integer>();
	    widthImpl(mp, btree, level);
	    System.out.println(mp);
	    // find maximum
	}

	private static void widthImpl(Map<Integer,Integer> mp, BinaryTree btree, int level) {
	    if(btree==null)
	        return;
	    if(mp.containsKey(level)){
	        int count = mp.get(level);
	        mp.put(level, count+1);
	    }else{
	        mp.put(level, 1);
	    }

	    widthImpl(mp, btree.left, level+1);
	    widthImpl(mp, btree.right, level+1);
	}
	
	
	
	/**
	 * 获取二叉树每一层的宽度
	 * @param btree
	 * @return
	 */
	public static List<Integer> calculateWidthList(BinaryTree btree){
		if(btree==null){
			return null;
		}
		List<Integer> tempList=new ArrayList<Integer>();
		int depth=BinaryTreeUtil.calculateBinaryDepth(btree);
		for(int i=1;i<=depth;i++){
			int width=getLeafNumByLevel(btree, i);
			tempList.add(width);
		}
		Collections.sort(tempList);
		
		return tempList;
		
	}
	
	/**
	 * 获取二叉树某一个层的节点个数
	 * @param btree
	 * @param level
	 * @return
	 */
	public static int getLeafNumByLevel(BinaryTree btree,int level){
		if(btree==null || level<1){
			return 0;
		}
		if(level==1){
			return 1;
		}else{
			return getLeafNumByLevel(btree.left,level-1)+getLeafNumByLevel(btree.right, level-1);
		}
	}
	
	public static void printPrettyBinaryTree(BinaryTree btree){
		int depth=BinaryTreeUtil.calculateBinaryDepth(btree);
		for(int i=1;i<=depth;i++){//外层循环控制层数
			/*List<Integer> width=calculateWidthList(btree);
			for(){
				
			}
			List<String> lnList=getBinaryTreeNodeAtLevel(btree, i);
			for(String str:lnList){//内层循环打印每一层
				System.out.print(str);
			}*/
			printBinaryTreeAtLevel(btree, i);
			System.out.println();
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	
}