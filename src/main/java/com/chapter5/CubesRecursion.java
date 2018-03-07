package com.chapter5;
/**
 * <p>Title: CubesRecursion</p>  
 * <p>Description:给出下列函数的递归版本</p>  
 * @author yanan  
 * @date 2018年3月7日
 */
public class CubesRecursion {

	static int startIndex=1;
	static int n=10;
	public static void main(String[] args) {
		cubesWithIterator(n);
		cubesWithRecursion(startIndex);
	}
	
	static void cubesWithIterator(int n){
		for(int i=1;i<=n;i++){
			System.out.printf("%d\n", i*i*i);
		}
	}
	
	static void cubesWithRecursion(int startIndex){
		if(startIndex>n){
			return;
		}
		System.out.printf("%d\n", startIndex*startIndex*startIndex);
		cubesWithRecursion(startIndex+1);
	}

}
