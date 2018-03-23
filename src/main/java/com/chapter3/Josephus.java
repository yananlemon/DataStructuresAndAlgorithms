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
		int n=41;
		int m=3;
		System.out.println(josephus(n, m));
		System.out.printf("最后活下来的人的编号是：%d",josephusWithCircularLinkedList(n, m));
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
		
		// 2.循环删除第m个元素，直到剩下两个人。
		while(list.size()!=2){
			list.deleteNode(m-1);
			list=buildNewList(list,m-1);
		}
		System.out.printf("留下的两个人是：%d,%d\n", list.get(0),list.get(1));
		
		//如果m是奇数,那么活下来的人的就是list中第二个元素
		if((m&1)==1){
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
