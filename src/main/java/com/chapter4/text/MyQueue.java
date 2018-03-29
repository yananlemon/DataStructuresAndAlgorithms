package com.chapter4.text;

import com.chapter3.text.DoublyLinkedList;

/**
 * <p>MyStack</p>  
 * <p>基于双链表实现的队列</p>  
 * @author yanan  
 * @date 2018年3月29日
 */
public class MyQueue<T> {

	private DoublyLinkedList<T> dll;
	
	public MyQueue(){
		this.dll = new DoublyLinkedList<T>();
	}
	
	/**
	 * 清空队列
	 */
	public void clear(){
		this.dll.clear();
	}
	
	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return this.dll.isEmpty();
	}
	
	/**
	 * 在队列尾部添加元素
	 * @param el
	 */
	public void enqueue(T el){
		dll.addToTail(el);
	}
	
	/**
	 * 删除并返回队列头部第一个元素
	 * @return T
	 */
	public T dequeue(){
		return dll.deleteFromHead();
	}
	
	/**
	 * 返回队列头部第一个元素
	 * @return T
	 */
	public T firstEl(){
		return dll.get(0);
	}

	public int size() {
		return this.dll.size();
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("[");
		for (int i = 0; i < dll.size(); i++) {
			sb.append(dll.get(i)).append(",");
		}
		sb.deleteCharAt(sb.length()-1).append("]");
		return sb.toString();
	}
	
	
}
