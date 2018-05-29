package algorithms.chapter1;

import java.util.Iterator;
import java.util.Scanner;

public class FifteenthQuestion {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();//4
		String line = in.next();// a,b,c,d,1,2
		char[] array = line.toCharArray();
		ResizingArrayQueue<Character> queue = new ResizingArrayQueue<Character>();
		for (int i = 0; i < array.length; i++) {
			queue.enqueue(array[i]);
		}// a,b,c,d,1,2
		int index = 0;
		while(array.length - k > index){
			index++;
			queue.dequeue();
		}
		Iterator<Character> it =queue.iterator(); 
		while(it.hasNext())
			System.out.println(it.next());
		
	}

}
