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
		
		for(int i=1;i<=10;i++){
			list.addToTail(i);
		}
		printList(list);//list is:1,2,3,4,5,6,7,8,9,10
		
		/*list.deleteNode(2);// list is:1,2,4,5,6,7,8,9,10
		System.out.println("list is:1,2,4,5,6,7,8,9,10？");
		printList(list);
		list.deleteNode(7);// list is:1,2,4,5,6,7,8,10
		System.out.println("list is:1,2,4,5,6,7,8,10？");
		printList(list);*/
		
	}

	private static void printList(SingleLinkedList list) {
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	
}
