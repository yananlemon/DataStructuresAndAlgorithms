package com.chapter4;

import com.chapter4.text.MyStack;

/**
 * <p>AddBigNumbers</p>
 * <p>编写一个程序，完成4.1节中的算法addingLargeNumbers()</p>
 * @author yanan  
 * @date 2018年3月29日
 */
public class AddBigNumbers {

	public static void main(String[] args) {
		int num1=2147483647;
		int num2=3784;
		System.out.println(addingLargeNumbers(num1, num2));
		
	}
	
	static String addingLargeNumbers(int num1,int num2){
		String str1 = String.valueOf(num1);
		String str2 = String.valueOf(num2);
		MyStack<String> stack1 = new MyStack<String>();
		MyStack<String> stack2 = new MyStack<String>();
		MyStack<String> resultStack = new MyStack<String>();
		readStr(stack1, str1);
		readStr(stack2, str2);
		int carry = 0;
		while(!stack1.isEmpty() || !stack2.isEmpty()){
			int a = 0;
			int b = 0;
			if(!stack1.isEmpty()){
				a = Integer.parseInt(stack1.pop().toString());
			}
			if(!stack2.isEmpty()){
				b = Integer.parseInt(stack2.pop().toString());
			}
			int rs = a+b+carry;
			if(rs >= 10){
				carry = 1;
				resultStack.push(String.valueOf(rs-10));
			}else{
				carry = 0;
				resultStack.push(String.valueOf(rs));
			}
		}
		StringBuffer rs = new StringBuffer();
		while(!resultStack.isEmpty()){
			rs.append(resultStack.pop());
		}
		return rs.toString();
	}
	
	static void readStr(MyStack<String> stack , String str){
		for (int i = 0; i < str.length(); i++) {
			stack.push(String.valueOf(str.charAt(i)));
		}
	}

}
