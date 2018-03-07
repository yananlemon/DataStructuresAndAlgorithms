package com.chapter5;
/**
 * <p>Title: BinaryConvertor</p>  
 * <p>Description:编写递归和非递归函数，输出非负整数的二进制表示。在函数中不能使用位操作。</p>  
 * @author yanan  
 * @date 2018年3月7日
 */
public class BinaryConvertor {

	public static void main(String[] args) {
		int num=1173;
		System.out.println("Java Api："+Integer.toBinaryString(num));
		System.out.println("非递归版本   ："+convertBinaryWithoutRecursion(num));
		System.out.println("递归版本       ："+convertBinaryWithRecursion(num));
	}
	
	static StringBuffer convertBinaryWithoutRecursion(int num){
		if(num<0){
			throw new IllegalArgumentException("参数必须是正整数");
		}
		StringBuffer sb=new StringBuffer();
		int quotient=num/2;//商
		while((quotient=num/2)!=0){
			sb.append(num%2);
			num=quotient;
		}
		sb.append(num%2);
		/*if(sb.length()<32){
			int len=sb.length();
			for(int i=0;i<32-len;i++){
				sb.append("0");
			}
		}*/
		return sb.reverse();
	}
	static StringBuffer sb=new StringBuffer();
	
	static StringBuffer convertBinaryWithRecursion(int num){
		if(num<0){
			throw new IllegalArgumentException("参数必须是正整数");
		}
		sb.append(num%2);
		if(num/2==0){
			return sb.reverse();
		}else{
			return convertBinaryWithRecursion(num/2);
		}
	}
	

}
