package codility.demo;

public class EquilibriumIndex {
	  int equi ( int[] A ) {
	    // write your code here
		  long lSum, rSum;
		  int j, k, n, p;
		  n = A.length;
		  for (p = 0; p < n; p++) {
			  for (j = 0, lSum=0; j <= p-1 ; j++) {
				  lSum += A[j];
			  }
			  for (k = p+1, rSum=0 ; k < n ; k++) {
				  rSum += A[k];
			  }
			  if (lSum == rSum) return p;
		  }
		  
		  return -1;
	  }
}
