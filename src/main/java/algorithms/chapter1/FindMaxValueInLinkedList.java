package algorithms.chapter1;

import java.util.Random;

import com.chapter3.text.Node;
import com.chapter3.text.SingleLinkedList;

public class FindMaxValueInLinkedList {

	public static void main(String[] args) {
		Random r=new Random();
		SingleLinkedList list=new SingleLinkedList();
		for(int i=1;i<=10;i++){
			list.addToTail(r.nextInt(10)+1);
		}
		Node temp=list.getHead();
		while(temp!=null){
			System.out.println(temp.getInfo());
			temp=temp.getNext();
		}
		System.out.println(getMaxWithItertor(list.getHead()));
		
		System.out.println(getMaxWithRecursion(list.getHead()));
		
	}
	
	public static int getMaxWithItertor(Node first) {
		Node temp = first;
		int max = 0;
		while(temp != null) {
			if(max < temp.getInfo()) {
				max = temp.getInfo();
			}
			temp = temp.getNext();
		}
		return max;
	}
	
	public static int getMaxWithRecursion(Node first) {
		if(first == null) {
			return 0;
		}
		Node temp = first;
		int max = first.getInfo();
		int nextVal = getMaxWithRecursion(temp.getNext());
		return max > nextVal ? max : nextVal;
	}

}
