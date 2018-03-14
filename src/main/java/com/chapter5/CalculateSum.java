package com.chapter5;

/**
 * <p>CalculateSum</p>  
 * @author yanan 
 * @date 2018年3月9日
 */
public class CalculateSum {

	public static void main(String[] args) {
		int start=1;
		int end=500000;//-Xss1024m
		long startTime=System.currentTimeMillis();
		System.out.println(calSumWithIterator(start, end));
		long endTime=System.currentTimeMillis();
		System.out.println("使用循环花费"+(endTime-startTime)+"ms.");
		
		startTime=System.currentTimeMillis();
		System.out.println(calSumWithRecursion(start, end));
		endTime=System.currentTimeMillis();
		System.out.println("使用递归花费"+(endTime-startTime)+"ms.");
		
		startTime=System.currentTimeMillis();
		System.out.println(calSumWithGauss(start, end));
		endTime=System.currentTimeMillis();
		System.out.println("使用高斯求和公式花费"+(endTime-startTime)+"ms.");
	}
	
	/**
	 * 使用递归计算等差数列的和(从start开始，到end结束，每次加1)
	 * @param start 起始数字
	 * @param end	结束数字
	 * @return long 返回结果
	 */
	static long calSumWithRecursion(int start,int end){
		if(start==end){
			return end;
		}else{
			return start+calSumWithRecursion(start+1, end);
		}
	}
	
	/**
	 * 使用循环计算等差数列的和(从start开始，到end结束，每次加1)
	 * @param start 起始数字
	 * @param end	结束数字
	 * @return long 返回结果
	 */
	static long calSumWithIterator(int start,int end){
		int sum=0;
		for(;start<=end;start++){
			sum+=start;
		}
		return sum;
	}
	
	/**
	 * 使用高斯求和公式计算等差数列的和(从start开始，到end结束，每次加1)
	 * @param start 起始数字
	 * @param end	结束数字
	 * @return long 返回结果
	 */
	static long calSumWithGauss(long start,long end){
		return (start+end)*end/2;
	}

}
