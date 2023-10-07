package 자료구조9장트리;
/*
 * 23.6.7 3회차 실습 코드
 */
import java.util.Random;
import java.util.Scanner;


//정수를 저정하는 이진트리 만들기 실습

class TreeNode {
	TreeNode LeftChild;
	int data;
	TreeNode RightChild;

	public TreeNode() {
		LeftChild = RightChild = null;
	}
	public TreeNode(int x) {
		data = x;
		LeftChild = RightChild = null;
	}
	
	public int toString(TreeNode t) {
		return t.data;
	}
}

class Tree {
	TreeNode root;

	Tree() {
		root = null;
	}

	TreeNode inorderSucc(TreeNode current) {
		TreeNode temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		System.out.println(temp.toString(temp));
		return temp;
	}

	boolean isLeafNode(TreeNode current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	boolean insert(int x) {// binary search tree를 만드는 입력 => A + B * C을 tree로 만드는 방법: 입력 해결하는 알고리즘 작성 방법을
							// 설계하여 구현
		TreeNode tmp = new TreeNode(x);
		TreeNode p = root;
		TreeNode q = null;
		
		if(root == null) {
			root = tmp;
			return true;
		}
		
		while(p != null) {
			
			if(x > p.data) {
				q = p;
				p = p.RightChild; 
			}
			else if (x < p.data){
				q = p;
				p = p.LeftChild;
			}
			else
			{
				System.out.println("there is already the same number");
				return false;
			}
		}
		
		if(q.data < x) {
			q.RightChild = tmp;
		}
		else{
			q.LeftChild = tmp;
		}

		return true;
	}

//	boolean nochild(TreeNode p , TreeNode q) {
//		if(isLeafNode(p)) {
//			if(q.RightChild == p) {
//				q.RightChild = null;
//				return true;
//			}
//			else {
//				q.LeftChild = null;
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
//	boolean onechild(TreeNode p, TreeNode q) {
//		if(p.RightChild == null) {
//			q.LeftChild = p.LeftChild;
//			return true;
//		}
//		else if(p.LeftChild == null) {
//			q.RightChild = p.RightChild;
//			return true;
//		}
//		
//		return false;
//	}
	
	boolean delete(int num) {
		TreeNode p = root;
		TreeNode q = null;

		if(root == null) return false; 
		

		while (p != null) {

			if (num > p.data) {
				q = p;
				p = p.RightChild;
			} 
			else if (num < p.data) {
				q = p;
				p = p.LeftChild;
			}
			
			else {
				//자식 노드가 없는 경우
				if(isLeafNode(p)) {
					if(q.RightChild == p) {
						q.RightChild = null;
						p = null;
						return true;
					}
					else {
						q.LeftChild = null;
						p = null;
						return true;
					}
				}
							
				//자식 노드가 1개인 경우
				if(p.RightChild == null) {
					q.LeftChild = p.LeftChild;
					p = null;
					return true;
				}
				else if(p.LeftChild == null) {
					q.RightChild = p.RightChild;
					p = null;
					return true;
				}
				
				
				//자식 노드가 2개인 경우
				TreeNode temp = inorderSucc(p);
				int temp_data = temp.data;
//				TreeNode q_temp = search2(temp_data);
//				if(q_temp.LeftChild == temp) {
//					p.data = temp_data;
//					q_temp.LeftChild = null;
//				}
//				else {
//					p.data = temp_data;
//					q_temp.RightChild = null;
//					
//				}
				delete(temp_data);
				p.data = temp_data;
				return true;
			}
				
		}
		return false;
	}

	boolean search(int num) {
		TreeNode p = root;
		TreeNode q = null;
		
		if(root == null) return false;
		
		while(p != null) {
			if(num > p.data) {
				q = p;
				p = p.RightChild;
			}
			else if(num < p.data) {
				q = p;
				p = p.LeftChild;
			}
			else {
				return true;
			}

		}
		return false;
	}


	TreeNode search2(int num) {
		TreeNode p = root;
		TreeNode q = null;

		while (p != null) {
			if (num > p.data) {
				q = p;
				p = p.RightChild;
			} else if (num < p.data) {
				q = p;
				p = p.LeftChild;
			} else {
				return q;
			}

		}
		return null;

	}
}

public class BinaryTree_int {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("순차출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		Tree t = new Tree();
		Menu menu; // 메뉴
		int count = 0;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: // 노드 삽입
				System.out.println("The number of items = ");
//				count = stdIn.nextInt();
//		        int[] input = new int[count];
//		        int currentIndex = 0;
//
//		        while (currentIndex < count) {
//		            int randomNumber = rand.nextInt(40); // 0부터 29까지의 랜덤 정수
//		            boolean isDuplicate = false;
//
//		            // 중복 확인
//		            for (int i = 0; i < currentIndex; i++) {
//		                if (input[i] == randomNumber) {
//		                    isDuplicate = true;
//		                    break;
//		                }
//		            }
//
//		            // 중복이 아니면 배열에 추가
//		            if (!isDuplicate) {
//		                input[currentIndex] = randomNumber;
//		                currentIndex++;
//		            }
//		        }
				
//				int[] input = new int[count];
//				for (int ix = 0; ix < count; ix++) {
//					input[ix] = rand.nextInt(30);
//				}
//				
				int[] input = {3,6,15,8,7,9,23,21,24};
				count = input.length;
				for (int i = 0; i < count; i++) {
					if (!t.insert(input[i]))
						System.out.println("Insert Duplicated data");
					else {
						System.out.print(input[i] + " ");
					}
				}
				break;

			case Delete: // 노드 삭제
				System.out.println("삭제할 데이터:: ");
				num = stdIn.nextInt();
				if (t.delete(num))
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println("삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터:: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result)
					System.out.println(" 데이터 = " + num + " 존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
