package com.ruby.java.ch10.collection;

import java.util.HashMap;
import java.util.Iterator;

public class Test06 {
	public static void main(String[] args) {
		String word[] = {"a", "b", "c", "d"};
		String meaning[] = {"asdsadsadas", "zxcxzczxzczc","fghgfhgfhfg" ,"qwqwqwqwqwq"};
		
		HashMap<String, String> dic = new HashMap<>();
		
		for(int i =0; i < word.length; i ++) {
			dic.put(word[i], meaning[i]);
		}
		System.out.println(dic);
		System.out.println(dic.size());
		
		System.out.println(dic.keySet());
		System.out.println(dic.values());
		
		Iterator<String> keys = dic.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			System.out.println(String.format("%s : %s",key,dic.get(key)));
		}
	}
}
