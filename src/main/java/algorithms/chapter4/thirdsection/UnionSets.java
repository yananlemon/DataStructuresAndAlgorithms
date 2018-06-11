package algorithms.chapter4.thirdsection;

public class UnionSets {

	private int[] array;

	public UnionSets(int size) {
		this.array = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
	}
	
	public int find(int x) {
		if(array[x] == x)
			return x;
		else
			return find(array[x]);
	}
	
	public void union(int x,int y) {
		array[x] = y;
	}
	
	
}
