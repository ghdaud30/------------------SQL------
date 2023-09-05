package com.ruby.java.ch13;

class Bag<T ,N >{
	private T thing;
	private N name;
	
	public Bag(T thing, N name) {
		this.thing = thing;
		this.name = name;
	}
	
	public T getThing() {
		return thing;
	}
	
	public void setThing(T thing) {
		this.thing = thing;
	}
	
	public N getName() {
		return name;
	}
	
	public void setName(N name) {
		this.name = name;
	}
	
	void showType() {
		System.out.println("T's type is " + thing.getClass().getName());
		System.out.println("N's type is " + name.getClass().getName());
	}
}

class Book{
	public String toString() {
		return "book";
	}
}
class PencilCase{}
class Notebook{}

public class BagTest {
	public static void main(String[] args) {
		Bag <Book,String> bag = new Bag<Book,String>(new Book(),"science");
		
		bag.showType();
		
		Book book = bag.getThing();
		String name = bag.getName();
		
		System.out.println("Thing is : " + book);
		System.out.println("name is : " + name);
		
		//Bag<Book> bag = new Bag<>(new Book());
		//Bag<PencilCase> bag2 = new Bag<>(new PencilCase());
		//Bag<Notebook> bag3 = new Bag<>(new Notebook());
		//bag.showType();
		//bag2.showType();
		//bag3.showType();
		
	}
}
