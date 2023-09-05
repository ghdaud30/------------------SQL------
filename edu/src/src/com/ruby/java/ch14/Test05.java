package com.ruby.java.ch14;

import java.util.Scanner;

interface NumberFunc {
	int func(int n, int d);
}

public class Test05 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int a, b, c = 0;

		NumberFunc sum = (n, d) -> {
			int result = 0;
			
			for (int i = n; i <= d; i++) {
				result += i;
			}
			return result;
		};

		while (true) {
			System.out.print("please input the two numbers (a): ");
			a = sc.nextInt();
			if (a == 0)
				break;
			
			System.out.print("please input the two numbers (b): ");
			b = sc.nextInt();
			if (b == 0)
				break;

			if (a > b) {
				c = a;
				a = b;
				b = c;
			}
			
			System.out.println("From a to b of Sum is " + sum.func(a, b));
		}
		
		System.out.println("The Function is over");
		
		// System.out.println("From 1 to 10 of Sum is : " + sum.func(10));
		// System.out.println("From 1 to 100 of Sum is : " + sum.func(100));
	}
}