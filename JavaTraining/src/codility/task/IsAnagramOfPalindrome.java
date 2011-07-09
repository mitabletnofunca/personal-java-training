package codility.task;
import java.util.HashSet;


class IsAnagramOfPalindrome {
  
  private static String palindrome;
  public static void main(String[] args) {
    setPalindrome("dooernedeevrvn");
    
    int a = isAnagramOfPalindrome("abc");
    System.out.println(a);
    int b = isAnagramOfPalindrome("dooernedeevrvn");
    System.out.println("b = " + b);
   }

  static void setPalindrome(String s) {
    palindrome = s;
  }
  
  static int isAnagramOfPalindrome ( String S ) {
    
    // Step 1: get unique characters from palindrome
    // Step 2: get unique characters from S
    // Step 3: loop through characters of S and match if 
    //         each char has a match in palindrome
    int pLen = palindrome.length();
    int sLen = S.length();
    HashSet pSet = new HashSet();
    HashSet sSet = new HashSet();
    
    // get unique characters from palindrome.
    for (int i = 0; i < pLen; i++) {
      if (!pSet.contains(palindrome.charAt(i))) {
        pSet.add(palindrome.charAt(i));
      }
    }
    
    // get unique characters from S.
    for (int i = 0; i < sLen; i++) {
      if (!sSet.contains(S.charAt(i))) {
        sSet.add(S.charAt(i));
      }
    }
    
    if (pSet.containsAll(sSet)) {
      return 1;
    } else {
      return 0;
    }
  }
}
