package com.ruby.java.ch10.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test05 {
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		
		list.add("seoul");
		list.add("cairo");
		list.add("london");
		list.add(1,"LA");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
		
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next() + " > ");
		}
		
		System.out.println();
		
		list.sort(null);
		Iterator<String> iter2 = list.iterator();
		while(iter2.hasNext()) {
			System.out.print(iter2.next() + " > ");
		}

	}
}
