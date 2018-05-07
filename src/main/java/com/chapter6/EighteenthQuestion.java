package com.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>习题第十八题</p>
 * <p>参考刘汝佳《算法竞赛入门经典》 P198</p>
 * @author yanan
 * @date 2018年5月7日
 */

public class EighteenthQuestion {

	public static void main(String[] args) {
		String infix="5*(8/(6-5))";
		List<Token> tokens=getTokens(infix);
		List<Token> postFix=convertPostFix(tokens);
		ExpressionTree root = buildExpressionTree(postFix);
		
		inOrder(root);
	}
	
	/**
	 * 二叉树表达式->中缀表达式：对二叉树表达式进行中序遍历，
	 * 若结点操作符的优先级高于其左或右子树，在打印相应的子树之前先打印开括号，在打印相应的子树最后在打印一个闭括号。
	 * @param root
	 */
	public static void inOrder(ExpressionTree root){
		if(root != null){
			if(root.left != null){
				if(checkWhetherOperator(root.left.v) && getPriority(root.v) > getPriority(root.left.v)){
					System.out.print("(");
					inOrder(root.left);
					System.out.print(")");
				}else{
					inOrder(root.left);
				}
			}
			System.out.print(root.v);
			if(root.right != null){
				if(checkWhetherOperator(root.right.v) && getPriority(root.v) > getPriority(root.right.v)){
					System.out.print("(");
					inOrder(root.right);
					System.out.print(")");
				}else{
					inOrder(root.right);
				}
			}
		}
	}
	
	public static ExpressionTree buildExpressionTree(List<Token> postFix){
		Stack<ExpressionTree> stack = new Stack<ExpressionTree>();
		for (int i = 0; i < postFix.size(); i++) {
			Token token = postFix.get(i);
			if(token.getTokenType() == TokenType.OPERATOR){
				ExpressionTree right  = stack.pop();
				ExpressionTree left   = stack.pop();
				ExpressionTree parent = new ExpressionTree(token.getValue(), left, right);
				stack.push(parent);
			}else if(token.getTokenType() == TokenType.NUMBER){
				ExpressionTree tree = new ExpressionTree(token.getValue(), null, null);
				stack.push(tree);
			}
		}
		return stack.pop();
	}
	
	public static List<Token> convertPostFix(List<Token> expression){
		List<Token> postFix = new ArrayList<Token>();
		Stack<Token> operatorStatck = new Stack<Token>();
		for(int i = 0;i < expression.size();i++){
			Token token=expression.get(i);
			if(token.getTokenType()==TokenType.NUMBER||token.getTokenType()==TokenType.VAR){
				postFix.add(token);
			}else if(token.getTokenType()==TokenType.OPERATOR||token.getTokenType()==TokenType.LEFT||token.getTokenType()==TokenType.RIGHT){
				if(token.getValue().equals(")")){
					while(!operatorStatck.isEmpty()&&!operatorStatck.peek().getValue().equals("(")){
						postFix.add(operatorStatck.pop());
					}
					operatorStatck.pop();
				}else if(token.getValue().equals("(")){
					operatorStatck.push(token);
				}else{
					while(!operatorStatck.isEmpty()&&getPriority(token.getValue())<=getPriority(operatorStatck.peek().getValue())&&
							(!token.getValue().equals(")")&&!operatorStatck.peek().getValue().equals("("))){
						postFix.add(operatorStatck.pop());
					}
					operatorStatck.push(token);
				}
			}
		}
		while(!operatorStatck.isEmpty()){
			postFix.add(operatorStatck.pop());
		}
		return postFix;
	}
	
	/**
	 * 获取操作符的优先级,
	 * @param operator
	 * @return number 数字越大优先级越高
	 */
	static int getPriority(String operator){
		if(operator.equals("+")||operator.equals("-")){
			return 2;
		}else if(operator.equals("*")||operator.equals("/")){
			return 3;
		}else if(operator.equals("=")){
			return 1;
		}else if(operator.equals("(")){
			return 10;
		}
		return 0;
	}
	public static boolean checkOperator(char c){
		if(String.valueOf(c).equals("+")||String.valueOf(c).equals("-")||
				String.valueOf(c).equals("*")||String.valueOf(c).equals("/")||String.valueOf(c).equals("=")){
			return true;
		}
		return false;
			
	}
	public static boolean checkWhetherOperator(String c){
		if(String.valueOf(c).equals("+")||String.valueOf(c).equals("-")||
				String.valueOf(c).equals("*")||String.valueOf(c).equals("/")||String.valueOf(c).equals("=")){
			return true;
		}
		return false;
			
	}
	public static List<Token> getTokens(String input){
		List<Token> tokens=new ArrayList<Token>();
		char[] chars=input.toCharArray();
		for(int i=0;i<chars.length;i++){
			if(Character.isLetter(chars[i])){
				StringBuffer sb=new StringBuffer(chars[i]);
				for(int k=i;k<chars.length&&!checkOperator(chars[k]);k++){
					if(Character.isLetter(chars[k])){
						sb.append(chars[k]);
						i=k;
					}
				}
				tokens.add(new Token(sb.toString(),TokenType.VAR));
			}else if(Character.isDigit(chars[i])){
				StringBuffer sb=new StringBuffer(chars[i]);
				for(int k=i;k<chars.length&&!checkOperator(chars[k]);k++){
					if(Character.isDigit(chars[k])||String.valueOf(chars[k]).equals(".")){
						sb.append(chars[k]);
						i=k;
					}
				}
				tokens.add(new Token(sb.toString(),TokenType.NUMBER));
			}else if(checkOperator(chars[i])){
				tokens.add(new Token(String.valueOf(chars[i]),TokenType.OPERATOR));
			}else if(String.valueOf(chars[i]).equals(";")){
				tokens.add(new Token(String.valueOf(chars[i]),TokenType.END));
			}else if(String.valueOf(chars[i]).equals("(")){
				tokens.add(new Token(String.valueOf(chars[i]),TokenType.LEFT));
			}else if(String.valueOf(chars[i]).equals(")")){
				tokens.add(new Token(String.valueOf(chars[i]),TokenType.RIGHT));
			}
			
			
		}
		return tokens;
	}

}

class Token {
	private String value;
	private TokenType tokenType;
	
	public Token(String value, TokenType tokenType) {
		super();
		this.value = value;
		this.tokenType = tokenType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public TokenType getTokenType() {
		return tokenType;
	}
	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	@Override
	public String toString() {
		return "Token [value=" + value + ", tokenType=" + tokenType + "]\n";
	}
	
	
}

class ExpressionTree{
	String v;
	ExpressionTree left,right;
	public ExpressionTree(String v, ExpressionTree left, ExpressionTree right) {
		this.v = v;
		this.left = left;
		this.right = right;
	}
	
}

enum TokenType {
	VAR,//变量
	NUMBER,//数字
	END,//结束标识符
	LEFT,//左括号
	RIGHT,//右括号
	OPERATOR,//操作符
	KEYWORD,//关键字
	COMMA,//逗号
	FUNCTION;//函数
	
}
