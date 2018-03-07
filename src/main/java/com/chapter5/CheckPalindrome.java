package com.chapter5;
/**
 * <p>Title: CheckPalindrome</p>  
 * <p>Description:递归检测下面的对象是不是回文
 * a.一个单词
 * b.一个句子(忽略空格，大小写以及标点符号，这样的句子"Madam,I'm Adam")
 *  </p>  
 * @author yanan  
 * @date 2018年3月7日
 */
public class CheckPalindrome {

	public static void main(String[] args) {
		//String test="redivider";
		//String test2="Able was I ere I saw Elba";
		String test3="Madam,I'm Adam";
		char[] cs=ignoreSpecialSymbol(test3);
		char c='a';
		System.out.println((char)(c+32));
		System.out.println(checkPa(cs, 0, cs.length));
		
	}
	static char[] ignoreSpecialSymbol(String str){
		StringBuffer result=new StringBuffer();
		for(int i=0;i<str.length();i++){
			char c=str.charAt(i);
			if(!checkSpecialSymbol(c)){
				if(c>=65 && c<=90){
					result.append((char)(c+32));
				}else{
					result.append(c);
				}
			}
		}
		System.out.println(result);
		return result.toString().toCharArray();
		
	}
	static boolean checkSpecialSymbol(char c){
		if(c==' '||c==','||c=='.'||c=='\''){
			return true;
		}
		return false;
	}
	static boolean checkPa(char[] str,int start,int length){
		if(length<=1){
			return true;
		}
		if(str[start]==str[length-1]){
			return checkPa(str, start+1, length-1);
		}
		return false;
	}

}
