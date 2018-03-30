package com.chapter4;

import com.chapter4.text.MyStack;

/**
 * <p>AddMultipleBigNumbers</p>
 * <p>任意多个大数相加</p>
 * @author yanan  
 * @date 2018年3月29日
 */
public class AddMultipleBigNumbers {

	public static void main(String[] args) {
		String num1="2147483647";
		String num2="3784";
		String num3="10";
		System.out.println(addingMultBigNumbers(num1, num2,num3));
		
	}
	static String addingMultBigNumbers(String...numbers){
		MyStack<String> stack = new MyStack<String>();
		for(String num : numbers){
			if(stack.isEmpty()){
				stack.push(addingLargeNumbers(num, "0"));
			}else{
				stack.push(addingLargeNumbers(stack.pop().toString(), num));
			}
		}
		return stack.pop().toString();
	}
	
	static String addingLargeNumbers(String str1,String str2){
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
