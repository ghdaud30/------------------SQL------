package 자료구조5장재귀알고리즘;

import java.util.ArrayList;
import java.util.List;

	public class Chap5_Test_MazingProblem_4회차02 {

		static Offsets[] moves = new Offsets[8];//static을 선언하는 이유를 알아야 한다
		static Directions2 direction = Directions2.N;
		
		
		public static void path(int[][] maze, int[][] mark, int ix, int iy) {

			mark[1][1] = 1;
			StackList st = new StackList(50);
			Items3 temp = new Items3(0, 0, 0);//N :: 0
			temp.x = 1;
			temp.y = 1;   //(1,1) 출발점
			temp.dir = 2;//E:: 2  -----  출발시 동쪽으로 이동   시계순서대로 북쪽이 0 북동이 1 동이 2 34567
			
			mark[temp.x][temp.y] = 2;//미로 찾기 궤적은 2로 표시 -----지나간곳은 2로 표시
			st.push(temp);	
			
			while (!st.isEmpty()) // stack not empty
			{	
				
				Items3 tmp = st.pop(); 		//unstack
				int x = tmp.x;
				int y = tmp.y;
				int dir = 0;
				
				System.out.println(tmp);
				System.out.println(x);
//				if(x == ix && y == iy) {
//					mark[x][y] = 2;
//					System.out.println("path in maze ");
//					break;
//				}
				
				mark[x][y] = 3;		//backtracking 궤적은 1로 표시
				

				while (dir < 8) // moves forward
				{	

					
					int g = x + moves[dir].a; int h = y + moves[dir].b;
					
					if ((g == ix) && (h == iy)) { // reached exit	// output path
						System.out.println("path in maze");
						mark[g][h] = 2;
						st.clear();
						break;
					}
					
					if ((maze[g][h] == 0) && (mark[g][h] == 0)) { // new position
						tmp = new Items3(g, h, dir);
						st.push(tmp);
						mark[x][y] = 2;
						break;
					} else {
						if(mark[g][h] == 2){
							tmp = new Items3(g, h, dir);
							st.push(tmp);
						}
						dir++;
						continue;
					}
				}
			}
			
			//System.out.println("no path in maze ");
			
			
		}
		static void showMatrix(int[][]d, int row, int col) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print(d[i][j] + " ");

				}
				System.out.println();
			}
			System.out.println();
		}
		public static void main(String[] args) {
			int[][] maze = new int[14][17];  //input 보다 더 크게 경계를 만듬, 알고리즘 복잡도를 줄이기 위해
			int[][] mark = new int[maze.length][maze[0].length];  //지나온곳은 mark로 표시

			int input[][] = { // 12 x 15
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
					{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
					{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
					{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
					{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
					{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
					{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
					{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }};
			for (int ia = 0; ia < 8; ia++)
				moves[ia] = new Offsets(0, 0);//배열에 offsets 객체를 치환해야 한다.
			moves[0].a = -1;	moves[0].b = 0;
			moves[1].a = -1;	moves[1].b = 1;
			moves[2].a = 0;		moves[2].b = 1;
			moves[3].a = 1;		moves[3].b = 1;
			moves[4].a = 1;		moves[4].b = 0;
			moves[5].a = 1;		moves[5].b = -1;
			moves[6].a = 0;		moves[6].b = -1;
			moves[7].a = -1;	moves[7].b = -1;
			//Directions d;
			//d = Directions.N;
			//d = d + 1;//java는 지원안됨
			
			
			for (int i = 0; i < input.length; i++) {
				for (int j = 0; j < input[0].length ; j++) {
					//input[12][15] -> maze[14][17]
					maze[i+1][j+1] = input[i][j];
				}
			}
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length ; j++) {
					//input[12][15] -> maze[14][17]
					if(i == 0 && j <= maze[0].length - 1) {
						maze[i][j] = 1;
					}else if(i == maze.length - 1 && j <= maze[0].length - 1){
						maze[i][j] = 1;
					}else if(i >= 1 && i <= maze.length - 2 && j == 0) {
						maze[i][j] = 1;
					}else if(i >= 1 && i <= maze.length - 2 && j == maze[0].length-1) {
						maze[i][j] = 1;
					}
				}
			}
			
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length ; j++) {
					//input[12][15] -> maze[14][17]
					mark[i][j] = maze[i][j];
				}
			}
			
			System.out.println(maze.length);
			System.out.println(maze[0].length);
			System.out.println("maze[14,17]::");
			showMatrix(maze, maze.length, maze[0].length);
		
			System.out.println("mark::");
			showMatrix(mark, maze.length, maze[0].length);

			path(maze, mark, input.length, input[0].length);
			System.out.println("solved-mark::");
			showMatrix(mark, maze.length, maze[0].length);
		}
	}