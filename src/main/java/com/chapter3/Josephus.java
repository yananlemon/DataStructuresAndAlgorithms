package com.chapter3;

import com.chapter3.text.IntSingleCircularLinkedList;
import com.chapter3.text.Node;

/**
 * <p>Josephus</p>  
 * <p>约瑟夫环问题 </p>  
 * @author yanan  
 * @date 2018年3月20日
 * http://blog.csdn.net/u011500062/article/details/72855826 
 */
public class Josephus {

	public static void main(String[] args) {
		System.out.println(josephus(11, 3));
		System.out.println(josephusWithCircularLinkedList(11, 3));
	}
	
	/**
	 * 约瑟夫环问题(使用数学公式解决)
	 * @param n n个人报数
	 * @param m 每报到m的时候杀掉这个人
	 * @return 活下来的人的编号
	 */
	static int josephus(int n,int m){
		if(n==1){
			return 0;
		}
		return (josephus(n-1,m)+m)%n;
	}
	
	static int josephusWithCircularLinkedList(int n,int m){
		IntSingleCircularLinkedList list=new IntSingleCircularLinkedList();
		// 1.创建约瑟夫环
		for (int i = 1; i <= n; i++) {
			list.addToTail(i);
		}
		while(list.size()!=2){
			list.deleteNode(m-1);
			list=buildNewList(list,m-1);
		}
		if((m&1)==1){//如果m是奇数,那么活下来的人的编号是
			return list.get(1);
		}
		return list.get(0);
	}
	
	static IntSingleCircularLinkedList buildNewList(IntSingleCircularLinkedList list,int start){
		if(start>=list.size()){
			return list;
		}
		IntSingleCircularLinkedList rs=new IntSingleCircularLinkedList();
		int index=0;
		Node curr=null;
		curr=list.getNode(start);
		Node temp=curr;
		while(index<list.size()){
			rs.addToTail(temp.getInfo());
			temp=temp.getNext();
			index++;
		}
		return rs;
	}

}
