package com.chapter4.text;

import java.util.Vector;


/**
 * <p>QueueS</p>  
 * <p>习题第九题：根据向量定义一个通用队列</p>  
 * @author yanan  
 * @date 2018年3月30日
 */
public class QueueV<E> {
	
	private Vector<E> vector;
	
	public QueueV(){
		vector = new Vector<E>(30);
	}
	
	/**
	 * 清空队列
	 */
	public void clear(){
		this.vector.clear();
	}
	
	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return this.vector.isEmpty();
	}
	
	/**
	 * 在队列尾部添加元素
	 * @param el
	 */
	public void enqueue(E el){
		this.vector.add(el);
	}
	
	/**
	 * 删除并返回队列头部第一个元素
	 * @return T
	 */
	public Object dequeue(){
		return this.vector.remove(0);
	}
	
	/**
	 * 返回队列头部第一个元素
	 * @return T
	 */
	public Object firstEl(){
		return this.vector.get(0);
	}

	public int size() {
		return this.vector.size();
	}
	
	public static void main(String[] args) {
		QueueV<String> queue = new QueueV<String>();
		queue.enqueue("a");
		queue.enqueue("b");
		queue.enqueue("c");
		System.out.println("top:"+queue.firstEl());
		queue.dequeue();
		while(!queue.isEmpty()){
			System.out.println(queue.dequeue());
		}
	}

}
