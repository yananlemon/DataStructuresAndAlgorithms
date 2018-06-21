package com.chapter4.text;

import com.chapter3.text.DoublyLinkedList;

/**
 * <p>StackL</p>  
 * <p>基于双向链表实现的栈 </p>  
 * @author yanan  
 * @date 2018年3月30日
 */
public class StackL<T> extends AbstractStack<T> {

	private DoublyLinkedList<T> dll = new DoublyLinkedList<T>();
	
	@Override
	public void clear() {
		this.dll.clear();
	}

	@Override
	public boolean isEmpty() {
		return dll.isEmpty();
	}

	@Override
	public int size() {
		return this.dll.size();
	}

	@Override
	public void push(T value) {
		dll.addToHead(value);
	}

	@Override
	public T pop() {
		return dll.deleteFromHead();
	}

	@Override
	public T topEl() {
		return dll.get(0);
	}

	@Override
	public String toString() {
		if(isEmpty()){
			return "[]";
		}
		StringBuffer sb = new StringBuffer("[");
		for (int i = 0; i < dll.size(); i++) {
			sb.append(dll.get(i)).append(",");
		}
		sb.deleteCharAt(sb.length()-1).append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		AbstractStack<Integer> stack = new StackL<Integer>();
		for (int i = 1; i <= 100; i++) {
			stack.push(i);
		}
		stack.clear();
		System.out.println(stack.isEmpty());
		System.out.println(stack);
	}

}
