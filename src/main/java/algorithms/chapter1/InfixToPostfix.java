package algorithms.chapter1;

import java.util.Scanner;

public class InfixToPostfix {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String infix = in.nextLine();//1 + 2 * 3 + ( 4 * 5 + 6 ) * 7
		String[] infixArray = infix.split(" ");
		String postfix = infixToPostfix(infixArray);
		System.out.println(postfix);
		in.close();
	}

	/**
	 * 将{@code infixArray} 转换为后缀表达式
	 * @param infixArray 中缀表达式
	 * @return 后缀表达式
	 */
	public static String infixToPostfix(String[] infixArray) {
		Stack<String> stack = new Stack<String>();
		StringBuilder postfix = new StringBuilder();
		for (int i = 0; i < infixArray.length; i++) {
			String currValue = infixArray[i];
			if(checkWhetherDigit(currValue)){
				postfix.append(currValue);
			}else if(currValue.equals(")")){
				while(!stack.peek().equals("(")){
					postfix.append(stack.pop());
				}
				stack.pop();
			}else if(currValue.equals("(")){
				stack.push(currValue);
			}else{
				while(!stack.isEmpty()
						&& !stack.peek().equals("(") && getPriority(currValue) <= getPriority(stack.peek())){
					postfix.append(stack.pop());
				}
				stack.push(currValue);
			}
		}
		while(!stack.isEmpty())
			postfix.append(stack.pop());
		return postfix.toString();
	}
	
	private static int getPriority(String operator){
		if(operator.equals("+") || operator.equals("-"))
			return 1;
		if(operator.equals("*") || operator.equals("/"))
			return 2;
		if(operator.equals("("))
			return 5;
		return 0;
	}
	
	private static boolean checkWhetherDigit(String v){
		for (int i = 0; i < v.toCharArray().length; i++) {
			if(!Character.isDigit(v.toCharArray()[i]))
				return false;
		}
		return true;
	}

}
