package com.chapter4;

import com.chapter4.text.MyStack;

/**
 * <p>习题第2题</p>  
 * @author yanan  
 * @date 2018年3月28日
 */
public class SecondQuestion {

	public static void main(String[] args) {
		MyStack<Integer> stack=new MyStack<Integer>();
		/*for(int i = 0; i<5; i++){
			stack.push((int)(Math.random()*100));
		}*/
		stack.push(10);
		stack.push(9);
		stack.push(8);
		stack.push(7);
		System.out.println("栈stack中元素：");
		stack.print();
		MyStack<Integer> newStack = sort(stack);
		System.out.println("\n排序后：");
		newStack.print();
	}
	
	/**
	 * 使用一个额外的栈
	 * @param stack
	 * @return
	 */
	static MyStack<Integer> sort(MyStack<Integer> stack){
		MyStack<Integer> tempStack=new MyStack<Integer>();
		int size = stack.size();
		for (int i = 0; i < size; i++) {
			Integer top = (Integer) stack.pop();
			Integer mix = top;
			for(int k = 0; k < size-i-1; k++){
				Integer temp = (Integer) stack.pop();
				if(mix > temp){
					tempStack.push(mix);
					mix = temp;
				}else{
					tempStack.push(temp);
				}
			}
			// 找到最小的元素并入栈
			stack.push(mix);
			while(!tempStack.isEmpty()){
				stack.push(tempStack.pop());
			}
			
		}
		while(!stack.isEmpty()){
			tempStack.push(stack.pop());
		}
		return tempStack;
		
	}

}
