package com.lemon.tree.binarytree;
import java.util.List;
import java.util.Stack;
import com.lemon.stack.*;


public class ExpressionTree {

	public static void main(String[] args) {
		String infixExpression="1+2*5-3";
		List<String> postfixList=PostfixConverter.buildPostfixExpression(infixExpression);
		BinaryTree btree=buildExpressionTree(postfixList);
		//BinaryTreeUtil.printBinaryTree(btree);
		//System.out.println();
		//
//		System.out.println("节点个数:");
//		System.out.println(BinaryTreeUtil.getLeafNumByLevel(btree, 3));
		//System.out.println("宽度:");
		//System.out.println(BinaryTreeUtil.calculateWidth(btree));
		//System.out.println("节点：");
		//System.out.println(BinaryTreeUtil.getBinaryTreeNodeAtLevel(btree,3));
		BinaryTreeUtil.printPrettyBinaryTree(btree);
	}
	
	/**
	 * 将后缀表达式转换成一棵表达式树.算法如下:
	 * 一次一个符号地读入后缀表达式。
	 * 如果符号是操作数，那么我们建立一个单节点树并将一个指向它的指针推入栈中；
	 * 如果符号是操作符，那么我们就从栈中弹出指向两棵树T1和T2的那两个指针（T1的先弹出，即T1是被操作数），
	 *    并形成一棵新的树，该树的根就是操作符，它的左右儿子分别指向T2和T1，然后将这棵
	 *    新树的指针压入栈中。
	 * 重复上述步骤，直到读到输入末尾。
	 * @param postfixList
	 */
	public static BinaryTree buildExpressionTree(List<String> postfixList){
		Stack<BinaryTree> stack=new Stack<BinaryTree>();
		for(String token:postfixList){
			if(token.equals("+")||token.equals("-")||
					token.equals("*")||token.equals("/")){
				BinaryTree right=stack.pop();
				BinaryTree left=stack.pop();
				BinaryTree childTree=new BinaryTree(left, right, token);
				stack.push(childTree);
			}else{
				BinaryTree leafTree=new BinaryTree(token);
				stack.push(leafTree);
			}
		}
		return stack.pop();
	}
	
	public static void print(BinaryTree tree){
		if(tree.left==null && tree.right==null){
			System.out.print(" "+tree.value);
		}else{
			System.out.print(" "+tree.value);
			print(tree.left);
			print(tree.right);
		}
		System.out.println();
	}

}

