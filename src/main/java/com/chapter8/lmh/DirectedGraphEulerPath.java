package com.chapter8.lmh;


import algorithms.chapter1.Stack;

import com.chapter4.text.MyQueue;

/**
 * <p>DirectedGraphEulerPath</p>  
 * <p>有向图欧拉路径和欧拉回路</p>  
 * @author yanan  
 * @date 2018年7月12日
 */
public class DirectedGraphEulerPath {

	private DirectedGraph graph;
	private Stack<Integer> stack = new Stack<Integer>();
	private MyQueue<Integer> queue = new MyQueue<Integer>();
	public DirectedGraphEulerPath(DirectedGraph graph) {
		this.graph = graph;
	}
	
	/**
	 * 
	 * 获取{@code graph}欧拉路径或者欧拉回路,如果存在则返回构成该路径的顶点集合。
	 * @return Stack
	 */
	public Stack<Integer> getEulerPath() {
		// 1.首先判断图是否是连通图
		DepthFirstSearch dfs = new DepthFirstSearch(graph);
		if(!dfs.checkWhetherConnected(graph))
			throw new Error("不是连通图！");
		
		// 2.图中顶点的入度和出度相等，最多存在两个这样的顶点：其中一个的出度=入度+1；另一个的入度=出度+1
		int count = 0;
		int start = 0;
		int end   = 0;
		int a = 0,b = 0;
		for (int i = 0; i < graph.getV(); i++) {
			int input  = graph.inputDegree(i);
			int output = graph.outputDegree(i);
			if(input != output){
				count++;
				if(input + 1 == output)
					a++;
				if(output + 1 == input)
					b++;
			}
			
		}
		if(count == 0) {
			System.out.println("存在欧拉回路：");
			hierholzer(0);
		}else if(a == 1 && b == 1){
			hierholzer(start);
			if(queue.firstEl() == end) {
				System.out.println("存在欧拉路径：");
				while(!queue.isEmpty()) {
					stack.push(queue.dequeue());
				}
				return stack;
			}else {
				throw new Error("该图不存在欧拉路径！");
			}
			
		}
		else {
			throw new Error("该图不存在欧拉路径或者欧拉回路！");
		}
		return stack;
	}
	
	
	/**
	 * 递归获取欧拉路径
	 *	开始递归函数Hierholzer(x):
	 *	　　循环寻找与x相连的边(x,u):
	 *	　　　　删除(x,u)
	 *	　　　　删除(u,x)
	 *	　　　　Hierholzer(u);
	 *	　　将x插入答案队列之中 
	 * @param x
	 */
	private void hierholzer(int x) {
		for (int i : graph.adj(x)) {
			graph.deleteEdge(x, i);
			hierholzer(i);
		}
		queue.enqueue(x);
	}
	
	public static void main(String[] args) {
		/*************************测试欧拉路径*******************************/
		DirectedGraph dGraph = new DirectedGraph(6);
		dGraph.addEdge(0, 1);
		dGraph.addEdge(1, 4);
		dGraph.addEdge(2, 3);
		dGraph.addEdge(3, 1);
		dGraph.addEdge(3, 4);//注释掉这行，该有向图则不存在欧拉路径
		dGraph.addEdge(4, 0);
		dGraph.addEdge(4, 5);
		dGraph.addEdge(5, 2);
		System.out.println(dGraph);
		DirectedGraphEulerPath euler = new DirectedGraphEulerPath(dGraph);
		Stack<Integer> path = euler.getEulerPath();
		while(!path.isEmpty()) {
			System.out.print(path.pop() + ",");
		}
	}

}

class DepthFirstSearch {
	
	private boolean marked[];
	
	private int count;
	
	
	public DepthFirstSearch(DirectedGraph g) {
		super();
		marked = new boolean[g.getV()];
	}
	
	public void dfs(DirectedGraph g,int v){
		marked[v] = true;
		count++;
		for (int w: g.adj(v)) {
			if(!marked[w]){
				dfs(g,w);
			}
		}
	}
	
	public boolean checkWhetherConnected(DirectedGraph g){
		dfs(g, 0);
		if(count == g.getV())
			return true;
		return false;
	}


}
