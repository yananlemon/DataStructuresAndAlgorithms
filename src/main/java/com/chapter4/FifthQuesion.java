package com.chapter4;

import com.chapter4.text.MyQueue;

/**
 * <p>FifthQuesion</p>  
 * <p>习题第五题 </p>  
 * @author yanan  
 * @date 2018年3月29日
 */
public class FifthQuesion {

	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		/*queue.enqueue(10);
		queue.enqueue(3);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(5);*/
		for(int i = 0; i<10; i++){
			queue.enqueue((int)(Math.random()*100));
		}
		System.out.println("队列中原始顺序："+queue);
		sort(queue);
		System.out.println("排序后的顺序："+queue);
		
	}
	
	static void sort(MyQueue<Integer> queue){
		if(queue.isEmpty()){
			return;
		}
		
		// 临时的队列
		MyQueue<Integer> tempOfQueue1 = new MyQueue<Integer>();
		MyQueue<Integer> tempOfQueue2 = new MyQueue<Integer>();
		
		//外层循环控制原始队列
		while (!queue.isEmpty()) {
			
			//默认最小值是队列头部元素
			Integer mix = queue.dequeue();
			
			//遍历原始队列找到最小的元素
			while (!queue.isEmpty()) {
				Integer temp = queue.dequeue();
				if(temp < mix){
					tempOfQueue1.enqueue(mix);
					mix = temp;
				}else{
					tempOfQueue1.enqueue(temp);
				}
			}
			
			// 第二个队列始终存放最小的元素
			tempOfQueue2.enqueue(mix);
			while (!tempOfQueue1.isEmpty()) {
				queue.enqueue(tempOfQueue1.dequeue());
			}
		}
		while(!tempOfQueue2.isEmpty()){
			queue.enqueue(tempOfQueue2.dequeue());
		}
		
		
	}

}
