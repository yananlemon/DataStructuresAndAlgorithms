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
		if( lo <= hi)
			return;
		int mid = lo + (hi - lo)/2;
		// 将左半边排序
		sort(a,lo,mid);
		// 将右半边排序
		sort(a,mid+1,hi);
		// 归并结果
		merge(a,lo,mid,hi);
	}
	
	private static void merge(Comparable[] a, int lo, int mid,int hi) {
		
	}
}
