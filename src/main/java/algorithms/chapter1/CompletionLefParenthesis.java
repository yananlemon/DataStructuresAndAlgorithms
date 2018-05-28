package algorithms.chapter1;

import java.util.Scanner;

public class CompletionLefParenthesis {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String next = in.nextLine();
		//evaluateInfixExpression(next.split(" "));
		System.out.println(completion(next.split(" ")));
		in.close();
	}
	
	/**
	 * 补全中缀表达式{@code infixExpression} 中缺少的左括号
	 * @param infixExpression
	 * @return
	 */
	public static String completion(String[] infixExpression){
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < infixExpression.length; i++) {
			String v = infixExpression[i];
			// 不是右括号就入栈
			if(!v.equals(")")){
				stack.push(v);
			}else{
				/**
				 *  如果是右括号,则弹出三个元素,并按照如下格式入栈:
				 *  (操作数1 运算符 操作数2)
				 */
				StringBuilder expressionStr = new StringBuilder();
				String operand2 = stack.pop();
				String operator = stack.pop();
				String operand1 = stack.pop();
				expressionStr.append(" ( ").append(operand1).append(" ")
				.append(operator).append(" ").append(operand2).append(" ) ");
				stack.push(expressionStr.toString());
			}
		}
		return stack.pop();
	}
	
	/**
	 *
	 * 计算中缀表达式的值
	 * 1.将操作数压入操作数栈
	 * 2.将运算符压入运算符栈
	 * 3.忽略左括号
	 * 4.在遇到右括号时，则弹出一个运算符，弹出所需数量的操作数，并将运算符和操作数的运算结果压入操作数栈。
	 * 在处理完最后一个右括号之后，操作数栈上只会有一个值，它就是表达式的值。
	 * @param infixExpression ( ( 1 + 2 ) * ( ( 3 - 4 ) * (5 -6 ) ) )
	 * @return
	 */
	public static double evaluateInfixExpression(String[] infixExpression){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Double> operandStack = new Stack<Double>();
		for (int i = 0; i < infixExpression.length; i++) {
			String currVal = infixExpression[i];
			if(currVal.equals("("))
				continue;
			if(currVal.equals(")")){
				String opertor = operatorStack.pop();
				double num1,num2 = 0;
				num2 = operandStack.pop();
				num1 = operandStack.pop();
				if(opertor.equals("+")){
					operandStack.push(num1 + num2);
				}else if(opertor.equals("-")){
					operandStack.push(num1 - num2);
				}else if(opertor.equals("*")){
					operandStack.push(num1 * num2);
				}else if(opertor.equals("/")){
					operandStack.push(num1 / num2);
				}
			}else if(checkWhetherOperator(currVal)){
				operatorStack.push(currVal);
			}else if(checkWhetherDigit(currVal)){
				operandStack.push(Double.valueOf(currVal));
			}
			
		}
		return operandStack.pop();
	}
	
	private static boolean checkWhetherDigit(String v){
		for (int i = 0; i < v.toCharArray().length; i++) {
			if(!Character.isDigit(v.toCharArray()[i]))
				return false;
		}
		return true;
	}
	
	private static boolean checkWhetherOperator(String v){
		if(v.equals("+") || v.equals("-") || v.equals("*") || v.equals("/"))
			return true;
		return false;
	}

}
