package 자료구조6장정렬알고리즘;

//퀵 정렬(비재귀 버전)

import java.util.Scanner;

class IntStack {
	private int[] stk; // 스택용 배열
	private int capacity; // 스택의 크기
	private int ptr; // 스택 포인터

//--- 실행시 예외: 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {
		}
	}

//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() {
		}
	}

//--- 생성자(constructor) ---//
	public IntStack(int maxlen) {
		ptr = 0;
		capacity = maxlen;
		
		try {
			stk = new int[capacity];
		}catch(OutOfMemoryError e) {
			capacity = 0;
		}
	}

//--- 스택에 x를 푸시 ---//
	public int push(int x) throws OverflowIntStackException {
		if(ptr >= capacity)
			throw new OverflowIntStackException();
		return stk[ptr++] = x;
	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public int pop() throws EmptyIntStackException {
		if(ptr <= 0)
			throw new EmptyIntStackException();
		return stk[--ptr];
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntStackException {
		if(ptr <= 0)
			throw new EmptyIntStackException();
		return stk[ptr - 1];
	}

//--- 스택을 비움 ---//
	public void clear() {
		ptr = 0;
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(int x) {
		for(int i = ptr - 1; i >= 0 ; i--)
			if(stk[i] == x)
				return i;
		return -1;
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return ptr;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return ptr <= 0;
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return ptr >= capacity;
	}

//--- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
	public void dump() {
		//구현
		if (ptr <= 0)
			System.out.println("The Stack is empty");
		else {
			for(int i = 0; i < ptr; i ++) {
				System.out.print(stk[i] + " ");
			System.out.println();
			}
		}
	}
}

public class 실습6_10QuickSortStack {
	static int count = 0;
 //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
 static void swap(int[] a, int idx1, int idx2) {
     int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
 }

 //--- 퀵 정렬(비재귀 버전)---//
 static void quickSort(int[] a, int left, int right) {
     IntStack lstack = new IntStack(right - left + 1);
     IntStack rstack = new IntStack(right - left + 1);

     lstack.push(left);
     rstack.push(right);

     while (!lstack.isEmpty()) {
         int pl = left  = lstack.pop();        // 왼쪽 커서
         int pr = right = rstack.pop();        // 오른쪽 커서
         int x = a[(left + right) / 2];        // 피벗은 가운데 요소

         do {
             while (a[pl] < x) pl++;
             while (a[pr] > x) pr--;
             if (pl <= pr)
                 swap(a, pl++, pr--);
             count++;
         } while (pl <= pr);
         showData(a);
         System.out.println();
         if (left < pr) {
        	 System.out.println("left = " + left + ", pr = " + pr);
             lstack.push(left);           // 왼쪽 그룹 범위의
             rstack.push(pr);             // 인덱스를 푸시
         }
         if (pl < right) {
          	 System.out.println("pl = " + pl + ", right = " + right);
             lstack.push(pl);             // 오른쪽 그룹 범위의
             rstack.push(right);          // 인덱스를 푸시
         }
     }
 }
 static void showData(int[] d) {
	 System.out.println();
     for (int i = 0; i < d.length; i++)
         System.out.print(d[i] + " ");
 }
 public static void main(String[] args) {
     Scanner stdIn = new Scanner(System.in);
     
     System.out.println("퀵 정렬");
     System.out.print("요솟수: ");
     int nx = stdIn.nextInt();
     int[] x = new int[nx];

     for (int i = 0; i < nx; i++) {
		double d = Math.random();
		x[i] = (int) (d * 20);
         //System.out.print("x[" + i + "]: ");
         //x[i] = stdIn.nextInt();
     }
     showData(x);
     System.out.println("");
     
     quickSort(x, 0, nx - 1);            // 배열 x를 퀵정렬
     
     System.out.println("비교횟수= " + count);
     System.out.println("오름차순으로 정렬했습니다.");
     showData(x);
 }
}
