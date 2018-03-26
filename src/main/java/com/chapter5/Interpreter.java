package com.chapter5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
/**
 * <p>Interpreter</p>  
 * <p>递归下降解释器</p>
 *  上下文无关文法如下：
 * 	<statement>-><id>=<expression>
 *	<expression>-><term>{(+|-)<term>}
 *	<term>-><factor>{(*|/)<factor>}
 *	<factor>->-<factor>|(<expression>)|number|<id>
 *	<id>->a|b|c.....|z
 * @author yanan  
 * @date 2018年3月8日
 */
public class Interpreter {
	
	private String look;
	private int index;
	private List<String> tokens=new ArrayList<String>();
	private HashMap<String,Double> idMap=new HashMap<String,Double>();
	
	/**
	 * 词法分析
	 * @param line 当前行
	 */
	void lexicalAnalysis(String line){
		char[] content=line.toCharArray();
		while(index<content.length){
			if(content[index]==' '){
				index++;
			}
			//是标识符
			if(content[index]>='a' && content[index]<='z'){
				StringBuffer sb=new StringBuffer();
				sb.append(content[index++]);
				while(content[index]>='a' && content[index]<='z'){
					sb.append(content[index]);
					index++;
				}
				while(Character.isDigit(content[index])){
					sb.append(content[index]);
					index++;
				}
				tokens.add(sb.toString());
			}else if(checkWhetherSeparator(content[index])){
				tokens.add(String.valueOf(content[index]));
				index++;
			}else if(Character.isDigit(content[index])){
				StringBuffer sb=new StringBuffer();
				sb.append(content[index]);
				index++;
				while(Character.isDigit(content[index])||content[index]=='.'){
					sb.append(content[index]);
					index++;
				}
				tokens.add(sb.toString());
			}else if(content[index]=='='||content[index]==';'){
				StringBuffer sb=new StringBuffer();
				sb.append(content[index]);
				index++;
				tokens.add(sb.toString());
			}
		}
	}
	
	/**
	 * 检查是否是分隔符(+,-,*,/,(,),^)
	 * @param ch
	 * @return
	 */
	private boolean checkWhetherSeparator(char ch){
		if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='^'){
			return true;
		}
		return false;
	}
	
	void match(String s){
		if(s.equals(look)){
			move();
		}else{
			throw new Error("期望获得"+look+",但是获得"+s);
		}
	}
	
	void move(){
		if(index<=tokens.size()-1){
			look=tokens.get(index++);
		}
	}
	
	void statement(){
		this.index=0;
		String id=tokens.get(index++);
		move();
		match("=");
		double val=expression();
		idMap.put(id, val);
	}
	
	private void resetResource(){
		this.index=0;
		this.tokens.clear();
	}

	private double expression() {
		double val=term();
		while(look.equals("+")||look.equals("-")){
			if(look.equals("+")){
				move();
				val+=term();
			}
			else if(look.equals("-")){
				move();
				val-=term();
			}
		}
		return val;
	}

	private double term() {
		double val=factor();
		//Integer v1=null;
		//Integer v2=null;
		while(look.equals("*")||look.equals("/")){
			if(look.equals("*")){
				move();
				val*=factor();
			}
			else if(look.equals("/")){
				move();
				/**
				 * 练习27.在C++中，如果对两个整数应用除法运算符/，则返回一个整数，例如11/5返回值是2。修改当前程序使之运行结果与C++保持一致
				 */
				/*v1=this.isInteger(String.valueOf(val));
				if(v1!=null){
					
					double v=factor();
					v2=this.isInteger(String.valueOf(v));
					if(v2!=null){
						//两个操作数都是整数
						v1/=v2;
					}else{
						val/=v;
					}
				}else{
					//是浮点数
					val/=factor();
				}*/
				val/=factor();
			}
		}
		
		/*if(v1!=null && v2!=null){
			return (int)v1;
		}*/
		return val;
	}
	
	/**
	 * 2^3^2=512;
	 * @return
	 */
	/*double exponent(){
		double val=factor();
		double rs=0;
		while(look.equals("^")){
			move();
			double v=expression();
			rs=Math.pow(val, v);
		}
		return rs;
	}*/
	
	private double factor() {
		double val=0;
		if(look.equals("-")){
			move();
			val=factor()*(-1);
		}else if(look.equals("(")){
			move();
			val = expression();
			match(")");
		}else if(isNumeric(look)){
			val=Double.valueOf(look);
			move();
		}else{
			//throw new Error("未知的符号 "+look);
			Double idVal=idMap.get(look);
			if(idVal!=null){
				move();
				return idVal;
			}else{
				throw new Error("未知的变量 "+look);
			}
		}
		return val;
	}
	
	private void printValOfVar(String key){
		Double val=idMap.get(key.trim());
		if(val!=null){
			System.out.println(key.trim()+"="+val);
		}else{
			System.out.println("没有名为"+key.trim()+"的变量！");
		}
	}
	
	private void status(){
		Set<String> sets = idMap.keySet();
		Iterator<String> it=sets.iterator();
		while(it.hasNext()){
			String key=it.next().toString();
			System.out.println(key+"="+idMap.get(key));
		}
	}
	
	private boolean isNumeric(String str){
		boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
        return isInt || isDouble;
	}
	
	private Integer isInteger(String str){
		String s=str.substring(str.indexOf(".")+1, str.length());
		if(s.equals("0")){
			return Integer.valueOf(str.substring(0, str.indexOf(".")));
		}else{
			return null;
		}
	}
	
	public static void main(String[] args) {
		Interpreter interpreter=new Interpreter();
		System.out.println("The program processes statements of the following format:\n"+
				"\t<id>=<expr>;\n\tprint <id>;\n\tstatus;\n\tend;\n\n");
		Scanner in=new Scanner(System.in);
		String line="";
		while(!(line=in.nextLine()).equals("end;")){
			interpreter.resetResource();
			if(line.startsWith("print")){
				interpreter.printValOfVar(line.substring(5,line.length()-1));
			}else if(line.equals("status;")){
				interpreter.status();
			}else{
				interpreter.lexicalAnalysis(line);
				interpreter.statement();
			}
		}
		in.close();
		System.out.println("Bye...");
	}

}
