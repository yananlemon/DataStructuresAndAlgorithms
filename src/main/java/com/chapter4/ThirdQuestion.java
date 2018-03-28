package com.chapter4;

import com.chapter4.text.MyStack;

/**
 * <p>习题第3题</p>  
 * @author yanan  
 * @date 2018年3月28日
 */
public class ThirdQuestion {

	public static void main(String[] args) {
		MyStack<String> stack=new MyStack<String>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		stack.push("5");
		System.out.println("栈stack中元素：");
		stack.print();
		//MyStack<String> rsStack=convertUsingStack(stack);
		MyStack<String> rsStack=convertUsingVariable(stack);
		System.out.println("\n栈rsStack中元素：");
		rsStack.print();
	}
	
	/**
	 * 使用一个额外的栈
	 * @param stack
	 * @return
	 */
	static MyStack<String> convertUsingStack(MyStack<String> stack){
		MyStack<String> tempStack=new MyStack<String>();
		MyStack<String> rsStack=new MyStack<String>();
		while(!stack.isEmpty()){
			tempStack.push(stack.pop());
		}
		while(!tempStack.isEmpty()){
			rsStack.push(tempStack.pop());
		}
		return rsStack;
		
	}
	
	static MyStack<String> convertUsingVariable(MyStack<String> stack){
		MyStack<String> rsStack = new MyStack<String>();
		int size = stack.size();
		for (int i = 0; i < size-1; i++) {
			String top=stack.pop().toString();
			for (int j = 0; j < size-i-1; j++) {
				rsStack.push(stack.pop());
			}
			stack.push(top);
			for (int j = 0; j < size-i-1; j++) {
				stack.push(rsStack.pop());
			}
		}
		while(!stack.isEmpty()){
			rsStack.push(stack.pop());
		}
		return rsStack;
		
	}
	
	
	
	
	

}
