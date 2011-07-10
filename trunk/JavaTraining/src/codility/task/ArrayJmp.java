package codility.task;


class ArrayJmp {
  
  int arrayJmp ( int[] A ) {

	  int cnt = 0, indx = 0;
	  while (indx < A.length) {
		  if (indx != A[indx]) {
			  indx += A[indx];
			  cnt++;
		  } else {
			  return -1;
		  }
	  }
	  return cnt;
  }
}
