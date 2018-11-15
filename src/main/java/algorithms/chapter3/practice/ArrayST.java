package algorithms.chapter3.practice;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基于无序数组的符号表实现
 * @author andy
 *
 * @param <Key>
 * @param <Value>
 */
public class ArrayST<Key,Value> {

	private static final int DEFAULT_SIZE = 10;
	Node<Key,Value>[] array;
	private int size;

	public ArrayST(int capacity) {
		this.array = new Node[DEFAULT_SIZE];
	}


	public ArrayST() {
		//this.array = (Node<Key,Value>[])new Object[DEFAULT_SIZE];
		this.array = new Node[10];
	}


	private class Node<Key,Value>{
		Key key;
		Value value;
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
		
	}
	
	public Iterable<Key> keys(){
		ArrayList<Key> keys = new ArrayList<Key>();
		for( int i = 0; i < this.array.length; i++)
			if( array[i] != null )
				keys.add(array[i].key);
		return keys; 
	}
	
	public Value get(Key key){
		for( int i = 0; i < this.array.length; i++)
			if( array[i] != null && key.equals(array[i].key) )
				return array[i].value;
		return null;
	}
	
	public void put(Key key,Value val){
		if(size() == array.length-1){
			int newLen=(array.length-1) << 1;
			array=Arrays.copyOf(array, newLen);
		}
		for( int i = 0; i < this.array.length; i++)
			if( array[i] != null && key.equals(array[i].key) ){
				array[i].value = val;
				return;
			}
		this.array[size++] = new Node<Key, Value>(key, val);
	}
	
	public void delete(Key key){
		for( int i = 0; i < this.array.length; i++)
			if(key.equals(array[i].key)){
				array[i]= null;
				size--;
				return;
			}
	}
	
	public int size(){
		return size;
	}
	
	public static void main(String[] args) {
		String[] words = {"A","bad","beginning","makes","a","bad","ending"};
		ArrayST<String, Integer> st = new ArrayST<String, Integer>();
		for( String word : words ){
			if( st.get(word) == null)
				st.put(word, 1);
			else
				st.put(word, st.get(word)+1);
		}
		System.out.print("单词频率如下:");
		for(String word : st.keys())
			System.out.println( word + ":" + st.get(word));
		System.out.println(st.size());
	}
}
