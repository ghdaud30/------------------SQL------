package misson;

import java.util.Random;

public class Matrix {
	public static void main(String[] args) {
		int a[][] = new int[3][4];
		int b[][] = new int[4][3];

		int s[][] = multply(a, b);

		print("a", a);
		print("b", b);
		print("s", s);

	}

	public static int[][] multply(int a[][], int b[][]) {
		Random rn = new Random();
		int s[][] = new int[a.length][b[0].length];
		
		for(int i=0; i <a.length; i++) {
			for(int j =0; j < b.length; j++) {
				a[i][j] = rn.nextInt(1,4);
				b[j][i] = rn.nextInt(1,4);
  			}
		}
		
		
		for(int i=0; i <a.length; i++) {
			for(int j =0; j < b[0].length; j++) {
				int sum = 0;
				for (int k =0; k < b.length; k ++) {
					sum += a[i][k] * b[k][j];
				}
				s[i][j] = sum;
			}
		}
		
		return s;
	}

	public static void print(String msg, int s[][]) {
		System.out.println(msg);
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				System.out.print(s[i][j] + " ");
			}
			System.out.println();
		}
	}
}
