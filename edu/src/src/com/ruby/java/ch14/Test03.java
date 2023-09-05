package com.ruby.java.ch14;

import java.util.Scanner;

interface Verify {
	boolean check(int n, int d);
}

public class Test03 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Verify vf = (n, d) -> (n % d) == 0;
		
		while (true) {

			System.out.print("please input the numbers (n): ");
			int n = in.nextInt();
			if(n == 0) {
				System.out.println("shutdown! please do it again!");
				break;
			}
			
			System.out.print("please input the numbers (d): ");
			int d = in.nextInt();
			if(n == 0) {
				System.out.println("shutdown! please do it again!");
				break;
			}
			
			System.out.println(vf.check(n, d));
		}
	}
}
