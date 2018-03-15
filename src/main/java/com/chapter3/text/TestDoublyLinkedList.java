package com.chapter3.text;

/**
 * <p>TestDoublyLinkedList</p>  
 * <p>双向链表测试类 </p>  
 * @author yanan  
 * @date 2018年3月15日
 */
public class TestDoublyLinkedList {

	public static void main(String[] args) {
		DoublyLinkedList<String> list=new DoublyLinkedList<String>();
		/*list.addToHead("a");
		list.addToHead("b");
		list.addToHead("c");
		list.addToHead("d");*/
		
		list.addToTail("a");
		list.addToTail("b");
		list.addToTail("c");
		list.addToTail("d");
		
		/*list.deleteFromHead();
		list.deleteFromHead();
		printList(list);//list is:c,d,and c.next=d,c.prev=null,d.prev=c,d.next=null?*/
		
		/*list.deleteFromTail();
		list.deleteFromTail();
		printList(list);//list is : a, b? and a.next=b,a.prev=null,b.prev=a,b.next=null ?*/
		
		list.deleteNode(1);
		list.deleteNode(1);
		printList(list);// list is:a,d? and a.next=d,a.prev=null,d.prev=a,d.next=null ?
	}
	
	static void printList(DoublyLinkedList<String> list){
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+",");
		}
	}

}
