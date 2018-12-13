package com.vv.demo.design.singleton;

public class TestSingleton {

	public static void main(String[] args) {
		Singleton sing = Singleton.getInstance();
        Singleton sing2 = Singleton.getInstance();
        
        System.out.println(sing);
        System.out.println(sing2);
	}

}
