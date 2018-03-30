package com.chapter4;

import java.util.Scanner;

import com.chapter4.text.MyStack;

/**
 * <p>PalindromeCheck</p>
 * <p>判断输入的字符串是否是回文</p>
 * @author yanan  
 * @date 2018年3月29日
 */
public class PalindromeCheck {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\r\n");
		System.out.println("\n判断是否是回文");
		String condition = "";
		do{
			System.out.println("请输入一个字符串");
			String str = in.next();
			if(checkWhetherPalindrome(str)){
				System.out.printf("%s是回文\n",str);
			}else{
				System.out.printf("%s不是回文\n",str);
			}
			System.out.println("是否继续？(y/n)");
			condition = in.next();
		}while(condition.equalsIgnoreCase("y"));
		in.close();
		System.out.println("Bye...");
	}
	
	static boolean checkWhetherPalindrome(String s){
		MyStack<String> s1   = new MyStack<String>();
		MyStack<String> s2   = new MyStack<String>();
		MyStack<String> temp = new MyStack<String>();
		for (int i = 0; i < s.length(); i++) {
			s1.push(String.valueOf(s.charAt(i)));
			temp.push(String.valueOf(s.charAt(i)));
		}
		while(!temp.isEmpty()){
			s2.push(temp.pop());
		}
		
		while(!s1.isEmpty()){
			if(!s1.pop().toString().equalsIgnoreCase(s2.pop().toString())){
				return false;
			}
		}
		return true;
		
	}

}
