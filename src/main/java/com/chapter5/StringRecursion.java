package com.chapter5;

/**
 * <p>StringRecursion</p>  
 * <p>对于给定的字符，不使用函数strchr()和函数strrchr()，以递归的方式完成下面工作：</p>
 * <p>a.检查该字符是否在一个字符串中</p>
 * <p>b.计算在字符串中出现该字符的次数</p>
 * <p>c.将一个字符串中出现的所有该字符全部删除</p> 
 * @author yanan  
 * @date 2018年3月8日
 */
public class StringRecursion {

	public static void main(String[] args) {
		String source="It's not a Bug, it's an unknown feature";
		char s='a';
		//System.out.println(testContains(source,source.length(), s));
		//System.out.println(count(source,source.length(), s));
		System.out.println(source);
		System.out.println(remove(source,source.length(), s));
	}
	static int index=0;
	
	/**
	 * 递归检测字符s是否在字符串source中,如果在则返回所处的索引.否则返回-1.
	 * @param source
	 * @param s
	 * @return
	 */
	static int testContains(String source,int len,char s){
		if(index>=len){
			return -1;
		}else{
			if(source.charAt(0)==s){
				return index;
			}else{
				source=source.substring(1, source.length());
				index++;
				return testContains(source,len, s);
			}
		}
	}
	
	static int index2,count;
	
	/**
	 * 递归计算字符s在字符串source中出现的次数
	 * @param source
	 * @param s
	 * @return
	 */
	static int count(String source,int length,char s){
		if(index2>=length){
			return count;
		}else{
			if(source.charAt(0)==s){
				count++;
			}
			source=source.substring(1, source.length());
			index2++;
			return count(source,length, s);
		}
	}
	/**
	 * 将一个字符串中出现的所有该字符全部删除
	 * @param source
	 * @param length
	 * @param s
	 */
	static String remove(String source,int length,char s){
		int i=testContains(source,length, s);
		if(i!=-1){
			char[] ch=source.toCharArray();
			ch[i]=' ';
			String newSource=new String(ch);
			index=0;
			return remove(newSource,length,s);
		}else{
			return source;
		}
	}

}
