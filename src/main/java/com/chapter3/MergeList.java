package com.chapter3;

import com.chapter3.text.Node;
import com.chapter3.text.SingleLinkedList;
/**
 * <p>MergeList</p>  
 * <p>输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是按照递增排序的</p>
 * <p>例如输入下面两个链表list1，list2</p>
 * <p>list1: 1->3->5->7->9</p>
 * <p>list2: 2->4->6->8->10</p>
 * <p>合并之后的结果是：</p>
 * <p>1->2->3->4->5->6->7->8->9->10</p>
 * @author yanan  
 * @date 2018年3月16日
 */
public class MergeList {

	public static void main(String[] args) {
		SingleLinkedList list1=new SingleLinkedList();
		SingleLinkedList list2=new SingleLinkedList();
		for(int i=1;i<=10;i+=2){
			list1.addToTail(i);
		}
		for(int i=2;i<=10;i+=2){
			list2.addToTail(i);
		}
		printList(list1);
		printList(list2);
		
		printNode(mergeList(list1.getHead(), list2.getHead()));
	}
	
	static void printList(SingleLinkedList list){
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	static void printNode(Node node){
		Node temp=node;
		while(temp!=null){
			System.out.println(temp.getInfo());
			temp=temp.getNext();
		}
	}
	
	/**
	 * 合并两个有序的链表
	 * @param head1
	 * @param head2
	 * @return
	 */
	static Node mergeList(Node head1,Node head2){
		if(head1==null){
			return head2;
		}
		if(head2==null){
			return head1;
		}
		Node mergedHead=null;
		if(head1.getInfo().intValue()<head2.getInfo().intValue()){
			mergedHead=head1;
			mergedHead.setNext(mergeList(head1.getNext(), head2));
		}else{
			mergedHead=head2;
			mergedHead.setNext(mergeList(head1, head2.getNext()));
		}
		return mergedHead;
	}

}
