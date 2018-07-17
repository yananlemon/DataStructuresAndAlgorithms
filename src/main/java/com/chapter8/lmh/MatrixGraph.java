package com.chapter8.lmh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>基于邻接矩阵实现的带权有向图</p>
 * @author andy
 * @date 2018/7/17
 * @see com.chapter8.lmh.AbstractGraph
 */
public class MatrixGraph extends AbstractGraph {

	private static final String NEWLINE = System.getProperty("line.separator");	
	private int e;																	// 边个数
	private int v;																	// 顶点个数
	private double[][] martrix;														// 矩阵
	public static final double MAX_WEIGHT = 9999999999.0;							// 最大权重
	
	@Override
	public void create(InputStream in) {
		byte[] data = new byte[1024];
		int i = -1;
		StringBuilder sb = new StringBuilder();
		try{
			while((i=in.read(data,0,data.length)) != -1){
				String str = new String(data,0,i,"UTF-8");
				sb.append(str);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] info = sb.toString().split("\r\n");
		this.v = Integer.parseInt(info[0]);
		this.e = Integer.parseInt(info[1]);
		this.martrix = new double[this.v][this.v];
		int index = 2;
		for (int j = 0; j < this.e; j++) {
			String[] e = info[index++].split(" ");
			addEdge(Integer.parseInt(e[0])-1, Integer.parseInt(e[1])-1, Double.parseDouble(e[2]));
			
		}
	}
	
	public void addEdge(int v,int w,double weight){
		validateVertex(v);
		validateVertex(w);
		this.martrix[v][w] = weight;
	}

	public void validateVertex(int v2) {
		if(v2 <0 || v2 >= this.v)
			throw new IllegalArgumentException("顶点 " + v + " 的范围不在 0 和 " + (v-1)+" 之间！");
	}
	
	@Override
	public String toString() {
		 StringBuilder s = new StringBuilder();
	        s.append(v + " 个顶点, " + e + " 条边 " + NEWLINE);
	        for (int i = 0; i < v; i++) {
	            s.append((i+1) + ": ");
	            for (int w = 0; w < martrix[i].length; w++) {
					if(martrix[i][w] != 0)
						s.append((w+1) + " "+martrix[i][w]+", ");
				}
	            s.append(NEWLINE);
	        }
	        return s.toString();
	}
	
	public static void main(String[] args) {
		try {
			AbstractGraph g = new MatrixGraph();
			g.create(new FileInputStream(new File("script\\Graph\\DirectedWeightGraph.txt")));
			System.out.println(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getV() {
		return this.v;
	}

	public double getWeight(int start, int i) {
		validateVertex(start);
		validateVertex(i);
		return martrix[start][i] == 0 ? 99999999 : martrix[start][i];
	}
	
}
