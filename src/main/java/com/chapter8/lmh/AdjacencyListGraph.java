package com.chapter8.lmh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.chapter3.text.SingleLinkedList;

/**
 * <p>使用邻接表表示图</p>
 * @author andy
 * @see com.chapter8.AbstractGraph
 * @see com.chapter8.Vertex
 */
public class AdjacencyListGraph extends AbstractGraph {

	private static final String NEWLINE = System.getProperty("line.separator");	
	private int e;																// 边数量
	private int v;																// 顶点数量
	private Vertex[] vertexs; 													// 顶点集合
	private SingleLinkedList[] adj;												// 邻接表
	private HashMap<String,Integer> map = new HashMap<String,Integer>();		// key:顶点名称;value:顶点Id
	private HashMap<Integer,String> map2 = new HashMap<Integer,String>();		// key:顶点Id;value:顶点名称
	
	public AdjacencyListGraph (){
		
	}
	
	/**
	 * 读入给定的{@code in}输入流创建图对象
	 * 文件格式如下:<br/>
	 * 	3,2<br/>
	 *	塘桥,蓝村路<br/>
	 *	蓝村路,浦电路<br/>
	 */
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
		String[] info = sb.toString().split(" |\r\n");
		this.v = Integer.parseInt(info[0].split(",")[0]);
		this.e = Integer.parseInt(info[0].split(",")[1]);
		this.adj = new SingleLinkedList[v];
		for (int k = 0; k < v; k++) {
			adj[k] = new SingleLinkedList();
		}
		vertexs = new Vertex[this.v];
		int index = 1;
		int vIndex = 0;
		for (int k = 0; k < this.e; k++) {
			String[] edge = info[index++].split(",");
			String v1 = edge[0];
			String v2 = edge[1];
			if(map.get(v1) == null){
				vertexs[vIndex] = new Vertex(vIndex, v1);
				map.put(v1, vIndex);
				map2.put(vIndex, v1);
				vIndex++;
			}
			if(map.get(v2) == null){
				vertexs[vIndex] = new Vertex(vIndex, v2);
				map.put(v2, vIndex);
				map2.put(vIndex, v2);
				vIndex++;
			}
			addEdge(map.get(v1), map.get(v2));
		}
	}

	private void addEdge(Integer v, Integer w) {
		validateEdge(v);
		validateEdge(w);
		
		this.adj[v].addToHead(w);
		this.adj[w].addToHead(v);
	}

	private void validateEdge(Integer w) {
		if(w < 0 || w > this.v)
			throw new IllegalArgumentException("顶点"+w+"必须在0和"+this.v+"之内");
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
        s.append(v + " 个顶点, " + e + " 条边 " + NEWLINE);
        for (int i = 0; i < v; i++) {
            s.append(map2.get(i) + ": ");
            for (int w : adj[i]) {
                s.append(map2.get(w) + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
	}
	
	public static void main(String[] args) {
		try {
			AbstractGraph g = new AdjacencyListGraph();
			g.create(new FileInputStream(new File("script\\Graph\\Line4.txt")));
			System.out.println(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
