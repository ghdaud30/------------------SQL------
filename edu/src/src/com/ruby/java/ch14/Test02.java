package com.ruby.java.ch14;

@FunctionalInterface
interface Multyply{
	double getValue();
}

public class Test02 {
	
	public static void main(String[] args) {
		Multyply m;
		
		m = () -> 3.14 * 2;
		System.out.println(m.getValue());
		
		m = () -> 10 * 3;
		System.out.println(m.getValue());
	}
}
