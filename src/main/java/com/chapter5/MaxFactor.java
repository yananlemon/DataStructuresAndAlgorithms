package com.chapter5;

/**
 * <p>Title: MaxFactor</p>  
 * <p>Description:编写一个递归函数，，根据下面的定义，返回两个整数n和m的最大公约数：</p> 
 * <p> 1.m,when m<=n<p>
 * <p> 2.GCD(m,n), when n<m</p>
 * <p> 3.GCD(m,n mod m) when other case</p>  
 * @author yanan  
 * @date 2018年2月20日
 */
public class MaxFactor {

	public static void main(String[] args) {
		System.out.println(gcd(18,15));
	}
	
	static int gcd(int n,int m){
		if(m<=n && n%m==0){
			return m;
		}
		if(n<m){
			return gcd(m,n);
		}
		return gcd(m,n%m);
	}

}
