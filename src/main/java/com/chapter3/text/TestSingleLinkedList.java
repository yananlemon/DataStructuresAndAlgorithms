package com.chapter3.text;

public class TestSingleLinkedList {
	
	public static void main(String[] args) {
		SingleLinkedList list=new SingleLinkedList();
		/*list.addToHead(1);
		list.addToHead(2);
		list.addToHead(3);
		
		printList(list);
		list.deleteFromHead();
		list.deleteFromHead();
		list.deleteFromHead();
		System.out.println(list.isEmpty());*/
		
		for(int i=1;i<=100;i++){
			list.addToTail(i);
		}
		//printList(list);
		
		list.deleteNode(2);
		printList(list);
		
	}

	private static void printList(SingleLinkedList list) {
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	
}
