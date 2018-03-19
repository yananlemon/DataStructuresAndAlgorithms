package com.chapter3;

/**
 * <p>CheckListExitLink</p>  
 * <p>判断链表是否有环 </p>  
 * @author yanan  
 * @date 2018年3月19日
 */
public class CheckListExitLink {

	public static void main(String[] args) {
		TestNode node1=new TestNode(5);
		
		//对应图B
		//node1.setNext(node1);
		
		//对应图A
		/*TestNode node2=new TestNode(4);
		TestNode node3=new TestNode(10);
		TestNode node4=new TestNode(16);
		TestNode node5=new TestNode(2);
		TestNode node6=new TestNode(8);
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node5);
		node5.setNext(node6);
		node6.setNext(node4);//产生环*/
		
		System.out.println(checkListExitLink(node1));
		
	}
	
	/**
	 * 判断链表是否有环。
	 * @param head
	 * @return
	 */
	static boolean checkListExitLink(TestNode head){
		if(head==null){
			return false;
		}
		if(head==head.getNext()){
			return true;
		}
		TestNode node1=head;
		TestNode node2=head;
		while(node1!=null && node2.getNext()!=null){
			if(node1.getVal()==node2.getVal()){
				return true;
			}
			node1=node1.getNext();
			node1=node1.getNext().getNext();
			
		}
		return false;
			
	}

}
class TestNode{
	int val;
	TestNode next;
	public TestNode(int val, TestNode next) {
		super();
		this.val = val;
		this.next = next;
	}
	public TestNode(int val) {
		super();
		this.val = val;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public TestNode getNext() {
		return next;
	}
	public void setNext(TestNode next) {
		this.next = next;
	}
	
	
}
