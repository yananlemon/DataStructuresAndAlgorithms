package algorithms.chapter4.firstsection;

import com.chapter4.text.QueueV;
import com.chapter4.text.StackL;

public class BreadFirstSearch {

	private boolean marked[];
	
	private int[] edgeTo;//索引代表图中顶点，值代表直接到达该顶点的顶点的索引
	private StackL<Integer> stack ;
	
	public BreadFirstSearch(Graph g,int start,int end) {
		marked = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		bfs(g, start,end);
	}


	private void bfs(Graph g, int start,int end) {
		QueueV<Integer> queue = new QueueV<Integer>();
		queue.enqueue(start);
		marked[start] = true;
		while(!queue.isEmpty()){
			int v = (Integer) queue.dequeue();
			if(v == end){
				stack= new StackL<Integer>();
				for(int w = end; w != start; w = edgeTo[w]){
					stack.push(w);
				}
				stack.push(start);
				//break;
			}
			for(int w : g.adj(v)){
				if(!marked[w]){
					marked[w] = true;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
			}
		}
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
		Graph g = new Graph(12);
		g.addEdge(2,3);
		g.addEdge(0,6);
		g.addEdge(3,6);
		g.addEdge(10,3);
		g.addEdge(2,0);
		g.addEdge(6,2);
		g.addEdge(5,2);
		g.addEdge(5,10);
		g.addEdge(5,0);
		int start = 0;
		int end = 3;
		BreadFirstSearch s = new BreadFirstSearch(g, start,end);
		if(!s.stack.isEmpty()){
			System.out.printf("%d到%d的最短路径如下:\n",start,end);
			while(!s.stack.isEmpty()){
				int v = s.stack.pop();
				if(v == start)
					System.out.print(start);
				else
					System.out.printf("->%d",v);
			}
			System.out.println();
		}else{
			System.out.printf("%d到%d没有路径如下:\n",start,end);
		}
	}

}

