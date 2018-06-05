package algorithms.chapter4.firstsection;

import com.chapter4.text.QueueV;
import com.chapter4.text.StackL;

public class BreadFirstPaths {

	private boolean marked[];
	
	private int start;//起点
	
	private int[] edgeTo;
	
	
	
	public BreadFirstPaths(Graph g,int start) {
		marked = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		this.start = start;
		bfs(g, start);
	}



	private void bfs(Graph g, int start) {
		QueueV<Integer> queue = new QueueV<Integer>();
		marked[start] = true;//标记起点
		queue.enqueue(start);//将它加入队列
		while(!queue.isEmpty()){
			int v = (Integer) queue.dequeue();//从队列中删除下一顶点
			
			//遍历当前顶点的所有邻接点
			for(int w : g.adj(v)){
				if(!marked[w]){
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
			}
			
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public StackL<Integer> pathTo(int v){
		if(!hasPathTo(v))
			return null;
		StackL<Integer> path = new StackL<Integer>();
		for(int x = v; x != start; x = edgeTo[x]){
			path.push(x);
		}
		path.push(start);
		return path;
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
		/*Graph g = new Graph(8);
		g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(3,4);
		g.addEdge(4,5);
		g.addEdge(5,6);
		g.addEdge(6,7);
		System.out.println(g);*/
		Graph g = new Graph(7);
		g.addEdge(0,1);
		g.addEdge(0,4);
		g.addEdge(1,5);
		g.addEdge(1,2);
		g.addEdge(5,4);
		g.addEdge(4,6);
		g.addEdge(4,3);
		g.addEdge(2,3);
		int start = 0;
		System.out.println(g);
		BreadFirstPaths s = new BreadFirstPaths(g, start);
		for (int v = 0; v < g.getV(); v++) {
			System.out.print(start + "  到 "+v+":");
			if(s.hasPathTo(v)){
				StackL<Integer> paths = s.pathTo(v);
				while(!paths.isEmpty()){
					int x = paths.pop();
					if(x == start)
						System.out.print(x);
					else
						System.out.print("->"+x);
				}
			}
			System.out.println();
		}
	}

}
