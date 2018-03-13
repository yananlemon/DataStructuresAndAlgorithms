package com.chapter5;

/**
 * <p>CalculateSum</p>  
 * @author yanan 
 * @date 2018年3月9日
 */
public class CalculateSum {

	public static void main(String[] args) {
		int start=1;
		//int end=500000;-Xss1024m
		int end=500000000;
		long startTime=System.currentTimeMillis();
		System.out.println(calSumWithIterator(start, end));
		long endTime=System.currentTimeMillis();
		System.out.println("使用循环花费"+(endTime-startTime)+"ms.");
		
		startTime=System.currentTimeMillis();
		//System.out.println(calSumWithRecursion(start, end));
		endTime=System.currentTimeMillis();
		System.out.println("使用递归花费"+(endTime-startTime)+"ms.");
		
		startTime=System.currentTimeMillis();
		System.out.println(calSumWithGauss(start, end));
		endTime=System.currentTimeMillis();
		System.out.println("使用高斯求和公式花费"+(endTime-startTime)+"ms.");
	}
	
	// calculate sum with recursion
	static long calSumWithRecursion(int start,int end){
		if(start==end){
			return end;
		}else{
			return start+calSumWithRecursion(start+1, end);
		}
	}
	
	static long calSumWithIterator(int start,int end){
		int sum=0;
		for(;start<=end;start++){
			sum+=start;
		}
		return sum;
	}
	
	static long calSumWithGauss(long start,long end){
		return (start+end)*end/2;
	}

}
