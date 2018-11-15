package algorithms.chapter3;


public class FrequencyCounter {

	public static void main(String[] args) {
		String[] words = {"A","bad","beginning","makes","a","bad","ending"};
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		for( String word : words ){
			if( st.get(word) == null)
				st.put(word, 1);
			else
				st.put(word, st.get(word)+1);
		}
		System.out.print("单词频率如下:");
		for(String word : st.keys())
			System.out.println( word + ":" + st.get(word));
		System.out.println(st.size());
	}

}
