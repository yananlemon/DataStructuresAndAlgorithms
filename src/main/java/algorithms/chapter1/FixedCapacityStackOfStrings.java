package algorithms.chapter1;

import java.util.Arrays;

/**
 * 
 * 保存字符串的固定容量的栈
 * @author yanan
 */
public class FixedCapacityStackOfStrings {

	private String[] array;
	
	private int size;
	
	private int capacity;
	
	/**
	 * 
	 * 创建一个容量为 {@code cap} 的空栈
	 * @param cap 栈的容量
	 */
	public FixedCapacityStackOfStrings(int cap) {
		this.capacity = cap;
		array = new String[cap];
	}
	
	/**
	 * 
	 * 添加一个字符串{@code item}到栈顶
	 * @param item 待添加的字符串
	 */
	public void push(String item) {
		if(size()==array.length-1){
			int newLen=(array.length-1)<<1;
			array=Arrays.copyOf(array, newLen);
		}
		array[size++] = item;
	}
	
	/**
	 * 删除最近添加的字符串并返回
	 * @return
	 */
	public String pop() {
		return array[--size];
	}
	
	public boolean isEmpty() {
		return size() == 0 ? true : false;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isFull() {
		return size() >= capacity ? true : false;
	}
	
	public static void main(String[] args) {
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
		//String[] strs = {"to","be","or","not","to","-","be","-","-","that","-","-","-","is"};
		String[] strs = {"it","was","-","the","best","-","of","times","-","-","-","it","was","-","the","-","-"};
		for (int i = 0; i < strs.length; i++) {
			if(strs[i].equals("-"))
				stack.pop();
			else
				stack.push(strs[i]);
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
 		System.out.println(stack.isEmpty());
	}
	
	
}
