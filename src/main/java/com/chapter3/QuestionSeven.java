package com.chapter3;

import com.chapter3.text.Node;
import com.chapter3.text.SingleLinkedList;

/**
 * <p>第7题</p>  
 * <p>从链表L1中删除有序链表L2和L3所给位置上的节点。例如L1=（1,2,3,4,5），而L2=（2,4,8），L3=（2,5）
 * 那么删除之后，L1=（1,3）
 * </p>  
 * @author yanan  
 * @date 2018年3月16日
 */
public class QuestionSeven {

	public static void main(String[] args) {
		SingleLinkedList list1=new SingleLinkedList();
		for(int i=1;i<=5;i++){
			list1.addToTail(i);
		}
		
		//1.初始化两个有序链表
		SingleLinkedList list2=new SingleLinkedList();
		SingleLinkedList list3=new SingleLinkedList();
		initData(list2, list3);
		
		//2.首先合并两个有序链表
		Node mergeNode=mergeList(list2.getHead(), list3.getHead());
		SingleLinkedList deleteIndexList=buildListWithNode(mergeNode);

		//3.将目标链表相应位置设置为-1
		for(int i=0;i<deleteIndexList.size();i++){
			if(deleteIndexList.get(i).intValue()-1>=list1.size()){
				continue;
			}
			list1.set(-1, deleteIndexList.get(i)-1);
		}

		//4.删除目标链表中所有值为1的节点
		int i=-1;
		while((i=checkWhetherExit(list1, -1))!=-1){
			list1.deleteNode(i);
		}
		printList(list1);
		
	}
	
	static int checkWhetherExit(SingleLinkedList list,int value){
		for(int i=0;i<list.size();i++){
			if(list.get(i).intValue()==-1){
				return i;
			}
		}
		return -1;
	}

	private static void initData(SingleLinkedList list2, SingleLinkedList list3) {
		list2.addToTail(2);
		list2.addToTail(4);
		list2.addToTail(8);
		list3.addToTail(2);
		list3.addToTail(5);
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
	
	static SingleLinkedList buildListWithNode(Node node){
		SingleLinkedList list=new SingleLinkedList();
		Node temp=node;
		while(temp!=null){
			list.addToTail(temp.getInfo());
			temp=temp.getNext();
		}
		return list;
	}
	
	/**
	 * 
	 * @param head1 2->4->8
	 * @param head2 2->5->7
	 * @return
	 */
	static Node mergeList(Node head1,Node head2){
		if(head1==null){
			return head2;
		}
		if(head2==null){
			return head1;
		}
		Node newNode=null;
		if(head1.getInfo().intValue()<head2.getInfo().intValue()){
			newNode=head1;
			newNode.setNext(mergeList(head1.getNext(), head2));
		}else if(head1.getInfo().intValue()>head2.getInfo().intValue()){
			newNode=head2;
			newNode.setNext(mergeList(head1, head2.getNext()));
		}else{
			newNode=head1;
			newNode.setNext(mergeList(head1.getNext(), head2.getNext()));
		}
		return newNode;
	}

}
