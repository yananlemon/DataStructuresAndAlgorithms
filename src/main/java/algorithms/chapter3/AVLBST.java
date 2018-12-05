package algorithms.chapter3;

import java.util.Comparator;

/**
 * 基于AVL树实现的符号表
 * @author andy
 * @param <Key>
 * @param <Value>
 */
public class AVLBST<Key extends Comparator<Key>,Value> {

	private Node root;
	
	private class Node{
		Key key;
		Value val;
		Node left,right;
		int height;
	}
	
	public Value get(Key key){
		return null;
	}
	
	public void put(Key key,Value val){
		
	}
	
	public void delete(Key key){
		
	}
	
	public void deleteMin(){
		
	}
	
	public void deleteMax(){
		
	}
}
