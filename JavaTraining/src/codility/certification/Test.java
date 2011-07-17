package codility.certification;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		String a = "abc";
		ArrayList<String> strArr = new ArrayList();
		strArr.add("abc");
		strArr.add("abc");
		
		System.out.println(Collections.frequency(strArr, a));
		
		Integer b = new Integer(1);
		ArrayList<Integer> intArr = new ArrayList();
		intArr.add(1);
		intArr.add(2);
		intArr.add(1);
		
		System.out.println(Collections.frequency(intArr, b));
	}
}
