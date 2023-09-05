package misson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class BigNumber_JBK {

	public static void main(String[] args) {

		// 두 수 입력 받기
		String sum1, sum2;

		try (BufferedReader br = new BufferedReader(new FileReader("D:/Users/work/src/bin/misson/input.txt"))) {
			sum1 = br.readLine();
			sum2 = br.readLine();

			System.out.println("num1 : " + sum1);
			System.out.println("num2 : " + sum2);
			System.out.println("_".repeat(50));
			System.out.println("sum :" + sumBigNumber(sum1,sum2));
		}catch (Exception e) {e.printStackTrace();}
		System.out.println("_".repeat(50));
		System.out.println("Its done");
	}
	
	// 두 수를 더한다
	
	private static String sumBigNumber(String sum1, String sum2) {
		int arr1[] = new int[sum1.length()];
		int arr2[] = new int[sum2.length()];
		
		for (int arr : arr1) {
			
		}
		
	}

}

