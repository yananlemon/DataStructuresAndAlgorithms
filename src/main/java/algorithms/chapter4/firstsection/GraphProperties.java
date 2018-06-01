package algorithms.chapter4.firstsection;

import java.util.ArrayList;

import com.chapter4.text.QueueV;
import com.chapter4.text.StackL;

public class GraphProperties {
	
	private Graph graph;
	
	private int count;
	
	private boolean[] marked;
	
	private int[] edgeTo;
	
	/**
	 * 
	 * @param g
	 * @throws Exception
	 */
	public GraphProperties(Graph g) throws Exception {
		this.graph = g;
		
		// 初始化marked数组
		marked = new boolean[graph.getV()];
		
		// 初始化edgeTo数组
		edgeTo = new int[graph.getV()];
		
		// bfs遍历
		bfs(0);
		
		// 检查Graph是否是连通图
		boolean rs = checkWhetherConnected();
		if(!rs)
			throw new Exception(graph.toString()+"不是连通图！");
		
		
			
	}
	
	/**
	 * 从指定的顶点开始使用bfs遍历Graph
	 * @param v
	 */
	private void bfs(int v){
		
		// 初始化marked数组
		marked = new boolean[graph.getV()];
		
		// 初始化edgeTo数组
		edgeTo = new int[graph.getV()];
		
		QueueV<Integer> queue = new QueueV<Integer>();
		marked[v] = true;
		queue.enqueue(v);
		count++;
		while(!queue.isEmpty()){
			
			// 队列头部元素出队
			int w = (Integer) queue.dequeue();
			// 遍历w顶点的所有邻接顶点
			for(int h : graph.adj(w)){
				if(!marked[h]){
					marked[h] = true;
					queue.enqueue(h);
					count++;
					edgeTo[h] = w;
				}
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public StackL<Integer> pathTo(int v,int start){
		if(!hasPathTo(v))
			return null;
		StackL<Integer> path = new StackL<Integer>();
		for(int x = v; x != start; x = edgeTo[x]){
			path.push(x);
		}
		path.push(start);
		return path;
	}
	
	
	/**
	 * 计算顶点{@code v}的离心率
	 * @param v 顶点
	 * @return 顶点{@code v}的离心率
	 */
	public int eccentricity(int v){
		graph.validateVertex(v);
		bfs(v);
		int max = 0;
		for (int i = 0; i < graph.getV(); i++) {
			int step = 0;
			if(hasPathTo(v)){
				//System.out.printf("从顶点%d到顶点%d:\n",v,i);
				StackL<Integer> paths = pathTo(i,v);
				while(!paths.isEmpty()){
					paths.pop();
					step ++;
				}
			}
			if(max < step)
				max = step;
		}
		return max - 1;
	}
	
	/**
	 * 获取图的直径
	 * @return int 直径
	 */
	public int diameter(){
		int diameter = 0;
		for (int i = 0; i < graph.getV(); i++) {
			int eccen = eccentricity(i);
			if(diameter < eccen)
				diameter = eccen;
		}
		return diameter;
	}
	
	/**
	 * 获取图的半径
	 * @return int 半径
	 */
	public int radius(){
		int radius = 99999999;
		for (int i = 0; i < graph.getV(); i++) {
			int eccen = eccentricity(i);
			if(radius > eccen)
				radius = eccen;
		}
		return radius;
	}
	
	/**
	 * 获取图的中点
	 * @return int 半径
	 */
	public ArrayList<Integer> center(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		int radius = radius();
		for (int i = 0; i < graph.getV(); i++) {
			int eccen = eccentricity(i);
			if(radius == eccen)
				list.add(i);
		}
		return list;
	}
	

	/**
	 * 检查Graph是否是连通图
	 * @return true:是连通图;false:不是连通图
	 */
	private boolean checkWhetherConnected() {
		return graph.getV() == count ? true : false;
	}

	public static void main(String[] args) {
		/*Graph g = new Graph(6);
		g.addEdge(0,5);
		g.addEdge(2,4);
		g.addEdge(2,3);
		g.addEdge(1,2);
		g.addEdge(0,1);
		g.addEdge(3,4);
		g.addEdge(3,5);
		g.addEdge(0,2);*/
		Graph g = new Graph(8);
		g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(3,4);
		g.addEdge(4,5);
		g.addEdge(5,6);
		g.addEdge(6,7);
		//g.addEdge(7,0);
		try {
			GraphProperties gp = new GraphProperties(g);
			for (int i = 0; i < g.getV(); i++) {
				int eccen = gp.eccentricity(i);
				System.out.printf("%d的离心率:%d\n",i,eccen);
			}
			
			int diameter = gp.diameter();
			System.out.printf("直径:%d\n",diameter);
			
			int radius = gp.radius();
			System.out.printf("半径:%d\n",radius);
			
			ArrayList<Integer> centerList = gp.center();
			for(int cen : centerList){
				System.out.printf("图的中点:%d\n",cen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
