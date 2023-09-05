package misson;

import java.util.Random;


public class Matrix1 {
	public static void main(String [] args) {
		int arr[] = new int[12];
		int brr[][] = new int[3][4];
		Random rn = new Random();
		
		for(int i =0; i < arr.length; i++) {
			arr[i] = rn.nextInt(1,4);
		}
		for(int i =0; i < arr.length; i++) {
			for(int j =0; j < arr.length / 3; j ++) {
				brr[i][j] = arr[i];
				System.out.println("brr is " + brr[i][j]);
			}
		}
	}
}
