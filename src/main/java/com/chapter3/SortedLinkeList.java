package com.chapter3;

import com.chapter3.text.Node;
import com.chapter3.text.SingleLinkedList;

/**
 * <p>SortedLinkeList</p>  
 * <p>将单链表中的数字按照升序排列</p>
 * @author yanan  
 * @date 2018年3月18日
 */
public class SortedLinkeList {

	public static void main(String[] args) {
		SingleLinkedList list1=new SingleLinkedList();
		list1.addToTail(10);
		list1.addToTail(2);
		list1.addToTail(1);
		list1.addToTail(6);
		list1.addToTail(3);
		sort(list1);
		printList(list1);
		
	}
	
	static void printNode(Node node){
		Node temp=node;
		while(temp!=null){
			System.out.println(temp.getInfo());
			temp=temp.getNext();
		}
	}
	
	static void sort(SingleLinkedList list){
		int[] array=new int[list.size()];
		for(int i=0;i<list.size();i++){
			array[i]=list.get(i);
		}
		for(int i=0;i<array.length-1;i++){
			for(int j=0;j<array.length-1-i;j++){
				if(array[j]>array[j+1]){
					int temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
		list.clear();
		for(int i=0;i<array.length;i++){
			list.addToTail(array[i]);
		}
		
		
	}
	
	static void printList(SingleLinkedList list){
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

}
