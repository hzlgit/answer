package com.hh.aws.utils;

import java.util.Random;

public class ArrayUtil {

	public static String join(Object[] array, String flag) {
		StringBuffer str_buff = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			str_buff.append(String.valueOf(array[i]));
			if (i < len - 1)
				str_buff.append(flag);
		}

		return str_buff.toString();
	}

	/**
	 * 获取数组中的最小值和最大值
	 * 
	 * @param arry
	 * @return
	 */
	public static double[] getMinMaxValue(double[] arry) {
		double[] result = new double[2];
		
		int i;
		double min, max;

		min = max = arry[0];
		for (i = 0; i < arry.length; i++) {
			if (arry[i] > max) // 判断最大值
				max = arry[i];
			if (arry[i] < min) // 判断最小值
				min = arry[i];
		}
	
		result[0] = min;
		result[1] = max;
		return result;
	}
	
	public static String getRandomValue(String[] array) {
		Random rand = new Random();
		int num = rand.nextInt(array.length);
		return array[num]; 
	}
	
	public static Integer getRandomValue(Integer[] array) {
		Random rand = new Random();
		int num = rand.nextInt(array.length);
		return array[num]; 
	}
	
	public static Long getRandomValue(Long[] array) {
		Random rand = new Random();
		int num = rand.nextInt(array.length);
		return array[num]; 
	}
	
	public static void main(String[] args) {
		String[] ids = { "a", "b", "c" };

		System.out.println(ArrayUtil.join(ids, ","));
		
		double[] result = ArrayUtil.getMinMaxValue(new double[]{2.621,2.727,2.719,2.711,2.861,3.23,2.96});
		System.out.println("最小值："+result[0]);
		System.out.println("最大值："+result[1]);
		
		String[] categorys = new String[] {"家具建材", "黄金珠宝", "咨询服务", "教育服务", "批发零售", "旅游留学", "收藏拍卖"};
		System.out.println(ArrayUtil.getRandomValue(categorys));
		
	}

}
