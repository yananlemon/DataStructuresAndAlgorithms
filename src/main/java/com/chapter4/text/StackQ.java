package com.chapter4.text;

/**
 * <p>StackQ</p>  
 * <p>习题第七题:根据队列定义一个栈 </p>  
 * @author yanan  
 * @date 2018年3月29日
 */
public class StackQ<T> {

	private MyQueue<T> queue1;
	private MyQueue<T> queue2;
	private int size;
	
	public StackQ(){
		this.queue1=new MyQueue<T>();
		this.queue2=new MyQueue<T>();
	}
	
	/**
	 * 清空栈
	 */
	public void clear(){
		this.queue1.clear();
		this.queue2.clear();
		this.size = 0;
	}
	
	/**
	 * 判断栈是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return size==0?true:false;
	}
	
	/**
	 * 添加元素到栈顶
	 * @param el
	 */
	public void push(T el){
		queue1.enqueue(el);
		size++;
	}
	
	/**
	 * 弹出栈顶部元素
	 * @return T
	 */
	public T pop(){
		while(queue1.size()>1){
			queue2.enqueue(queue1.dequeue());
		}
		T rs = queue1.dequeue();
		while(!queue2.isEmpty()){
			queue1.enqueue(queue2.dequeue());
		}
		size--;
		return rs;
	}
	
	/**
	 * 获取栈顶元素，但不删除。
	 * @return T
	 */
	public T topEl(){
		while(queue1.size()>1){
			queue2.enqueue(queue1.dequeue());
		}
		T rs = queue1.dequeue();
		while(!queue2.isEmpty()){
			queue1.enqueue(queue2.dequeue());
		}
		queue1.enqueue(rs);
		return rs;
	}

	public int size() {
		return size;
	}
	
	
	public static void main(String[] args) {
		StackQ<String> stack = new StackQ<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println("the top of stack is:"+stack.topEl());
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}

}
