package com.chapter5;

/**
 * <p>Title: AddCommaToPositiveInteger</p>  
 * <p>Description:编写一个递归函数，对于正整数n，输出下面范围内的奇数</p> 
 * <p> a.1到n之间<p>
 * <p> b.n到1之间</p>  
 * @author yanan  
 * @date 2018年2月12日
 */
public class PrintOddNumbers {

	public static void main(String[] args) {
		printOddNumbersWithAsc(index);
		printOddNumbersWithDesc(n);
	}
	
	static int n=30;
	static int index=1;
	
	static void printOddNumbersWithAsc(int index){
		if(index>n){
			return;
		}
		if((index&1)==1){
			System.out.println(index);
		}
		printOddNumbersWithAsc(index+1);
		
	}
	
	static void printOddNumbersWithDesc(int num){
		if(num<=0){
			return;
		}
		if(num==1){
			System.out.println(num);
			return;
		}
		if((num&1)==1){
			System.out.println(num);
		}
		printOddNumbersWithDesc(num-1);
	}

}
