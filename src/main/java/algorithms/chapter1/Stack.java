package algorithms.chapter1;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
	
	private Node<Item> head;
	
	private int size;
	
	public Stack(){
		
	}
	
	/**
	 * 将{@code item}入栈
	 * @param item
	 */
	public void push(Item item){
		Node<Item> newItem = new Node<Item>(item);
		if(head == null){
			head = newItem;
		}else{
			 newItem.next = head;
			 head = newItem;
		}
		size++;
	}
	
	/**
	 * 
	 * 弹出栈顶元素并返回
	 * @return Item
	 */
	public Item pop(){
		Item item = head.val;
		head = head.next;
		size--;
		return item;
	}
	
	/**
	 * 
	 * 返回栈顶元素但不弹出
	 * @return Item
	 */
	public Item peek(){
		return head == null ? null :head.val;
	}
	
	public boolean isEmpty(){
		return size == 0 ? true : false; 
	}
	
	public int size(){
		return size;
	}
	
	private static class Node<Item>{
		Item val;
		Node<Item> next;
		public Node(Item item) {
			this.val = item;
		}
	}
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println(stack.peek());
		for(String s : stack)
			System.out.println(s);
	}


	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{

		private Node<Item> current = head;
		
		public boolean hasNext() {
			return current == null ? false : true;
		}

		public Item next() {
			Item item = current.val;
			current = current.next;
			return item;
		}

		public void remove() {
			
		}
		
	}
}
