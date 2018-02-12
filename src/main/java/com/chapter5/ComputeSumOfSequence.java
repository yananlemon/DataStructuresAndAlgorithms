package com.chapter5;

/**
 * <p>Title: AddCommaToPositiveInteger</p>  
 * <p>Description:编写一个递归函数，计算序列中前n项的和：
 * 1+1/2-1/3+1/4-1/5 </p>  
 * @author yanan  
 * @date 2018年2月12日
 */
public class ComputeSumOfSequence {

	public static void main(String[] args) {
		System.out.println(compute(7));
	}
	
	static double compute(double n){
		if(n<0){
			throw new IllegalArgumentException("期望是非负整数！");
		}
		if(n<=1 ){
			return 1;
		}
		if(n%2!=0){
			return -(1/n)+compute(n-1);
		}else{
			return 1/n+compute(n-1);
		}
	}
	
	

}
