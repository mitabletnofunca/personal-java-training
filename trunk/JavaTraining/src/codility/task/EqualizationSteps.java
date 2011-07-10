package codility.task;

import java.util.ArrayList;


class EqualizationSteps {
  
	int equalization_steps ( int[] A ) {
		
		int sum = 0;
		for (int i = 0; i < A.length ; i++) {
			sum += A[i];
		}
		if ( sum % 2 != 0  )return -1;
		
		int avg = (int) Math.ceil( (double) sum / (double) A.length);
		int cnt = 0;
		while (true) {
			int x = 0;
			for (int i = 0 ; i < A.length ; i++) {
				if (A[i] > avg) {
					A[i]--;
					x--;
				} else {
					A[i]++;
					x++;
				}
			}

			boolean areAllEqual = true;
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < A.length ; ++i) {
				if (!arr.contains(A[i])) arr.add(A[i]);
				if (arr.size() > 1) {
					areAllEqual = false;
					break;
				}
			}
			
			avg = (int) Math.ceil( (double)( sum + x ) / (double)A.length);
			if (( sum + x ) % 2 != 0  )return -1;
			cnt++;
			if (areAllEqual) break;
		}
		
		return cnt;
	}
}
