package algorithms.chapter3;
public class Node<Key extends Comparable<Key>,Value> implements Comparable<Node<Key,Value>>{
	Key key;
	Value value;
	public Node(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	public int compareTo(Node<Key, Value> o) {
		return this.key.compareTo(o.key);
	}

}