package com.chapter6.practice;


import java.util.Scanner;

import com.chapter6.bst.BinaryNode;

/**
 * <p>6.15编程练习第1题</p>
 * <p>编写一个程序，接受以前缀表示法表示的代数表达式，构造一棵表达式树，然后
 * 遍历该树并计算表达式。计算应该在表达式全部输入完毕后才开始。</p>
 * https://wenku.baidu.com/view/9bb37b6548d7c1c708a145de.html
 * @author yanan
 * @date 2018年5月10日
 */
public class CalcutePreOrderMathExpression {

	public static void main(String[] args) {
		System.out.println("请输入一个前缀表达式并以#号结束，例如+ - 2 * 3 4 5");
		Scanner in = new Scanner(System.in);
		while(true){
			String next = in.nextLine();
			//String preOrderStr = "+ - 2 * 3 4 5"; // -5
			//String preOrderStr = "- 2 * 3 4"; 
			//String preOrderStr = "* - 2 3 + 4 5"; //  -9
			//String preOrderStr = "- 2 + * 3 4 5";// -15
			if(next.endsWith("#")){
				BinaryNode<String> rs = buildExpressionTreeWithPreOrderStr(next.substring(0, next.lastIndexOf("#")).split(" "));
				System.out.println(calculateExpressTree(rs));
				System.out.println("是否继续？(Y/N)");
				String c = in.next();
				if(c.equalsIgnoreCase("n")){
					break;
				}
			}
			
		}
		in.close();
	}
	
	static double calculateExpressTree(BinaryNode<String> tree){
		// 如果是操作符则将操作符应用于左子树和右子树
		if(!isNumeric(tree.element)){
			String operator = tree.element;
			if(operator.equals("+")){
				return calculateExpressTree(tree.left) + calculateExpressTree(tree.right);
			}else if(operator.equals("-")){
				return calculateExpressTree(tree.left) - calculateExpressTree(tree.right);
			}else if(operator.equals("*")){
				return calculateExpressTree(tree.left) * calculateExpressTree(tree.right);
			}else if(operator.equals("/")){
				return calculateExpressTree(tree.left) / calculateExpressTree(tree.right);
			}else{
				throw new Error("未知的符号："+operator);
			}
		}else{
			return Double.valueOf(tree.element);
		}
	}
	
	/**
	 * 算法如下：第一个字符作为树根；紧接着选择其后第一次满足运算符个数比操作数个数少1的部分表达式作为该树根的左子树；
	 * 剩下的部分表达式作为右子树。再分别对左右两部分表达式递归使用上述算法。
	 * @param preOrderStr
	 * @return
	 */
	static BinaryNode<String> buildExpressionTreeWithPreOrderStr(String[] array) {
		BinaryNode<String> resultTree = new BinaryNode<String>(array[0]);
		if(array.length == 1) {
			return resultTree;
		}
		String[] newArray = new String[array.length-1];
		String[] leftArray,rightArray;
		for(int i = 1; i < array.length; i++) {
			newArray[i-1] = array[i];
		}
		int index = getIndex(newArray);
		leftArray = new String[index+1];
		for(int i = 0; i <= index; i++) {
			leftArray[i] = newArray[i];
		}
		rightArray = new String[newArray.length - index - 1];
		int k = 0;
		for(int i = index+1; i < newArray.length; i++) {
			rightArray[k] = newArray[i];
			k++;
		}
		resultTree.left   = buildExpressionTreeWithPreOrderStr(leftArray);
		resultTree.right  = buildExpressionTreeWithPreOrderStr(rightArray);
		return resultTree;
	}
	
	/**
	 * - 2 * 3 4 5
	 * @param array
	 * @return
	 */
	static int getIndex(String[] array) {
		int rs = 0;
		int count1 = 0;//运算符个数
		int count2 = 0;//操作数个数
		for (int i = 0; i < array.length; i++) {
			if(i != 0 && count1 != 0 && count2 != 0 && count1 + 1 == count2) {
				return i-1;
			}
			if(isNumeric(array[i]) && i == 0) {
				count2++;
				break;
			}else if(isNumeric(array[i]) && i != 0) {
				count2++;
			}
			else {
				count1++;
			}
				
		}
		return rs;
	}
	
	public static boolean isNumeric(String str){
	   for (int i = str.length();--i>=0;){  
	       if (!Character.isDigit(str.charAt(i))){
	           return false;
	       }
	   }
	   return true;
	}

}
