package codility.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


class FirstCovering {
    int ps ( int[] A ) {
    	ArrayList<Integer> arrA = new ArrayList<Integer>(A.length);
    	for (int i = 0; i < A.length; i++) {
    		arrA.add(A[i]);
    	}
    	
    	HashSet<Integer> hashSet = new HashSet<Integer>(arrA);
    	Iterator<Integer> iter = hashSet.iterator();

    	int index = 0, tempIndx=0;
    	while (iter.hasNext()) {
    		
    		tempIndx = arrA.indexOf(iter.next());
    		if (tempIndx > index ) index = tempIndx;
    	}
    	
    	return index;
    }
}