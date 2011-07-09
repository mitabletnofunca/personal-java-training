package codility.task;
import java.util.HashSet;


class ComplementaryPairs {
  
  private static String palindrome;
  public static void main(String[] args) {
    
    int array[] = {4,5};
    int a = complementary_pairs(6, array);
    System.out.println(a);
    
    int array2[] = {4,5};
    int b = complementary_pairs(4, array2);
    System.out.println("b = " + b);
   }

  static int complementary_pairs ( int k,int[] A ) {
    // find count of complementary pairs from array A.
    int count = 0;
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        if (A[j] + A[i] == k) {
          count++;
        }
      }
    }
    return count;
  }
}
