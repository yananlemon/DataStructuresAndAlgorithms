package algorithms.chapter3;

import java.util.ArrayList;
import java.util.Collections;

public class TestAVL {

	public static void main(String[] args) {
		AVLBST<Integer, Integer> avl = new AVLBST<Integer, Integer>();
		ArrayList<Integer> list = genIntegerList(1000);
		long beginTime = System.currentTimeMillis();
		for(Integer i : list)
			avl.put(i, i);
		System.out.println(System.currentTimeMillis() - beginTime);
		//System.out.println(avl.isAVL());
		System.err.println("height:" + avl.height());
	}
	
	static ArrayList<Integer> genIntegerList(int total){
		long beginTime = System.currentTimeMillis();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < total; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		System.out.println(System.currentTimeMillis() - beginTime);
		return list;
	}

}
