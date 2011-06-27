package com.codility;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		String a = "abc";
		ArrayList<String> strArr = new ArrayList();
		strArr.add("abc");
		strArr.add("abc");
		
		System.out.println(Collections.frequency(strArr, a));
	}
}
