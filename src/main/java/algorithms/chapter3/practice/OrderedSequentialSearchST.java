package algorithms.chapter3.practice;

import java.util.ArrayList;

/**
 * 符号表之有序链表实现
 * @author andy
 * @param <Key>
 * @param <Value>
 */
public class OrderedSequentialSearchST<Key extends Comparable<Key>,Value>  implements SymbolTable<Key, Value> {

	private Node<Key,Value> tail;
	
	private int size;
	
	public OrderedSequentialSearchST(){
		
	}
	
	@SuppressWarnings("hiding")
	private class Node<Key,Value>{
		Key key;
		Value val;
		Node<Key,Value> prev;
		Node<Key,Value> next;
		public Node(Key key, Value val,Node<Key, Value> prev,Node<Key, Value> next) {
			super();
			this.key = key;
			this.val = val;
		}
		
	}
	
	
	public void put(Key key, Value val) {
		if( tail == null) {
			tail = new Node<Key, Value>(key, val,null,null); 
			size++;
		}
		else{
			// 如果要添加的元素比链表尾部元素大，那么直接更新链表尾部元素
			if( key.compareTo(tail.key) > 0){
				Node<Key,Value> newNode = new Node<Key,Value>(key, val, null,null);
				newNode.prev = tail;
				tail.next = newNode;
				tail = newNode;
				size++;
			}
			// 如果要添加的元素等于链表尾部元素，那么直接更新链表尾部元素的值
			else if( key.compareTo(tail.key) == 0 ){
				tail.val = val;
			}else{
				Node<Key,Value> tempNode = tail;
				while( true ){
					if(key.compareTo(tempNode.key) == 0){
						tempNode.val = val;
						break;
					}
					else if( key.compareTo(tempNode.key) < 0 ) {
						tempNode = tempNode.prev;
					}
					else if(key.compareTo(tempNode.key) > 0 ) {
						Node<Key,Value> newNode = new Node<Key, Value>(key, val,null,null);
						newNode.next=tempNode.next;
						tempNode.next.prev=newNode;
						tempNode.next=newNode;
						newNode.prev=tempNode;
						size++;
						break;
					}
						
				}
				
			}
			
		}
	}

	public Value get(Key key) {
		if( this.size() == 0)
			return null;
		Node<Key,Value> node = tail;
		for(int i = 0 ; i < this.size() && node != null;i++)
			if(key.compareTo(node.key) == 0)
				return node.val;
			else
				node = node.prev;
		return null;
	}

	public void delete(Key key) {
		for( Node<Key,Value> node = tail;tail !=null && node !=null;node = node.prev)
			if(key.compareTo(node.key) == 0) {
				if( node.prev == null) {
					node = node.next;
					node.prev = null;
				}else if(node.next == null) {
					node = node.prev;
					node.next = null;
				}else {
					node.prev.next = node.next;
					node.next.prev = node.prev;
				}
				size--;
				break;
			}
			
	}

	public Iterable<Key> keys() {
		ArrayList<Key> keys = new ArrayList<Key>();
		for( Node<Key,Value> node = tail; tail !=null && node !=null;node = node.prev)
				keys.add(node.key);
		return keys; 
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		SymbolTable<String, Integer> st = new OrderedSequentialSearchST<String, Integer>();
		st.put("a", 1);
		st.put("c", 3);
		st.put("b", 2);
		for(String word : st.keys())
			System.out.println( word + ":" + st.get(word));
		System.out.println(st.size());
	}
}
