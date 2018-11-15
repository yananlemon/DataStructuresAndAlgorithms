package algorithms.chapter3.practice;


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
	
	private class Node<Key,Value>{
		Key key;
		Value val;
		Node<Key,Value> prev;
		Node<Key,Value> next;
		public Node(Key key, Value val,Node<Key, Value> prev,Node<Key, Value> next) {
			super();
			this.key = key;
			this.val = val;
			this.next = next;
		}
		
	}
	
	
	public void put(Key key, Value val) {
		if( tail == null)
			tail = new Node<Key, Value>(key, val,null, null); 
		else{
			// 如果要添加的元素比链表尾部元素大，那么直接更新链表尾部元素
			if( key.compareTo(tail.key) > 0){
				Node<Key,Value> newNode = new Node<Key,Value>(key, val, tail,null);
				newNode.prev = tail;
			}
			// 如果要添加的元素等于链表尾部元素，那么直接更新链表尾部元素的值
			else if( key.compareTo(tail.key) == 0 ){
				tail.val = val;
			}else{
				Node<Key,Value> tempNode = tail.prev;
				while( key.compareTo(tempNode.key) < 0 ){
					tempNode = tempNode.prev;
				}
				
			}
			
		}
		size++;
	}
	
	private Node<Key,Value> getNode(Node<Key,Value> node,Key key){
		if( key.compareTo(node.key) < 0 ){
			return getNode(node.prev, key);
		}
		else if( key.compareTo(node.key) == 0 ){
			return node;
		}
		else if( key.compareTo(node.key) > 0){
			if(node.prev != null && key.compareTo(node.prev.key) > 0 )
				return node.prev;
			if( node.next == null)
				return node;
			return getNode(node.next, key);
		}
		return null;
	}

	public Key get(Key key) {
		return null;
	}

	public void delete(Key key) {
		
	}

	public Iterable<Key> keys() {
		return null;
	}

	public int size() {
		return 0;
	}

	public static void main(String[] args) {
		SymbolTable<String, Integer> st = new OrderedSequentialSearchST<String, Integer>();
		st.put("a", 1);
		st.put("b", 1);
		st.put("c", 1);
		st.put("z", 1);
		st.put("d", 1);
		System.out.println(st);
	}
}
