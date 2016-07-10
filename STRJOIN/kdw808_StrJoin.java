import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.concurrent.SynchronousQueue;

public class StrJoin {
	static int caseCount;
	static int numCount;
	static int result;
	static int sum;
	static int h;
	static int ANSWER[] =  new int[50];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		caseCount = Integer.parseInt(in.readLine().trim());
		PriorityQueue<Integer> priQueue = new PriorityQueue<Integer>();
		
		for(int testCase = 0; testCase < caseCount; testCase++) {
			result = 0;
			numCount = Integer.parseInt(in.readLine().trim());
			String[] LineValue = in.readLine().split("\\s+");
			
			for(int gameCase = 0; gameCase < numCount; gameCase++){
				priQueue.add(Integer.parseInt(LineValue[gameCase]));
			}
			
			while(priQueue.size() > 1){
				sum = 0;
				sum += priQueue.poll(); 
				sum += priQueue.poll();
				
				result += sum;
				
				priQueue.add(sum);
			}
			
			ANSWER[testCase] = result;
			
			priQueue.clear();
		}
		
		for(int testCase = 0; testCase < caseCount; testCase++) {
			System.out.println(ANSWER[testCase]);
		}
	}
}