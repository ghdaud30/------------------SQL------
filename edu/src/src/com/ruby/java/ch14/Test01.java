package com.ruby.java.ch14;

interface Myinterface {
	public void print();

}

class MyClass1 implements Myinterface {
	@Override
	public void print() {
		System.out.println("Myclass");
	}
}

class MyClass2 implements Myinterface {
	@Override
	public void print() {
		System.out.println("Myclass2");
	}
}

public class Test01 {
	public static void test(Myinterface mi) {
		mi.print();
	}

	public static Myinterface test2() {
		Myinterface mi = new Myinterface() {
			@Override
			public void print() {
				System.out.println("test2()메서드에서 반환된 MyInterface");
			}
		};
		return mi;
	}

	public static void main(String[] args) {
		MyClass1 mc1 = new MyClass1();
		MyClass2 mc2 = new MyClass2();
		mc1.print();
		mc2.print();

		Myinterface mi = new Myinterface() {

			@Override
			public void print() {
				System.out.println("익명 클래스로 구현");
			}
		};

		test(mc1);
		test(mi);

		mi.print();

		(new Myinterface() {

			@Override
			public void print() {
				System.out.println("선언,생성,호출을 한번에 처리");
			}
		}).print();

		Myinterface mi2 = test2();
		mi2.print();
	}
}
