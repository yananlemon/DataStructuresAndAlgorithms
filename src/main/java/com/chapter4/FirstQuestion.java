package com.chapter4;

import com.chapter4.text.MyQueue;
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
		//reverse3(stack).print();;
		reverse2(stack).print();
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
	/**
	 * 将栈中元素颠倒过来：使用一个额外的队列
	 * @param stack
	 * @return
	 */
	static MyStack<String> reverse2(MyStack<String> stack){
		if(stack.isEmpty()){
			return null;
		}
		MyQueue<String> queue = new MyQueue<String>();
		while(!stack.isEmpty()){
			queue.enqueue(stack.pop().toString());
		}
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			stack.push(queue.dequeue());
		}
		return stack;
	}
	
	
	/**
	 * 将栈中元素颠倒过来：使用一个额外的栈和几个额外的非数组变量
	 * @param stack
	 * @return
	 */
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
