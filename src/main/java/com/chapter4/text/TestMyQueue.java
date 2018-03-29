package com.chapter4.text;

public class TestMyQueue {

	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}
		
		//while(!queue.isEmpty()){
		while(queue.size()>0){
			System.out.println(queue.dequeue());
		}
		System.out.println(queue.size());
	}

}
