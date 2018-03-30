package com.chapter4.text;

import java.util.Arrays;

/**
 * <p>MyStack</p>  
 * <p>基于数组实现的栈</p>  
 * @author yanan  
 * @date 2018年3月27日
 */
public class MyStack<T> extends AbstractStack<T> {

	private int size;
	private int defaultCapacity=10;
	private T[] data;
	
	@SuppressWarnings("unchecked")
	public MyStack(){
		this.data= (T[]) new Object[defaultCapacity];
	}
	
	public void clear(){
		for (int i = 0; i < size(); i++){
			data[i] = null;
		}
        size = 0;
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
	public void push(T value){
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
	public T pop(){
		int index=--size;
		T val=data[index];
		data[index]=null;
		return val;
	}
	
	/**
	 * 获取栈顶元素但不删除
	 * @return T
	 */
	public T topEl(){
		T val=data[size-1];
		return val;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		for (int i = size()-1; i>=0; i--) {
			sb.append(data[i]).append(",");
		}
		sb.deleteCharAt(sb.length()-1).append("]");
		return sb.toString();
	}
	
	
}
