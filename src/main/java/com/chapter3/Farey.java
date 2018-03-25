package com.chapter3;
/**
 * <p>Farey</p>  
 * <p>1.一级Farey分数的定义为序列(0/1,1/1)。此序列的二级形式是(0/1,1/2,1/1),
 * 三级序列是(0/1,1/3,1/2,2/3,1/1),四级序列是(0/1,1/4,1/3,1/2,2/3,3/4,1/1)。
 * 对于每一级n，只要c＋d<=n，就要在两个相邻的分数a/c和b/d中间插入一个新的分数(a+b)/(c+d)。
 * 编写程序，根据用户输入的n（以相同的方式进行扩展）创建一个n级的Farey分数链表，并显示其内容。</p>  
 * @author yanan  
 * @date 2018年3月25日
 */
public class Farey {
	
	public static void main(String[] args) {
		FNode oneLevel=init();
		int n=6;
		FNode first=oneLevel;
		FNode second=first.next;
		while(first.next!=null){
			FNode newFarey=null;
			int sumOfDeno=first.denominator+second.denominator;
			if(sumOfDeno<=n){
					newFarey=new FNode(first.molecule+second.molecule, 
							first.denominator+second.denominator);
					newFarey.next=second;
					first.next=newFarey;
					second=newFarey;
			}else{
				first=second;
				second=second.next;
			}
			
		}
		showFarey(oneLevel);
	}
	
	static void showFarey(FNode farey){
		if(farey==null){
			System.out.println("farey is empty!");
			return;
		}
		FNode temp=farey;
		while(temp!=null){
			System.out.printf("%d\\%d\t", temp.molecule,temp.denominator);
			temp=temp.next;
		}
	}
	
	/**
	 * 初始化一级Farey分数
	 * @return FNode
	 */
	static FNode init(){
		FNode rs=new FNode(0, 1);
		FNode fNext=new FNode(1, 1);
		rs.next=fNext;
		return rs;
	}

}


class FNode{
	int molecule;//分子
	int denominator;//分母
	FNode next;//下一个节点
	public FNode(){
		
	}
	
	public FNode(int molecule,int denominator){
		this.denominator=denominator;
		this.molecule=molecule;
	}
}
