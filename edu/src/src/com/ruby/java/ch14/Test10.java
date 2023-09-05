package com.ruby.java.ch14;

interface StringFunc5 {
	String modify(String s);
}

public class Test10 {
	static String func(String s) {
		String result = "";
		char c;
		for(int i =0; i< s.length(); i ++) {
			c = s.charAt(i);
			if( c == ',') 
				result += " ";
			else
				result += c;
		}
		return result;
	}

	public static void main(String[] args) {
		StringFunc5 sf = Test10::func;
		
		String str = "Korea,Australia,Japan,Germany,Spain,Turkey";
		String result = sf.modify(str);
		System.out.println(result);
		
		String str2 = "11,22,33,44,55,66";
		result = sf.modify(str2);
		System.out.println(result);
	}
}
