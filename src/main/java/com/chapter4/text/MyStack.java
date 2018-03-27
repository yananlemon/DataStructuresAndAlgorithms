package com.chapter4.text;

import java.util.Arrays;


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
			Object[] newArray=new Object[newLen];
			newArray=Arrays.copyOf(data, newLen);
			data=newArray;
		}
		data[size++]=value;
	}
	
	/**
	 * 获取并删除栈顶元素
	 * @return T
	 */
	public Object pop(){
		Object val=data[--size];
		return val;
	}
	
	/**
	 * 获取栈顶元素但不删除
	 * @return T
	 */
	public Object topEl(){
		Object val=data[size];
		return val;
	}
	
	
}
