package com.chapter8.thirdsection;

import java.util.Stack;

import com.chapter8.firstsection.EdgeNode;
import com.chapter8.firstsection.VertexNode;

/**
 * <p>深度优先搜索</p>
 * @author yanan
 * @date 2018年5月23日 
 */
public class DepthFirstSearch {

	// 顶点的访问标记数组
	private boolean[] visited;
	
	// 顶点的先深编号
	private int[] dfn;
	
	private int count;
	
	public DepthFirstSearch(com.chapter8.firstsection.AdjGraph g) {
		visited = new boolean[g.getN()];
		dfn = new int[g.getN()];
	}
	
	/**
	 * 深度优先搜索递归实现
	 * @param g
	 */
	public void dfsTraverse(com.chapter8.firstsection.AdjGraph g) {
		count = 1;
		
		// 遍历图中每个顶点
		for (int i = 0; i < g.getN(); i++) {
			if(!visited[i])
				//从顶点Vi出发进行的一次遍历
				dfs(g,i);
		}
	}

	private void dfs(com.chapter8.firstsection.AdjGraph g, int i) {
		
		// 访问顶点v并设置v已经访问过
		System.out.println(g.getVexList()[i].getVertex());
		visited[i] = true;
		dfn[i] = count++;
		
		// 获取顶点v的第一个邻接点
		EdgeNode edgeNode = g.getVexList()[i].getFirstEdge();
		while(edgeNode != null) {
			
			// 如果该顶点没有被访问过，则递归地进行深度优先搜索
			if(!visited[edgeNode.getIndex()]){
				dfs(g, edgeNode.getIndex());
			}
			
			// 否则则获取顶点V的下一个邻接点
			edgeNode = edgeNode.getNext();
		}
		
	}
	
	public EdgeNode getUnVisitedNextEdgeNode(EdgeNode edgeNode){
		
		while(edgeNode !=null && visited[edgeNode.getIndex()]){
			edgeNode = edgeNode.getNext();
		}
		return edgeNode == null ? null : edgeNode;
	}
	
	/**
	 * 深度优先搜索非递归实现
	 * @param g
	 */
	public void dfsTraverseWithoutRecursion(com.chapter8.firstsection.AdjGraph g){
		Stack<VertexNode> stack = new Stack<VertexNode>();
		// 图可能是非连通的,故需要遍历图中每个顶点
		for (int i = 0; i < g.getN(); i++) {
			if(!visited[i]){
				VertexNode vertexNode = g.getVexList()[i];
				System.out.println(vertexNode.getVertex());
				stack.push(vertexNode);
				visited[i] = true;
				while(!stack.isEmpty()){
					
					//获取当前顶点未被访问过的顶点
					EdgeNode edgeNode = getUnVisitedNextEdgeNode(vertexNode.getFirstEdge());
					if(edgeNode !=null && !visited[edgeNode.getIndex()]){
						vertexNode = g.getVexList()[edgeNode.getIndex()];
						System.out.println(vertexNode.getVertex());
						stack.push(vertexNode);
						visited[edgeNode.getIndex()] = true;
					}else{
						vertexNode = stack.pop();
					}
				}
			}

		}
	}
	

}
