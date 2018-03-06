package com.chapter5;

/**
 * <p>Title: Pow</p>  
 * <p>Description:编写一个递归函数，求x的n次方</p> 
 * @author yanan  
 * @date 2018年2月21日
 */
public class Pow {

	public static void main(String[] args) {
		System.out.println(pow(2,7));
	}
	
	/**
	 * 求x的n次方
	 * @param x
	 * @param n
	 * @return int
	 */
	static int pow(int x,int n){
		if((n&1)==1){//n为奇数
			return pow(x,n-1)*x; 
		}else{
			if(n>2){
				return pow(x,n/2)*pow(x,n/2);
			}else{
				return x*x;
			}
		}
	}

}
