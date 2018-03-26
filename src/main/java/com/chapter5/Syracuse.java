package com.chapter5;
/**
 * <p>Syracuse</p>  
 * <p>编写一个递归函数，输出Syracuse数列，该数列第一个数是.....该数列以1结束 </p>  
 * @author yanan  
 * @date 2018年3月26日
 */
public class Syracuse {

	static int index=0;
	
	public static void main(String[] args) {
		int[] syracuse=getSyracuseArray(100,67);
		System.out.println("使用迭代：");
		for(int i=0;i<syracuse.length;i++){
			System.out.printf("%d\n",syracuse[i]);
		}
		for(int i=1;i<syracuse.length;i++){
			syracuse[i]=0;
		}
		outputSyracuse(syracuse, index, syracuse.length);
		System.out.println("使用递归：");
		for(int i=0;i<syracuse.length;i++){
			System.out.printf("%d\n",syracuse[i]);
		}
	}
	
	static int[] getSyracuseArray(int n,int choice){
		int[] array=new int[n];
		array[0]=choice;
		for(int i=1;i<n;i++){
			array[i]=0;
		}
		for(int i=1;i<n;i++){
			if((array[i-1]&1)==1){
				array[i]=3*(array[i-1])+1;
			}else{
				array[i]=array[i-1]/2;
			}
			
		}
		return array;
	}
	
	//[67, 0, 0, 0, 0, 0, 0]
	static void outputSyracuse(int[] array,int index,int n){
		
		if(index<n){
			if((array[index]&1)==1){
				if(index+1<n){
					array[index+1]=3*(array[index])+1;
					outputSyracuse(array,++index,n);
				}
			}else{
				if(index+1<n){
					array[index+1]=array[index]/2;
					outputSyracuse(array,++index,n);
				}
			}
		}
		return ;
		
	}

}
