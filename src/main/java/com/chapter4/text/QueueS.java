package com.chapter4.text;


/**
 * <p>QueueS</p>  
 * <p>习题第八题：根据栈定义一个队列</p>  
 * @author yanan  
 * @date 2018年3月30日
 */
public class QueueS<E> {
	
	private MyStack<E> stack1;
	private MyStack<E> stack2;
	private int size;
	
	public QueueS(){
		stack1 = new MyStack<E>();
		stack2 = new MyStack<E>();
	}
	
	/**
	 * 清空队列
	 */
	public void clear(){
		
	}
	
	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return size()==0?true:false;
	}
	
	/**
	 * 在队列尾部添加元素
	 * @param el
	 */
	public void enqueue(E el){
		stack1.push(el);
		size++;
	}
	
	/**
	 * 删除并返回队列头部第一个元素
	 * @return T
	 */
	public Object dequeue(){
		Object el = null;
		if(!stack2.isEmpty()){
			el = stack2.pop();
		}else{
			while(stack1.size()>1){
				stack2.push(stack1.pop());
			}
			el = stack1.pop();
		}
		if(el != null){
			size--;
		}
		return el;
	}
	
	/**
	 * 返回队列头部第一个元素
	 * @return T
	 */
	public Object firstEl(){
		Object el = null;
		if(!stack2.isEmpty()){
			el = stack2.topEl();
		}else{
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
			el = stack2.topEl();
		}
		return el;
	}

	public int size() {
		return this.size;
	}
	
	public static void main(String[] args) {
		QueueS<String> queue = new QueueS<String>();
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
