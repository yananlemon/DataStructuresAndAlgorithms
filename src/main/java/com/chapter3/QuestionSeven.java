package com.chapter3;

import com.chapter3.text.SingleLinkedList;

/**
 * <p>第7题</p>  
 * <p></p>  
 * @author yanan  
 * @date 2018年3月16日
 */
public class QuestionSeven {

	public static void main(String[] args) {
		SingleLinkedList list=new SingleLinkedList();
		//添加A,B,C,D,E
		for(int i=65;i<=69;i++){
			list.addToTail(i);
		}
		
		SingleLinkedList list2=new SingleLinkedList();
		list2.addToTail(2);
		list2.addToTail(4);
		list2.addToTail(8);
		SingleLinkedList list3=new SingleLinkedList();
		list3.addToTail(2);
		list3.addToTail(5);
		SingleLinkedList list4=union(list2, list3);
		delete(list, list4);
		for(int i=0;i<list.size();i++){
			System.out.println((char)list.get(i).intValue());
		}
	}

	private static void delete(SingleLinkedList list, SingleLinkedList list2) {
		for(int i=0;i<list2.size();i++){
			int index=list2.get(i)-1;
			if(index>=list.size() || index<0){
				continue;
			}
			list.deleteNode(index);
		}
	}
	// list1:2,4,8
	// list2:2,5
	static SingleLinkedList union(SingleLinkedList list1, SingleLinkedList list2){
		for(int i=0;i<list1.size();i++){
			if(!checkExist(list1.get(i),list2)){
				list2.addToTail(list1.get(i));
			}
		}
		return list2;
	}

	private static boolean checkExist(Integer integer, SingleLinkedList list2) {
		for(int k=0;k<list2.size();k++){
			if(list2.get(k)==integer.intValue()){
				return true;
			}
		}
		return false;
	}

}
