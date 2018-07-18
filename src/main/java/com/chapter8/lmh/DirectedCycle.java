package com.chapter8.lmh;

import java.util.ArrayList;
import java.util.List;

import com.chapter4.text.StackL;

/**
 * <p>基于DFS寻找有向图中所有环路</p>
 * @author andy
 * @date 2018/7/17
 * @see com.chapter8.lmh.MatrixGraph
 */
public class DirectedCycle {

	private boolean marked[];
	private int[] edgeTo;
	private StackL<Integer> cycle;	// 有向环中的所有顶点（如果存在）
	private boolean[] onStack;		// 递归调用的栈上的所有顶点
	private List<StackL<Integer>> allCycle;
	
	public DirectedCycle(DirectedGraph g){
		onStack  = new boolean[g.getV()];
		edgeTo   = new int[g.getV()];
		marked   = new boolean[g.getV()];
		allCycle = new ArrayList<StackL<Integer>>();
		for(int v = 0; v < g.getV(); v++)
			if(!marked[v])
				dfs(g, v);
	}
	
	public void dfs(DirectedGraph g,int v){
		onStack[v] = true;
		marked[v]  = true;
		
		// 遍历顶点V的邻接点
		for (int w : g.adj(v)) {
			/*if(this.hasCycle()){
				return;
			}else*/ 
				if(!marked[w]){
				edgeTo[w] = v;
				dfs(g,w);
			}else if(onStack[w]){
				cycle = new StackL<Integer>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
				allCycle.add(cycle);
			}
		}
		onStack[v] = false;
	}
	
	private boolean hasCycle() {
		return allCycle.size() > 0;
	}

	public static void main(String[] args) {
		/*DirectedGraph graph = new DirectedGraph(4);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		graph.addEdge(3, 0);//加上这条边则对应图b
		*/	
		
		/*// 对应图c
		DirectedGraph graph = new DirectedGraph(13);
		graph.addEdge(0, 1);
		graph.addEdge(0, 5);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 2);
		graph.addEdge(3, 5);
		graph.addEdge(4, 2);
		graph.addEdge(4, 3);
		graph.addEdge(5, 4);
		graph.addEdge(6, 0);
		graph.addEdge(6, 4);
		graph.addEdge(6, 9);
		graph.addEdge(7, 6);
		graph.addEdge(7, 8);
		graph.addEdge(8, 7);
		graph.addEdge(8, 9);
		graph.addEdge(9, 10);
		graph.addEdge(9, 11);
		graph.addEdge(10, 12);
		graph.addEdge(11, 4);
		graph.addEdge(11, 12);
		graph.addEdge(12, 9);*/
		// 一幅不包含环的有向图
		DirectedGraph graph = new DirectedGraph(4);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		DirectedCycle dc = new DirectedCycle(graph);
		if(!dc.hasCycle()){
			System.out.println("不存在环");
		}else{
			int size = dc.allCycle.size();
			System.out.printf("存在%d条环，顶点如下所示:\n",size);
			System.out.println("=======================");
			for(int i = 0; i < size; i++){
				while(!dc.allCycle.get(i).isEmpty()){
					System.out.println(dc.allCycle.get(i).pop());
				}
				System.out.println("=======================");
			}
			
		}
	}

}

