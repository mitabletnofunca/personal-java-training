package codility.task;
import java.util.HashSet;


class HeavyDecimal {
  
  public static void main(String[] args) {
    
    int a = heavy_decimal_count(8675, 8689);
    System.out.println(a);
   }

  static int heavy_decimal_count ( int a,int b ) {
    int count=0;
    
    int lower=a;
    int upper=b;
    if ( a > b ) {
      lower=b;
      upper=a;
    }
    
    while (lower<=upper) {
      // Get integer length.
      String s = Integer.toString(lower);
      int len=s.length();

      int intArr[] = new int[len];
      for (int i=0; i<len; i++) {
        intArr[i]=Character.getNumericValue(s.charAt(i));
      }
      
      // sum all integers.
      int sum=0;
      for (int j=0; j < intArr.length; j++) {
        sum+=intArr[j];
      }

      // get average.
      double average= (double) sum / (double) len;
      if (average>7) {
        count++;
      }
      lower++;
    }
    return count;
  }
}
