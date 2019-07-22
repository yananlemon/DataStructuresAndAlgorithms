package algorithms.chapter2;

/**
 * Created by andy on 2019/7/22.
 */
public class BinarySearch {

	/**
	 *
	 * @param numbers
	 * @param key
	 * @return
	 */
	public int rank(int[] numbers,int key){
		int low = 0;
		int hi = numbers.length - 1;
		while( low <= hi ){
			int mid = low + (hi - low)/2;
			if( numbers[mid] < key)
				low = mid + 1;
			else if( numbers[mid] > key)
				hi = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	public int rankWithRecurtion(int[] numbers,int key){
		return rankWithRe(numbers,key,0,numbers.length-1);
	}

	private int rankWithRe(int[] numbers,int key,int low,int hi){
		if( low > hi ){
			return -1;
		}
		int mid = low + (hi - low)/2;
		if( numbers[mid] < key)
			return rankWithRe(numbers, key, mid+1, hi);
		else if( numbers[mid] > key)
			return rankWithRe(numbers, key, low, mid-1);
		else
			return mid;

	}


	public static void main(String[] args){
		int[] numbers={1,2,3,4,5,6,7,8,9,10};
		BinarySearch binarySearch = new BinarySearch();
		System.out.println(binarySearch.rank(numbers,5));
		System.out.println(binarySearch.rankWithRecurtion(numbers,5));
	}
}