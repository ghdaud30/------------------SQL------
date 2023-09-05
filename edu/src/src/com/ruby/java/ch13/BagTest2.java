package com.ruby.java.ch13;

class Bag2<T extends Solid >{
	private T thing;
	private String owner;
	
	public Bag2(T thing) {
		this.thing = thing;

	}
	
	public T getThing() {
		return thing;
	}
	
	public void setThing(T thing) {
		this.thing = thing;
	}
	

	void showType() {
		System.out.println("T's type is " + thing.getClass().getName());
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	boolean isSameOwner(Bag2<?> obj) {
		if(this.owner.equals(obj.getOwner()))
			return true;
		return false;
		
	}
}

class Solid{}
class Liquid{}

class Book2 extends Solid{}
class PencilCase2 extends Solid{}
class Notebook2 extends Solid{}

class Water extends Liquid{}
class Coffee extends Liquid{}



public class BagTest2 {
	public static void main(String[] args) {
		Bag2<Book2> bag = new Bag2<>(new Book2());
		Bag2<PencilCase2> bag2 = new Bag2<>(new PencilCase2());
		
		bag.setOwner("kim");
		bag2.setOwner("kim");
		
		boolean result = bag.isSameOwner(bag2);
		if(result) System.out.println("same");
		else System.out.println("Difference");
	}
}