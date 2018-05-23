package com.chapter8.thirdsection;

/**
 * <p>AbstractGraph</p>
 * <p>图抽象类</p>
 * @author yanan
 * @date 2018年5月23日 
 */
public abstract class AbstractGraph {
	
	protected int vertexCount;
	
	protected int edgeCount;
	
	protected boolean[] visited;
	
	public abstract AbstractGraph createGraph();
	
	public abstract void dfs(AbstractGraph graph);
	
}
