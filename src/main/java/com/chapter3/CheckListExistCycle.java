package com.chapter3;

/**
 * <p>CheckListExitLink</p>  
 * <p>判断链表是否有环 </p>  
 * @author yanan  
 * @date 2018年3月19日
 */
public class CheckListExistCycle {

	public static void main(String[] args) {
		//TestNode node1=new TestNode(5);
		
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
		
		TestNode node1=new TestNode(1);
		TestNode node2=new TestNode(2);
		TestNode node3=new TestNode(3);
		TestNode node4=new TestNode(4);
		TestNode node5=new TestNode(5);
		TestNode node6=new TestNode(6);
		TestNode node7=new TestNode(7);
		TestNode node8=new TestNode(8);
		TestNode node9=new TestNode(9);
		TestNode node10=new TestNode(10);
		TestNode node11=new TestNode(11);
		TestNode node12=new TestNode(12);
		
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node5);
		node5.setNext(node6);
		node6.setNext(node7);
		node7.setNext(node8);
		node8.setNext(node9);
		node9.setNext(node10);
		node10.setNext(node11);
		node11.setNext(node12);
		node12.setNext(node4);
		
		System.out.println(checkListExistCycle(node1));
		getEntranceOfCycle(node1);
		getLengthOfCycle(node1);
	}
	
	/**
	 * 判断链表是否有环。
	 * @param head
	 * @return
	 */
	static boolean checkListExistCycle(TestNode head){
		if(head==null){
			return false;
		}
		if(head==head.getNext()){
			return true;
		}
		TestNode node1=head;
		TestNode node2=head;
		while(node1!=null && node2.getNext().getNext()!=null){
			node1=node1.getNext();
			node2=node2.getNext().getNext();
			if(node1.getVal()==node2.getVal()){
				System.out.println("相遇的位置："+node1.val);
				return true;
			}
			
		}
		return false;
			
	}
	
	/**
	 * 获取环的入口
	 * @param head
	 * @return
	 */
	static TestNode getEntranceOfCycle(TestNode head){
		if(head==null){
			return null;
		}
		if(head==head.getNext()){
			return head;
		}
		TestNode tortoise = head;
		TestNode rabbit   = head;
		while(tortoise != null && rabbit.getNext().getNext() != null){
			tortoise = tortoise.getNext();
			rabbit   = rabbit.getNext().getNext();
			if(tortoise.getVal() == rabbit.getVal()){
				break;
			}
			
		}
		tortoise = head;
		while(tortoise != null && rabbit.getNext() != null){
			tortoise = tortoise.getNext();
			rabbit   = rabbit.getNext();
			if(tortoise.getVal() == rabbit.getVal()){
				break;
			}
			
		}
		System.out.println(rabbit.getVal()+"是环的入口");
		return rabbit;
	}
	
	/**
	 * 获取环的长度
	 * @param head
	 * @return
	 */
	static TestNode getLengthOfCycle(TestNode head){
		if(head==null){
			return null;
		}
		if(head==head.getNext()){
			return head;
		}
		TestNode tortoise = head;
		TestNode rabbit   = head;
		while(tortoise != null && rabbit.getNext().getNext() != null){
			tortoise = tortoise.getNext();
			rabbit   = rabbit.getNext().getNext();
			if(tortoise.getVal() == rabbit.getVal()){
				break;
			}
			
		}
		int count = 0;
		while(rabbit.getNext() != null){
			rabbit   = rabbit.getNext();
			count ++ ;
			if(tortoise.getVal() == rabbit.getVal()){
				break;
			}
			
		}
		System.out.printf("环的长度:%d\n",count);
		return rabbit;
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
