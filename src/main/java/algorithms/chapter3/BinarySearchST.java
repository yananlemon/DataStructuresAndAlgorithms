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
		// 查找键，找到则更新值，否则创建新的元素
		int index = recursionIndex(array, key, 0, size-1);
		if(index < size() && array[index].key.compareTo(key) == 0){
			array[index].value = val;
			return;
		}
		for( int j = size; j > index; j--){
			array[j] = array[j-1];
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
		int middle = begin + (end - begin)/2;
		if( key.compareTo(this.array[middle].key) > 0)
			return recursionIndex(array, key, middle + 1, end);
		
		else if( key.compareTo(this.array[middle].key) < 0)
			return recursionIndex(array, key, begin, middle - 1);
		else
			return middle;
	}

	public Value get(Key key) {
		int index = recursionIndex(this.array, key, 0, size());
		return array[index].key.compareTo(key) == 0 ? array[index].value : null;
	}

	public void delete(Key key) {
		int index = recursionIndex(this.array, key, 0, size());
		if( array[index].key.compareTo(key) != 0)
			return;
		for( int j = index; j < size; j++){
			array[j] = array[j+1];
		}
		size--;
	}
	
	/**
	 * 返回此符号表中小于或等于{@code key}的最大键。
	 * @param key
	 * @return
	 */
	public Key floor(Key key){
		int index = recursionIndex(this.array, key, 0, size());
		if( array[index].key.compareTo(key) != 0)
			return array[index].key;
		if( index == 0 )
			return null;
		return array[index-1].key;
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
		st.put("w", 1);
		st.put("b", 1);
		st.put("z", 1);
		st.put("a", 1);
		st.delete("b");
		st.delete("w");
		st.delete("z");
		st.delete("a");
		System.out.println(st.size());
	}

}
