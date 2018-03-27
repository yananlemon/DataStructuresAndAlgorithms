package com.chapter4;

import com.chapter4.text.MyStack;

/**
 * <p>习题第1题</p>  
 * @author yanan  
 * @date 2018年3月28日
 */
public class FirstQuestion {

	public static void main(String[] args) {
		MyStack<String> stack=new MyStack<String>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		stack.push("5");
		System.out.println("原栈中元素：");
		stack.print();
		System.out.println("\n反转后：");
		//reverse(stack).print();;
		reverse3(stack).print();;
	}
	
	/**
	 * 使用两个额外的栈
	 * @param stack
	 * @return
	 */
	static MyStack<String> reverse(MyStack<String> stack){
		if(stack.isEmpty()){
			return null;
		}
		MyStack<String> s1=new MyStack<String>();
		while(!stack.isEmpty()){
			s1.push(stack.pop());
		}
		MyStack<String> s2=new MyStack<String>();
		while(!s1.isEmpty()){
			s2.push(s1.pop());
		}
		
		while(!s2.isEmpty()){
			stack.push(s2.pop());
		}
		return stack;
	}
	
	//stack:top:5,4,3,2,1
	static MyStack<String> reverse3(MyStack<String> stack){
		if(stack.isEmpty()){
			return null;
		}
		MyStack<String> s1=new MyStack<String>();
		int len=stack.size();
		for(int i=0;i<len-1;i++){
			String top=stack.pop().toString();
			while(stack.size()>i){
				s1.push(stack.pop());
			}
			stack.push(top);
			while(!s1.isEmpty()){
				stack.push(s1.pop());
			}
		}
		return stack;
	}
	

}
