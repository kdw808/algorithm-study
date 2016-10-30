import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class RunningMedian {
	static int caseCount;
	static long numArray[][] =  new long[20][200000];
	static int count[] = new int[200000];
	static int mid[][] = new int[20][200000];
	static long a[] = new long[10000];
	static long b[] = new long[10000];
	
	public static void main(String[] args) throws Exception {
		Long temp1;
		Long temp2;
		long result = 0;
		
		Scanner scan = new Scanner(System.in);
		caseCount = scan.nextInt();
		
		PriorityQueue<Long> leftTree = new PriorityQueue<Long>(1, Collections.reverseOrder());
		PriorityQueue<Long> rightTree = new PriorityQueue<Long>();
		
		for(int testCase = 0; testCase < caseCount; testCase++) {
			count[testCase] = scan.nextInt();
			a[testCase] = scan.nextInt();
			b[testCase] = scan.nextInt();
		}
		
		for(int caseNum = 0; caseNum < caseCount; caseNum++){
			result = 0;
			
			for(int testCase = 0; testCase < count[caseNum]; testCase++){
				// 수열 생성
				if(testCase == 0)
					numArray[caseNum][testCase] = 1983;
				else
					numArray[caseNum][testCase] = ((numArray[caseNum][testCase-1] * a[caseNum]) + b[caseNum]) % 20090711;
				
//				System.out.println("testCase : " + testCase + " " + numArray[caseNum][testCase]);

				// 큐에 입력
				if(leftTree.size() == rightTree.size())
					rightTree.add(numArray[caseNum][testCase]);
				else
					leftTree.add(numArray[caseNum][testCase]);
				
				// left의 최대값과 right의 최소값을 비교한다.
				if(!leftTree.isEmpty() && !rightTree.isEmpty() && leftTree.peek() > rightTree.peek()){
					temp1 = leftTree.peek();
					temp2 = rightTree.peek();
					
					leftTree.poll();
					rightTree.poll();
					
					leftTree.add(temp2);
					rightTree.add(temp1);
				}
				
				if(leftTree.size() == rightTree.size())
					result = ((result + leftTree.peek()) % 20090711) ;
				else
					result = ((result + rightTree.peek()) % 20090711) ;
			}
			System.out.println(result);
		}		
	}
}