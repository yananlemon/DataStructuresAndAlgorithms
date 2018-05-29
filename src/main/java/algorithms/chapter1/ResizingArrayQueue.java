package algorithms.chapter1;

import java.util.Iterator;

/**
 * 基于动态数组实现的队列
 * @author andy
 * @param <T>
 */
public class ResizingArrayQueue<T> implements Iterable<T>{

	private T[] data;
	
	private int head;
	
	private int tail;
	
	private int size;
	
	private int defaultCapacity;
	
	public ResizingArrayQueue(int cap){
		this.defaultCapacity = cap;
		data = (T[]) new Object[cap];
	}
	
	public ResizingArrayQueue(){
		this.defaultCapacity = 10;
		data = (T[]) new Object[defaultCapacity];
	}
	
	public void resize(int len){
		T[] newData = (T[]) new Object[len];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
		head = 0;
		tail = size;
	}
	
	public void enqueue(T item){
		if(size  == data.length)
			resize(data.length*2);
		data[tail++] = item;
		size++;
	}
	
	public T dequeue(){
		T item = data[head];
		data[head++] = null;
		size--;
		return item;
	}
	
	public boolean isEmpty(){
		return size != 0;
	}
	

	public Iterator<T> iterator() {
		return new QueueIterator<T>();
	}
	
	private class QueueIterator<T> implements Iterator<T>{

		private int index = head; 
		
		public boolean hasNext() {
			return index >= tail ? false : true;
		}

		public T next() {
			T rs = (T) data[index];
			index++;
			return rs;
		}

		public void remove() {
			
		}
		
	}
	
	public static void main(String[] args) {
		ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<Integer>();
		for (int i = 0; i < 100; i++) {
			queue.enqueue(i);
		}
		Iterator<Integer> it =queue.iterator(); 
		while(it.hasNext())
			System.out.println(it.next());
		System.out.println(queue.dequeue());
		System.out.println("aaaaa");
	}

}
