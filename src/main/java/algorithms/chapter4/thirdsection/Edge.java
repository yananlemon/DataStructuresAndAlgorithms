package algorithms.chapter4.thirdsection;

public class Edge {
	
	/**一个顶点**/
	private final int v;
	
	/**另一个顶点**/
	private final int w;
	
	/**边的权重**/
	private double weight;

	/**
	 * 根据给定的一个顶点{@code v} 和另一个顶点{@code w},以及{@code weight}创建边对象
	 * @param v
	 * @param w
	 * @param weight
	 */
	public Edge(int v, int w, double weight) {
		super();
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int getOther(int v) throws Exception{
		if(v == this.v)
			return this.w;
		if(v == this.w)
			return this.v;
		else
			throw new Exception("getOther occur!");
	}

	public int getV() {
		return v;
	}

	public int getW() {
		return w;
	}

	public double getWeight() {
		return weight;
	}
	
}
