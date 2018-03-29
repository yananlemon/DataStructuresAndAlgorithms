package com.chapter4;

import com.chapter4.text.MyStack;

/**
 * <p>HexConvertion</p>
 * <p>
 * 编写一个程序，将十进制数转换为其他（技术介于2和9之间）进制的数。可以
 * 将要转换的数重复除以基数，然后将除得的余数按照反方向排列来实现转换。例如，要将
 * 6转换为二进制，需要进行下述三次除法：6/2=3，余数为0；3/2=1，余数为1；1/2=0,余数为1。
 * 将余数0、1、1，按照相反的顺序排列成110，则数6转换成二进制数110。
 * 修改程序，当基数为11到27之间的一个数时，也能实现进制的转化。基数大于10的计数系统需要更多的符号，
 * 所以使用大写字母来表示。例如十六进制的计数系统需要16个字符：0、1、2.....9、A、B、C、D、E、F。
 * 在这个系统中，十进制数26等于十六进制表示的1A，因为26/16=1，余数为10（也就是A），而1/16=0,余数为1。
 * </p>
 * @author yanan  
 * @date 2018年3月29日
 */
public class HexConvertion {

	public static void main(String[] args) {
		int num = 466;
		int base = 7;
		String rs= hexConvertionBaseTwoToNine(num, base);
		System.out.printf("%d的%d进制是:%s\n",num,base,rs);
		
		int num2 = 260;
		int base2 = 27;
		String rs2= hexConvertionBaseElevenToTwentySeven(num2, base2);
		System.out.printf("%d的%d进制是:%s",num2,base2,rs2);
	}
	
	/**
	 * 将十进制数转换为相应进制的数(基于参数base)
	 * @param num 十进制数
	 * @param base 基数范围是[2,9]
	 * @return 转换结果
	 */
	static String hexConvertionBaseTwoToNine(int num,int base){
		if(base < 2 || base > 9 ){
			throw new Error("基数需要介于2和9之间！");
		}
		StringBuffer sb = new StringBuffer();
		MyStack<Integer> remainderStack = new MyStack<Integer>();
		int remainder = 0;
		int quotient = 0;
		while((quotient = num / base) != 0){
			remainder = num % base;
			remainderStack.push(remainder);
			num = quotient;
		}
		remainderStack.push(num % base);
		while(!remainderStack.isEmpty()){
			sb.append(remainderStack.pop());
		}
		return sb.toString();
	}
	
	/**
	 * 将十进制数转换为相应进制的数(基于参数base)
	 * @param num 十进制数
	 * @param base 基数范围是[11,27]
	 * @return 转换结果
	 */
	static String hexConvertionBaseElevenToTwentySeven(int num,int base){
		if(base < 11 || base > 27 ){
			throw new Error("基数需要介于11和27之间！");
		}
		StringBuffer sb = new StringBuffer();
		MyStack<String> remainderStack = new MyStack<String>();
		int remainder = 0;
		int quotient = 0;
		while((quotient = num / base) != 0){
			remainder = num % base;
			switch (remainder) {
				case 10:
					remainderStack.push("A");
					break;
				case 11:
					remainderStack.push("B");
					break;
				case 12:
					remainderStack.push("C");
					break;
				case 13:
					remainderStack.push("D");
					break;
				case 14:
					remainderStack.push("E");
					break;
				case 15:
					remainderStack.push("F");
					break;
				case 16:
					remainderStack.push("G");
					break;
				case 17:
					remainderStack.push("H");
					break;
				case 18:
					remainderStack.push("I");
					break;
				case 19:
					remainderStack.push("J");
					break;
				case 20:
					remainderStack.push("K");
					break;
				case 21:
					remainderStack.push("L");
					break;
				case 22:
					remainderStack.push("M");
					break;
				case 23:
					remainderStack.push("N");
					break;
				case 24:
					remainderStack.push("O");
					break;
				case 25:
					remainderStack.push("P");
					break;
				case 26:
					remainderStack.push("Q");
					break;
				case 27:
					remainderStack.push("R");
					break;

				default:
					remainderStack.push(remainder);
					break;
			}
			num = quotient;
		}
		remainderStack.push(num % base);
		while(!remainderStack.isEmpty()){
			sb.append(remainderStack.pop());
		}
		return sb.toString();
	}

}
