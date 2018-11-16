package algorithms.chapter3;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class FrequencyCounter {

	public static void main(String[] args) {
		String filename = "script/chapter3/tale.txt";
		//String filename = "script/chapter3/tinyTale.txt";
		Object[] words = readWord(filename).toArray();
		System.out.println(words.length);
		/*String[] words = {"A","bad","beginning","makes","a","bad","ending"};*/
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		for( Object word : words ){
			if( st.get(word.toString()) == null)
				st.put(word.toString(), 1);
			else
				st.put(word.toString(), st.get(word.toString())+1);
		}
		for(String word : st.keys())
			System.out.println(word + " "+ st.get(word));
		System.out.println("单词频率最高且长度大于等于10如下:");
		String max = "";
		st.put(max, 0);
		ArrayList<String> maxArray = new ArrayList<String>();
		for(String word : st.keys())
			if( st.get(word) >= st.get(max) && word.length() >= 10){
			//if(word.length() >= 10){
				max = word;
				maxArray.add(max);
			}
		System.out.println("长度大于等于10的单词数量："+maxArray.size());
		for(String m : maxArray)
			System.out.println(m+ " " + st.get(m));
	}
	
	static ArrayList<String> readWord(String filename){
		ArrayList<String> rs = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new FileInputStream(filename));
			while(scanner.hasNext()){
				String word = scanner.next() ;
				//System.out.println(world);
				rs.add(word);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
