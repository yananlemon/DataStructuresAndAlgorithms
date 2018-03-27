package com.chapter4.text;

/**
 * <p>TestMyStack</p>  
 * <p>测试MyStack</p>  
 * @author yanan  
 * @date 2018年3月27日
 */
public class TestMyStack {

	public static void main(String[] args) {
		MyStack<Integer> stack=new MyStack<Integer>();
		for(int i=1;i<=100;i++){
			stack.push(i);
		}
		/*stack.push("n");
		stack.push("a");
		stack.push("n");
		stack.push("a");
		stack.push("y");*/
		while(!stack.isEmpty()){
			System.out.printf("%s\n",stack.pop());
		}
	}

}
