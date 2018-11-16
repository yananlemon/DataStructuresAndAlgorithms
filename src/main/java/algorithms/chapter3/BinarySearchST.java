package algorithms.chapter3;

import java.util.ArrayList;

import algorithms.chapter3.practice.SymbolTable;

/**
 * 基于有序数组的符号表实现
 * @author andy
 *
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> implements SymbolTable<Key, Value> {

	private static final int DEFAULT_SIZE = 10;
	Node<Key,Value>[] array;
	private int size;
	
	public BinarySearchST() {
		this.array = new Node[DEFAULT_SIZE];
	}
	
	private class Node<Key,Value>{
		Key key;
		Value value;
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
		
	}
	
	public void put(Key key, Value val) {
		if( isEmpty()){
			array[size++] = new Node<Key, Value>(key, val);
			return;
		}
		int index = recursionIndex(array, key, 0, size-1);
		if(index < size() && array[index].key.compareTo(key) == 0){
			array[index].value = val;
			return;
		}
		// 添加新的元素
		
		for( int i = size; i > index; i--){
			array[i] = array[i-1];
		}
		array[index] = new Node<Key, Value>(key, val);
		size++;
		
	}
	
	private boolean isEmpty() {
		return size() == 0;
	}

	private int recursionIndex(Node<Key,Value>[] array,Key key,int begin,int end){
		if( begin > end ){
			return begin;
		}
		int middle = (begin + end)/2;
		
		if( key.compareTo(this.array[middle].key) > 0){
			return recursionIndex(array, key, middle, end);
		}
		if( key.compareTo(this.array[middle].key) < 0){
			return recursionIndex(array, key, begin, middle);
		}
		return middle;
	}

	public Value get(Key key) {
		return null;
	}

	public void delete(Key key) {
		
	}

	public Iterable<Key> keys() {
		ArrayList<Key> keys = new ArrayList<Key>();
		for( int i = 0; i < this.array.length; i++)
			if( array[i] != null )
				keys.add(array[i].key);
		return keys; 
	}

	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
		st.put("a", 1);
		st.put("b", 1);
		st.put("c", 1);
		st.put("d", 1);
		System.out.println(st.size());
	}

}
