package 자료구조5장재귀알고리즘;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
/*
* 체스판은 8 x 8
* 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
*  Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
*  pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능
*  체스판 최대 배치 문제 : king/16, Queen/8, rook/8, bishop/?, knight/?
*  rook 2개/a, h, knight 2개/b, g, bishop 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
*/

class Point3 {
	private int ix;
	private int iy;

	public Point3(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}
	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
}

class Stack333 {
	private Point3[] data; // 스택용 객체 배열
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 실행시 예외 : 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {
		}
	}

	// --- 실행시 예외 : 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() {
		}
	}

	// --- 생성자(constructor) ---//
	public Stack333(int maxlen) {
		top = 0;
		capacity = maxlen;
		try {
			data = new Point3[capacity]; // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	// --- 스택에 x를 푸시 ---//
	public void push(Point3 p) throws OverflowIntStackException {
		if (top >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException();
		data[top++] = p;
		return;
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point3 pop() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
//		Point ip = data[--top];
//		System.out.println("pop::"+ip.toString());
		return data[--top];
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point3 peek() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
		return data[top - 1];
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(벌견하지 못하면 –1)를 반환 ---//
	public int indexOf(Point3 x) {
		for (int i = top - 1; i >= 0; i--) // 정상 쪽에서 선형검색
			if (data[i].equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("스택이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data[i] + " ");
			System.out.println();
		}
	}
}

public class Chap5_Test_QueenEight_4회차3 {

	public static void SolveQueen(int[][] d , int num) {
        
		int count = 0;// count는 퀸의 갯수
		int ix = 0, iy = 0;
		int tmp = 0;
		Stack333 st = new Stack333(num);
		Point3 p = new Point3(ix, iy); // 초기 퀸의 좌표
		d[ix][iy] = 1; count++; //퀸의 개수 증가
		
		st.push(p); // 퀸의 위치를 스택에 삽입
		
		while (true) {

			if (st.isEmpty() && ix == 8) {
				break;
			}

			ix++; // 다음 행으로 변경
			int cy = 0; // 열의 위치를 0으로 변경

			while (ix < d.length) {

				// 1번 방법
//				while (cy < d[0].length) {
//
//					if (CheckMove(d, ix, cy)) {
//						st.push(new Point(ix, cy));
//						d[ix][cy] = 1;
//						count++;
//						break;
//					} else {
//						cy++;
//					}
//
//				}

				// 2번 방법 NextMove 이용
				while (cy < d[0].length) {
					tmp = NextMove(d, ix, cy);
					if (tmp != -1) {
						st.push(new Point3(ix, tmp));
						d[ix][tmp] = 1;
						count++;
						break;
					} else {
						cy++;
					}
				}

				if (cy == d[0].length) {

					if (!st.isEmpty()) {
						Point3 p1 = st.pop();
						d[p1.getX()][p1.getY()] = 0; // 이전 좌표 퀸 빼기
						ix--;
						count--;
						cy = p1.getY() + 1; // 팝한 위치의 다음 열으로 변경해서
						// 다시 순환 할 수 있도록 만듬
					}

				} else {
					break;
				}

				if (count == 8) {
					Point3 p1 = st.pop();
					d[p1.getX()][p1.getY()] = 0; // 이전 좌표 퀸 빼기
					ix--;
					count--;
					cy = p1.getY() + 1; // 팝한 위치의 다음 열으로 변경해서
					// 다시 순환 할 수 있도록 만듬
					showQueen(d);
				}
			}

		}
	}
	public static void showQueen(int[][] data) {
		for (int[] element : data) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(" " + element[j]);
			}
			System.out.println();
	}
		
}

	public static boolean checkRow(int[][] d, int crow) { // 행 체크
		
		if (crow < 0 || crow >= d.length) {
	        // crow가 유효한 범위를 벗어나면 오류 처리
	        return false;
	    }
		
		for(int i = 0; i < d[0].length ; i++) {
			if(d[crow][i] == 1) {
				return false;
			}
		}
		
		return true;
	}

	public static boolean checkCol(int[][] d, int ccol) { // 열 체크

		if (ccol < 0 || ccol >= d.length) {
	        // crow가 유효한 범위를 벗어나면 오류 처리
	        return false;
	    }
		
		for(int i = 0; i < d.length ; i++) {
			if(d[i][ccol] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDiagSW(int[][] d, int cx, int cy) { //x++, y-- or x--, y++ where 0<= x,y <= 7 , 대각선 체크
		
		while (cx >= 0 && cy < d.length) {
			
	        if (d[cx][cy] == 1) {
	            return false;
	        }
	        
	        cx--;
	        cy++;
	    }
		return true;
		
		
	}

	public static boolean checkDiagSE(int[][] d, int cx, int cy) {//x++, y++ or x--, y-- , 대각선 체크

		
		while (cx >= 0 && cy >= 0) {
			
	        if (d[cx][cy] == 1) {
	            return false;
	        }
	        
	        cx--;
	        cy--;
	    }
		
		return true;
	}
	
  public static boolean CheckMove(int[][]d, int x, int y) { //(x,y)로 이동 가능한지를 check
	  
	  if(checkRow(d, x) && checkCol(d, y) 
				&& checkDiagSW(d, x, y) 
				&& checkDiagSE(d, x, y)) {
				return true;
			}
	  
	  return false;

  }
  public static int NextMove(int[][]d, int row, int col) {//주어진 row에 대해서 주어진 col 부터
	  
	  	for(int i = col ; i < d[0].length; i ++) {
	  		if(CheckMove(d, row, i)) {
	  			return i;
	  		}
	  	}
	  
	    return -1;
  }
  
  // 순서대로 NextMove >  CheckMove 호출
  
  
	public static void main(String[] args) {
		
		int row = 4, col = 4;
		int[][] data = new int[row][col];
		int num = data.length;
		
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;
		
		SolveQueen(data, num);
		
//		System.out.println("solved Array data");
//		System.out.println("");
//
//		for (int[] element : data) {
//			for (int j = 0; j < data[0].length; j++) {
//				System.out.print(" " + element[j]);
//			}
//			System.out.println();
//		}
	}
}

