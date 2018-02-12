package com.chapter5;
/**
 * 
* <p>Title: ComputeProductUsingRecurion</p>  
* <p>Description:编写一个递归函数，只用加减和比较，来计算两个数字的乘积 </p>  
* @author shenlan  
* @date 2018年2月11日
 */
public class ComputeProductUsingRecurion {

	public static void main(String[] args) {
		System.out.println(computeProduct(30,50));
	}
	/**
	 * 只用加减法和比较运算符使用递归计算两个数字的乘积
	 * @param a 第一个数字
	 * @param b 第二个数字
	 * @return a和b的乘积
	 */
	public static int computeProduct(int a,int b){
		if(a==0 || b==0){
			return 0;
		}
		if(b==1){
			return a;
		}
		return a+computeProduct(a, b-1);
	}

}
