package com.chapter4.text;

import java.util.Arrays;

/**
 * <p>MyStack</p>  
 * <p>基于数组实现的栈</p>  
 * @author yanan  
 * @date 2018年3月27日
 */
public class MyStack<E> {

	private int size;
	private int defaultCapacity=10;
	private Object[] data;
	
	public MyStack(){
		this.data= new Object[defaultCapacity];
	}
	
	public void clear(){
		
	}
	
	public boolean isEmpty(){
		return size()==0?true:false;
	}
	
	public int size(){
		return size;
	}
	
	/**
	 * 入栈
	 * @param value
	 */
	public void push(Object value){
		if(size()==data.length-1){
			int newLen=(data.length-1)<<1;
			data=Arrays.copyOf(data, newLen);
		}
		data[size++]=value;
	}
	
	/**
	 * 获取并删除栈顶元素
	 * @return T
	 */
	public Object pop(){
		int index=--size;
		Object val=data[index];
		data[index]=null;
		return val;
	}
	
	/**
	 * 获取栈顶元素但不删除
	 * @return T
	 */
	public Object topEl(){
		Object val=data[size-1];
		return val;
	}
	
	public void print(){
		for(int i = size()-1; i>=0; i--){
			System.out.print(data[i]+",");
		}
	}
	
	
}
