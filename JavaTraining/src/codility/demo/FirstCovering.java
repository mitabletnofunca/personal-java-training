package codility.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


class FirstCovering {
    int ps ( int[] A ) {
    	// write your code here
    	
    	ArrayList<Integer> arrA = new ArrayList<Integer>(A.length);
    	for (int i = 0; i < A.length; i++) {
    		arrA.add(A[i]);
    	}
    	
    	ArrayList<Integer> sortedA = new ArrayList<Integer>(arrA);
    	HashSet<Integer> hashSet = new HashSet<Integer>(arrA);
    	
    	Iterator<Integer> iter = hashSet.iterator();

    	int index = 0;
    	while (iter.hasNext()) {
    		
    		if (index > 0 ) {
    			index = arrA.indexOf(iter.next());
    		}
    	}
    	
    	
    	return index;
    }
}