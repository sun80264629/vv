package com.vv.demo.j2se.lang;

public class StudyBoolean {
	public static void main(String[] args) {
		// 常量
		System.out.println("Boolean.FALSE - " + Boolean.FALSE); // 运行结果：false
		System.out.println("Boolean.TRUE - " + Boolean.TRUE); // 运行结果：true
		System.out.println("Boolean.TYPE - " + Boolean.TYPE); // 运行结果：boolean
		
		// 构造方式
		// boolean
		Boolean b1 = new Boolean(true);
		System.out.println("b1 - " + b1);
		// String
		Boolean b2 = new Boolean("ssss");
		System.out.println("b2 - " + b2);
		// String
		Boolean b3 = new Boolean("true");
		System.out.println("b3 - " + b3);
		// String
		Boolean b4 = new Boolean("false");
		System.out.println("b4 - " + b4);
		
		// 对象比较
		// 相同则为0，
		System.out.println("b1:b3 - " + b1.compareTo(b3)); // 运行结果：0
		// true : false则为1
		System.out.println("b1:b2 - " + b1.compareTo(b2)); // 运行结果：1
		// false : true则为-1
		System.out.println("b2:b3 - " + b2.compareTo(b3));  // 运行结果：-1
		
		System.out.println("b2==b3 - " + (b2==b3));
	}
}
