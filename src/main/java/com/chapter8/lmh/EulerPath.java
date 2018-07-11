package com.chapter8.lmh;


import com.chapter4.text.MyQueue;

import algorithms.chapter1.Stack;
import algorithms.chapter4.firstsection.DepthFirstSearch;
import algorithms.chapter4.firstsection.Graph;

/**
 * <p>EulerPath</p>  
 * <p>无向图欧拉路径和欧拉回路</p>  
 * @author yanan  
 * @date 2018年7月12日
 */
public class EulerPath {

	private Graph graph;
	private Stack<Integer> stack = new Stack<Integer>();
	private MyQueue<Integer> queue = new MyQueue<Integer>();
	public EulerPath(Graph graph) {
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
		
		// 2.图中顶点的度是奇数的个数是2
		int count = 0;
		int start = 0;
		int end   = 0;
		for (int i = 0; i < graph.getV(); i++) {
			int degree = graph.degree(i);
			if(degree % 2 != 0) {
				count++;
				start = i;
			}
		}
		
		for (int i = 0; i < graph.getV(); i++) {
			int degree = graph.degree(i);
			if(degree % 2 != 0 && i != start) {
				end = i;
			}
		}
		if(count == 0) {
			System.out.println("存在欧拉回路：");
			hierholzer(0);
		}else if(count == 2){
			hierholzer(start);
			/*while(!stack.isEmpty()) {
				System.out.print(stack.pop() + ",");
			}*/
			
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
	 * 
	 * 获取{@code graph}欧拉回路,如果存在则返回构成欧拉回路的顶点集合。
	 * @return Stack
	 *//*
	public Stack<Integer> getEulerCircuit(int start) {
		// 1.首先判断图是否是连通图
		DepthFirstSearch dfs = new DepthFirstSearch(graph);
		if(!dfs.checkWhetherConnected(graph))
			throw new Error("不是连通图！");
		
		// 2.图中顶点的度必须全部是偶数
		for (int i = 0; i < graph.getV(); i++) {
			int degree = graph.degree(i);
			if(degree % 2 != 0) {
				throw new Error("该图没有欧拉回路！");
			}
		}
		hierholzer(start);
		if(stack.peek() == start)
			return stack;
		else
			throw new Error("该图不存在欧拉回路！");
	}*/
	
	/*开始递归函数Hierholzer(x):
		　　循环寻找与x相连的边(x,u):
		　　　　删除(x,u)
		　　　　删除(u,x)
		　　　　Hierholzer(u);
		　　将x插入答案队列之中*/
	private void hierholzer(int x) {
		for (int i : graph.adj(x)) {
			graph.deleteEdge(x, i);
			hierholzer(i);
		}
		queue.enqueue(x);
	}
	
	public static void main(String[] args) {
		/*************************测试欧拉回路*******************************/
		//对应第十五题图片图a
		/*Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(4, 3);
		graph.addEdge(3, 0);*/
		
		//对应第十五题图片图b&&图c
		/*Graph graph = new Graph(4);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(1, 3);
		graph.addEdge(0, 2);
		EulerPath euler = new EulerPath(graph);
		Stack<Integer> path = euler.getEulerPath();
*/		
		Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 2);
		//graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		System.out.println(graph);
		EulerPath euler = new EulerPath(graph);
		Stack<Integer> path = euler.getEulerPath();
		while(!path.isEmpty()) {
			System.out.print(path.pop() + ",");
		}
	}

}
