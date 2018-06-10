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

	@Override
	public String toString() {
		return "Edge [v=" + v + "->w=" + w + ":weight=" + weight + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + v;
		result = prime * result + w;
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (v != other.v)
			return false;
		if (w != other.w)
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	
	
	
	
}
