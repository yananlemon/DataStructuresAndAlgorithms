package com.chapter5;

/**
 * <p>StandardDeviation</p>  
 * <p>计算标准偏差</p>  
 * @author yanan  
 * 参考资料：https://baike.baidu.com/item/%E6%A0%87%E5%87%86%E5%81%8F%E5%B7%AE/10963562?fr=aladdin#2
 * @date 2018年3月26日
 */
public class StandardDeviation {

	public static void main(String[] args) {
		int[] data=initArray(2000);
		//System.out.println(avg(data));
		double avg=sumWithRecursion(0,data)/data.length;
		for(int i=0;i<data.length;i++){
			System.out.printf("%d\t",data[i]);
		}
		System.out.println("\n标准偏差是："+sd(v(avg,data)));
	}
	
	static int index=0;
	
	/**
	 * 初始化随机数组，数组长度由length决定。
	 * @param length
	 * @return
	 */
	static int[] initArray(int length){
		int[] rs=new int[length];
		for(int i=0;i<length;i++){
			rs[i]=(int) (Math.random()*99)+1;
		}
		return rs;
	}
	
	/**
	 * 求数组均值
	 * @return 平均值
	 */
	static double avg(int[] data){
		double sum=0;
		for(int i=0;i<data.length;i++){
			sum+=data[i];
		}
		return sum/data.length;
	}
	
	/**
	 * 使用递归求和
	 * @param index
	 * @return
	 */
	static double sumWithRecursion(int index,int[] data){
		if(index==data.length-1){
			return data[index];
		}
		/*double v1=data[index];
		double v2=sumWithRecursion(++index);
		return v1+v2;*/
		return data[index]+sumWithRecursion(++index,data);
	}
	
	/**
	 * 求方差：累加数组中的每个数字减去平均值之后的平方的和,再除以数组长度
	 * @return
	 */
	static double v(double avg,int[] data){
		double sum=0;
		for(int i=0;i<data.length;i++){
			sum+=Math.pow((data[i]-avg), 2);
		}
		return sum/data.length;
	}
	
	/**
	 * 求标准偏差
	 * @param v
	 * @return
	 */
	static double sd(double v){
		return Math.sqrt(v);
	}

}
