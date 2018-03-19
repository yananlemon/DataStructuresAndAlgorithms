package com.chapter3;

import com.chapter3.text.Node;
import com.chapter3.text.SingleLinkedList;

/**
 * <p>反转单链表</p>  
 * <p>参考https://stackoverflow.com/questions/14080758/reversing-a-linkedlist-recursively-in-c</p>  
 * @author yanan  
 * @date 2018年3月16日
 */
public class ReverseSingleLinkedList {

	public static void main(String[] args) {
		SingleLinkedList list=new SingleLinkedList();
		for(int i=1;i<=30;i++){
			list.addToTail(i);
		}
		
		//第1种方法
		//Node reversed=reverseLinkedListWithIterator(list.getHead());
		
		//第2种方法
		//Node reversed=reverseLinkedListWithRecursion(list.getHead());
		
		//第3种方法
		//SingleLinkedList reversed=reverseLinkedList3(list);
		
		//第5种方法
		SingleLinkedList reversed=reverseLinkedList5(list);
		printList(reversed);
		
		/*Node temp=reversed;
		while(temp!=null){
			System.out.println(temp.getInfo());
			temp=temp.getNext();
		}*/
		
	}
	
	public static void printList(SingleLinkedList list){
		StringBuffer buffer=new StringBuffer("[");
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1)
				buffer.append(list.get(i));
			else
				buffer.append(list.get(i)+",");
		}
		buffer.append("]");
		System.out.println(buffer);
	}
	
	/**
	 * 反转单链表使用迭代
	 * @param head
	 * @return
	 */
	static Node reverseLinkedListWithIterator(Node head){
		if(head==null || head.getNext()==null){
			return head;
		}
		//1->2->3->null
		Node currNode=head;//1
		Node prevNode=null;//null
		Node nextNode=null;//null
		while(currNode!=null){
			nextNode=currNode.getNext();//2
			currNode.setNext(prevNode);//1->null
			prevNode=currNode;//1
			currNode=nextNode;//2
		}
		return prevNode;
	}
	
	/**
	 * 反转单链表使用递归
	 * @param head
	 * @return
	 */
	static Node reverseLinkedListWithRecursion(Node head){
		if(head==null){
			return null;
		}
		if(head.getNext()==null){
			return head;
		}
		Node last=reverseLinkedListWithRecursion(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return last;
	}
	
	/**
	 * 第三种方法将单链表反转。
	 * 思路是：不断地从list尾部删除元素并将其添加到新链表尾部，直到list为空。
	 * @param list
	 * @return
	 */
	// 1->2->3->4->5
	// 5->1->2->3->4
	// 5->4->1->2->3
	// 5->4->3->1->2
	// 5->4->3->2->1
	static SingleLinkedList reverseLinkedList3(SingleLinkedList list){
		if(list==null || list.isEmpty())
			return null;
		SingleLinkedList result=new SingleLinkedList();
		while(!list.isEmpty())
			result.addToTail(list.deleteFromTail());
		return result;
		
	}
	
	
	//思路是每次都把最后一个节点放到最前面
	//1->2->3
	//3->1->2
	//3->2->1
	static Node reverseLinkedList4(Node head){
		return head;
	}
	
	/**
	 * 反转单链表。思路如下：
	 * 从list头部不断的删除元素并将元素添加到new_list的头部，直到list为空。
	 * @param list
	 * @return
	 */
	static SingleLinkedList reverseLinkedList5(SingleLinkedList list){
		if(list==null || list.isEmpty()){
			return null;
		}
		SingleLinkedList result=new SingleLinkedList();
		while(!list.isEmpty())
			result.addToHead(list.deleteFromHead());
		return result;
		
	}

}
