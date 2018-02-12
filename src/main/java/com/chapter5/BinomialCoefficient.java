package com.chapter5;
/**
 * 二项式系数是什么还需研究
 * <p>Title: BinomialCoefficient</p>  
 * <p>Description:编写一个递归函数，根据下面的定义计算二项式系数 </p>  
 * @author yanan  
 * @date 2018年2月11日
 */
public class BinomialCoefficient {

	public static void main(String[] args) {
		System.out.println(compute(3,10));
	}
	
	static int compute(int k,int n){
		if(k==0 || k==n){
			return 1;
		}
		return compute(k,n-1)+compute(k-1,n-1);
	}

}
