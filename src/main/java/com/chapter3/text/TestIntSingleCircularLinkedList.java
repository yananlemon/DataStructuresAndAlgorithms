package com.chapter3.text;

public class TestIntSingleCircularLinkedList {

	public static void main(String[] args) {
		IntSingleCircularLinkedList iscl=new IntSingleCircularLinkedList();
		/*for(int i=1;i<=10;i++){
			iscl.addToHead(i);
		}
		print(iscl);//list is : 10,9,8,7,6,5,4,3,2,1?
		iscl.deleteFromHead();
		iscl.deleteFromHead();
		print(iscl);//list is : 8,7,6,5,4,3,2,1?
		*/
		/*for(int i=1;i<=10;i++){
			iscl.addToTail(i);
		}
		print(iscl);//list is : 1,2,3,4,5,6,7,8,9,10?
		iscl.deleteFromTail();
		iscl.deleteFromTail();
		print(iscl);//list is : 1,2,3,4,5,6,7,8?
		*/	
		
		for(int i=1;i<=10;i++){
			iscl.addToTail(i);
		}
		iscl.deleteNode(8);
		print(iscl);//list is : 1,2,3,4,5,6,7,8,10?
		iscl.deleteNode(5);
		print(iscl);//list is : 1,2,3,4,5,7,8,10?*/	
	}
	
	static void print(IntSingleCircularLinkedList iscl){
		StringBuffer sb=new StringBuffer("[");
		for(int i=0;i<iscl.size();i++){
			if(i!=iscl.size()-1){
				sb.append(iscl.get(i)).append(",");
			}else{
				sb.append(iscl.get(i));
			}
		}
		sb.append("]");
		System.out.println(sb);
	}

}
