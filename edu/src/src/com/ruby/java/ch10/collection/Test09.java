package com.ruby.java.ch10.collection;

import java.util.Map;
import java.util.TreeMap;

public class Test09 {

	public static void main(String[] args) {
		TreeMap<String, String> users = new TreeMap<>();
		
		users.put("20181707", "Hong");
		users.put("20191707", "Kong");
		users.put("20281707", "Qong");
		users.put("20381707", "Fong");
		users.put("20481707", "Eong");
		
		System.out.println(users);
		
		Map.Entry<String,String> entry = null;
		
		entry = users.firstEntry();
		System.out.print("early is " + entry);
		
		System.out.println("");
		
		entry = users.lastEntry();
		System.out.print("last is " + entry);
		
	}

}
