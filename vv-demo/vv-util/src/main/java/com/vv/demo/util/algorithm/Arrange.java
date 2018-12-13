package com.vv.demo.util.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <br>类 名: Arrange 
 * <br>描 述: M中选N组合算法C(n,m)
 * <br>		例如：12345中选择3个数字进行组合，其中，123，321属于一个
 * <br>作 者: Vv
 * <br>创 建： 2014年10月23日 
 * <br>版 本：v1.2 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class Arrange {
	/** 组合总个数 **/
	public static int total = 0;

	public static void main(String[] args) {
		//String[] a = { "未中奖", "未兑奖", "已兑奖", "弃奖", "出票失败" };
		// 票兑奖状态
		//String[] a = { "未开奖","未中奖", "未兑奖", "已兑奖", "弃奖"};
		//String[] a = { "未开奖/未刮开","未中奖", "中奖[税前总金额]",  "出票中…", "出票失败"};
		String[] a = { "未开奖[未刮开]","未中奖","未兑奖","已兑奖",  "弃奖",  "出票中…", "出票失败"};
		//String[] a = { "1", "2", "3", "4", "5" };
		int num = a.length;

		for (int i = 1; i <= num; i++) {
			for (String obj : combine(a, i)) {
				// break;
				System.out.println(obj.trim());
			}
		}
		
		System.out.println(total);

	}

	/**
	 * <br>描 述：M选N的组合
	 * <br>作 者：Vv 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param a 需要进行组合数组元素
	 * @param num M选N中 N的个数
	 * @return M选N的组合个数
	 */
	public static List<String> combine(String[] a, int num) {
		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		String[] b = new String[a.length];
		for (int i = 0; i < b.length; i++) {
			if (i < num) {
				b[i] = "1";
			} else {
				b[i] = "0";
			}
		}
		// 如果num=1，则b = [1, 0, 0, 0, 0, 0, 0];
		// 如果num=2，则b = [1, 1, 0, 0, 0, 0, 0];
		// 如果num=3，则b = [1, 1, 1, 0, 0, 0, 0];
		System.out.println(Arrays.asList(b));
		
		int point = 0;
		int nextPoint = 0;
		int count = 0;
		int sum = 0;
		String temp = "1";
		
		while (true) {
			// 判断是否全部移位完毕
			for (int i = b.length - 1; i >= b.length - num; i--) {
				if (b[i].equals("1"))
					sum += 1;
			}
			// 根据移位生成数据
			for (int i = 0; i < b.length; i++) {
				if (b[i].equals("1")) {
					point = i;
					sb.append(a[point]);
					sb.append(" ");
					count++;
					if (count == num)
						break;
				}
			}
			// 往返回值列表添加数据
			list.add(sb.toString());

			// 当数组的最后num位全部为1 退出
			if (sum == num) {
				break;
			}
			sum = 0;

			// 修改从左往右第一个10变成01
			for (int i = 0; i < b.length - 1; i++) {
				if (b[i].equals("1") && b[i + 1].equals("0")) {
					point = i;
					nextPoint = i + 1;
					b[point] = "0";
					b[nextPoint] = "1";
					break;
				}
			}
			// 将 i-point个元素的1往前移动 0往后移动
			for (int i = 0; i < point - 1; i++)
				for (int j = i; j < point - 1; j++) {
					if (b[i].equals("0")) {
						temp = b[i];
						b[i] = b[j + 1];
						b[j + 1] = temp;
					}
				}
			// 清空 StringBuffer
			sb.setLength(0);
			count = 0;
		}
		//
		total +=list.size();
		//System.out.println("数据长度 " + list.size());
		return list;

	}

}