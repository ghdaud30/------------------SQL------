package 자료구조8장리스트;

//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class LinkedList1 {

	class Node1 {
		int data;
		Node1 next;

		public Node1(int element) {
			data = element;
			next = null;
		}
		

	}
	
	private Node1 head;
	
	public LinkedList1() {
		head = null;
	}

	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{	
		
		Node1 tmp = new Node1(element);
		Node1 ptr = head, q = null; // q 는 이전에 값
		
		if(head == null) {
			head = tmp;
			return;
		}
		
		if(head.data > element) {
			tmp.next = head;
			head = tmp;
			return;
		}
		
		
		while(ptr != null) {
			if(ptr.data < element) {
				q = ptr;
				ptr = ptr.next;
			}
			else {
				q.next = tmp;
				tmp.next = ptr;
				return;
			}
		}
		
		q.next = tmp;
		
		return;	
	}
	
	public int Delete(int element) // 데이터를 삭제 한다
	{
		
		Node1 ptr = head, q = null;
		
		if(head == null) {System.out.println("head is null please "
				+ "\n insert the data first");
			return -1;
		}
		
		if(head.data == element) {
			ptr = head.next;
			head = ptr;
			return element;
		}
		
		
		while(ptr != null) {

			if( element > ptr.data) {
				q = ptr;
				ptr = ptr.next;
			}
			else if (ptr.data == element) {
				q.next = ptr.next;
				return element;
			}
			else {
				return -1;
			}
		}
		return element;
	}
	
	public void Show() { // 전체 리스트를 순서대로 출력한다.
		System.out.println("");
		Node1 ptr = head;
		
		if(ptr == null) System.out.println("ptr is null");
		while(ptr != null) {
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	}
	
	public boolean Search(int data) { // 주어진 데이터를 검색한다.
		
		Node1 ptr = head , q = null;
		
		if(head == null) {
			System.out.println("head is null please "
					+ "\n insert the data first");
			return false;
		}
		
		while(ptr != null) {
			if(ptr.data == data) {
				return true;
			}
			else if(ptr.data < data) {
				q = ptr;
				ptr = ptr.next;
			}
			else {
				return false;
			}
				
		}
		
		return false;
	}
}

class 정수연결리스트 {
	
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("출력"), Search("검색"), Exit("종료");

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
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		Random rand = new Random();
		System.out.println("Linked List");
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int data = 0;
		System.out.println("inserted");
		l.Show();
		System.out.println("");
		
		do {
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				data = rand.nextInt(20);
				//double d = Math.random();
				//data = (int) (d * 50);
				l.Add(data);
				break;
			case Delete: // 머리 노드 삭제
				data = sc.nextInt();
				int num = l.Delete(data);
				System.out.println("삭제된 데이터는 " + num);
				break;
			case Show: // 노드 출력하기
				l.Show();
				break;
			case Search: // 회원 번호 검색
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}

