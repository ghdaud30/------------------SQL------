package 자료구조6장정렬알고리즘;

//셸 정렬(버전 1) 6-5

import java.util.Scanner;

class 실습6_6ShellSort {
//--- 셸 정렬 ---//
static void shellSort(int[] a, int n) {
   int count = 0;
   for (int h = n / 2; h > 0; h /= 2)
       for (int i = h; i < n; i++) {
           int j;
           int tmp = a[i];
           for (j = i - h; j >= 0 && a[j] > tmp; j -= h)
               a[j + h] = a[j];
           System.out.println("h = " + h + ", tmp = " + tmp + ", a[j+h] = " + a[j+h]);
           a[j + h] = tmp;
           count++;
           for (int na: a)
          	 System.out.print(" " + na);
           System.out.println();
       }
   System.out.println(count);
}

public static void main(String[] args) {
   Scanner stdIn = new Scanner(System.in);

   System.out.println("셸 정렬(버전 1)");
   //System.out.print("요솟수: ");
  // int nx = stdIn.nextInt();
   int nx = 8;
   //int[] x = new int[nx];
   int []x = {8,1,4,2,7,6,3,5};
   /*
   for (int i = 0; i < nx; i++) {
       double d = Math.random();
       x[i] = (int) (d * 40);
     //  System.out.print("x[" + i + "]: ");
     //  x[i] = stdIn.nextInt();
   }
   */
   for (int n: x)
  	 System.out.print(" " + n);
   System.out.println();
   shellSort(x, nx);            // 배열 x를 셸정렬

   System.out.println("오름차순으로 정렬했습니다.");
   for (int i = 0; i < nx; i++)
       System.out.print(" " + x[i]);
}
}
