package com.chapter2;


public class SearchingInOrderAndBinarySearch {

	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);
		System.out.println("请输入一个数字，这将会生成从1到这个数字之间的所有数\n");
		int n = in.nextInt();
		int[] array = buildArray(n);
		while(true){
			System.out.println("请输入要查找的数字\n");
			int num=in.nextInt();
			long start = System.currentTimeMillis();
			int rs=searchingInOrder(array, num);
			long end = System.currentTimeMillis();
			System.out.println("顺序查找花费了："+(end-start)+"ms");
			if(rs==-1){
				System.out.println("在列表中没有找到该数字");
			}else{
				System.out.println("在列表中找到了该数字"+rs);
			}
			start = System.currentTimeMillis();
			rs=binarySearch(array, num);
			end = System.currentTimeMillis();
			System.out.println("二分查找花费了："+(end-start)+"ms");
			System.out.println("是否继续(Y\\N)?");
			String next = in.next();
			if(next.equals("N")){
				break;
			}
		}*/
		int[] array = buildArray(1000000000);
		long start = System.currentTimeMillis();
		int target=100000000;
		int rs=binarySearch(array, target);
		long end = System.currentTimeMillis();
		System.out.println("循环版本花费:"+(end-start)+"ms");
		System.out.println(rs);
		start = System.currentTimeMillis();
		//rs=binarySearchWithRecursion(array, target, 0, array.length-1);
		end = System.currentTimeMillis();
		System.out.println("递归版本花费:"+(end-start)+"ms");
		System.out.println(rs);
	}
	
	static int[] buildArray(int n){
	
		int[] array=new int[n+1];
		for(int i = 1; i <= n; i++){
			array[i] = i;
		}
		return array;
	}
	
	static int searchingInOrder(int[] array,int n){
		
		//从数组起始位置开始遍历直到找到目标数字或者遍历到数组末尾
		for(int i = 0;i < array.length; i++){
			if(array[i] ==n){
				
				//如果找到该数字则返回这个数字
				return n;
			}
		}
		
		//如果没有找到则返回-1
		return -1;
	}
	
	static int binarySearchWithRecursion(int[] array,int target,int begin,int end){
		if(array.length==1){
			return -1;
		}
		int middle=(begin+end)>>1; 
        if(array[middle]>target){  
            return binarySearchWithRecursion(array, target, begin, middle-1);
        }  
        if(array[middle]<target){  
        	return binarySearchWithRecursion(array, target, middle+1, end);
        }  
        return middle;  
	}
	
	public static int binarySearch(int[] array,int target){  
        int begin=0;  
        int end=array.length-1;  
        int middle=0;  
        while(begin<=end){  
            middle=(begin+end)/2;  
            if(array[middle]==target){  
                return middle;  
            }  
            if(array[middle]>target){  
                end=middle-1;  
            }  
            if(array[middle]<target){  
                begin=middle+1;  
            }  
        }  
          
        return -1;  
    }  
	
	

}
