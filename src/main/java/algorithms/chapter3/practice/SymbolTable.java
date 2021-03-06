package algorithms.chapter3.practice;

/**
 * 符号表接口
 * @author andy
 *
 */
public interface SymbolTable<Key,Value> {

	public void put(Key key,Value val);
	
	public Value get(Key key);
	
	public void delete(Key key);
	
	public Iterable<Key> keys();
	
	public int size();
	
	public boolean contains(Key key);
	
}
