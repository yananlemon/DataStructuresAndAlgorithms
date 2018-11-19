package algorithms.chapter2;

/**
 * 归并排序
 * @author yanan
 *
 */
public class Merge {

	private static Comparable[] aux;// 归并排序所需的辅助数组
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a,0,a.length-1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if( hi <= lo)
			return;
		int mid = lo + (hi - lo)/2;
		// 将左半边排序
		sort(a,lo,mid);
		// 将右半边排序
		sort(a,mid+1,hi);
		// 归并结果
		merge(a,lo,mid,hi);
	}
	
	// 将a[lo...mid] 和a[mid+1...hi]归并
	private static void merge(Comparable[] a, int lo, int mid,int hi) {
		int i = lo,j = mid+1;
		// 将a[lo...hi]复制到aux[lo...hi]
		for( int k = lo; k <= hi; k++)
			aux[k] = a[k];
		// 归并回到a[lo...hi]
		for(int k = lo;k <= hi; k++) {
			if( i > mid )
				a[k] = aux[j++];
			else if( j > hi )
				a[k] = aux[i++];
			else if( aux[j].compareTo(aux[i]) < 0  )
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}
	
	public static void main(String[] args) {
		Integer[] array = {10,12,9,222,15};
		sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
