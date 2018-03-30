package com.chapter4.text;

/**
 * <p>AbstractStack</p>  
 * <p>栈的抽象类 </p>  
 * @author yanan  
 * @date 2018年3月30日
 */
public abstract class AbstractStack<T> {
	
	public abstract void clear();
	public abstract boolean isEmpty();
	public abstract int size();
	public abstract void push(T value);
	public abstract T pop();
	public abstract T topEl();
	public abstract String toString();
}
