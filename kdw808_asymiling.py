import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AsymTiling {
 static int caseCount;
 static int tileCount[] = new int[50];
 static int ANSWER[] = new int[101];

 public static void main(String[] args) throws Exception {
  Arrays.fill(ANSWER, -1);
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  caseCount = Integer.parseInt(in.readLine().trim());

  for (int a = 0; a < caseCount; a++) {
   tileCount[a] = Integer.parseInt(in.readLine().trim());
  }
  
  for (int a = 0; a < caseCount; a++) {
   if(tileCount[a] % 2 ==1){
    System.out.println((match(tileCount[a]) - match((tileCount[a]-1)/2)) % 1000000007);
   } else {
    System.out.println((match(tileCount[a])  - match(tileCount[a]/2) - match((tileCount[a]/2) -1)) % 1000000007);
   }
  }
 }

 public static int match(int tileCount) {
  if(tileCount <= 1)
   return 1;
  
  if(ANSWER[tileCount] != -1)
   return ANSWER[tileCount];
  
    
  return ANSWER[tileCount] = (match(tileCount-1) + match(tileCount-2));
 }
} 
