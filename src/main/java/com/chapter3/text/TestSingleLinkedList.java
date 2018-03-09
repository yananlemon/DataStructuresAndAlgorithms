package com.chapter3.text;

public class TestSingleLinkedList {
	
	public static void main(String[] args) {
		SingleLinkedList list=new SingleLinkedList();
		list.addToHead(1);
		list.addToHead(2);
		list.addToHead(3);
		
		printList(list);
		list.deleteFromHead();
		list.deleteFromHead();
		list.deleteFromHead();
		System.out.println(list.isEmpty());
		
		list.addToTail(1);
		list.addToTail(2);
		list.addToTail(3);
		printList(list);
	}

	private static void printList(SingleLinkedList list) {
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	
}
