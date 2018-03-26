package com.chapter5;

/**
 * <p>AddCommaToPositiveInteger</p>  
 * <p>编写一个递归函数，对于任一正整数，该函数返回一个在适当
 * 位置加入逗号的字符串，例如putCommas(1234567)返回字符串“1,234,567” </p>  
 * @author yanan  
 * @date 2018年2月11日
 */
public class AddCommaToPositiveInteger {

	public static void main(String[] args) {
		String numStr="1234567890";
		System.out.println(putCommas(numStr));
	}
	
	static String putCommas(String numStr){
		if(numStr.length()<3){
			return numStr;
		}
		if(numStr.length()==3){
			return ","+numStr;
		}
		String frontStr=numStr.substring(0, numStr.length()-3);
		String endStr=numStr.substring(numStr.length()-3,numStr.length());
		return putCommas(frontStr)+putCommas(endStr); 
	}

}
