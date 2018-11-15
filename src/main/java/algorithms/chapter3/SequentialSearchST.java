package algorithms.chapter3;

import java.util.ArrayList;


/**
 * 基于链表的顺序查找
 * @author andy
 *
 * @param <Key>	符号表中的键
 * @param <Value>	键所对应的值
 */
public class SequentialSearchST<Key,Value>{

	private Node<Key,Value> first;
	
	private int size;
	
	private class Node<Key,Value>{
		Key key;
		Value val;
		Node<Key,Value> next;
		public Node(Key key, Value val,Node<Key, Value> next) {
			super();
			this.key = key;
			this.val = val;
			this.next = next;
		}
		
	}
	
	public Value get(Key key){
		Node<Key,Value> temp = first;
		while( temp != null){
			if( temp.key.equals(key))
				return temp.val;
			else
				temp = temp.next;
		}
		return null;
	}
	
	public void put(Key key,Value val){
		Node<Key,Value> temp = first;
		while( temp != null){
			if( temp.key.equals(key)){
				temp.val = val;
				return;
			}
			else
				temp = temp.next;
		}
		first = new Node<Key, Value>(key, val, first);
		size++;
	}
	
	public Iterable<Key> keys(){
		ArrayList<Key> keys = new ArrayList<Key>();
		Node<Key,Value> temp = first;
		while( temp != null){
			keys.add(temp.key);
			temp = temp.next;
		}
		return keys; 
	}
	
	public void delete(Key key){
		if( get(key) == null)
			throw new Error("符号表中不存在该键:"+key.toString());
		else{
			Node<Key,Value> temp = first;
			while( temp != null){
				if( temp.key.equals(key)){
					temp = temp.next;
					size --;
					return;
				}
				else
					temp = temp.next;
			}
		}
	}
	
	public int size(){
		return size;
	}

}
