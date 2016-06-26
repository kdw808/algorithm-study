import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.SynchronousQueue;

public class Fence {
	static int caseCount;
	static int gameCount;
	static int result;
	static int right;
	static int left;
	static int h;
	static int max;
	static int ANSWER[] =  new int[50];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		caseCount = Integer.parseInt(in.readLine().trim());
		for(int testCase = 0; testCase < caseCount; testCase++) {
			gameCount = Integer.parseInt(in.readLine().trim());
			max = 0;
			String[] LineValue = in.readLine().split("\\s+");
			
			for(int gameCase = 0; gameCase < LineValue.length; gameCase++){
				right = 0;
				left = 0;
				h = Integer.parseInt(LineValue[gameCase]);
				
				if(gameCase != LineValue.length - 1){
					for(int r = gameCase + 1 ; r < gameCount -1 ; r++ ){
						if( h <=  Integer.parseInt(LineValue[r])) {
							right++;
						} else {
							break;
						}
					}
				}
				
				if(gameCase != 0){
					for(int l = gameCase-1 ; l == 0 ; l-- ){
						if(h >=  Integer.parseInt(LineValue[l])) {
							left++;
						} else {
							break;
						}
					}	
				}
				

				if(right == 0 && left == 0){
					result = h;
				} else {
					result = h * (right + left + 1);
				}
				
				
				if(max < result)
					max = result;
			}
			
			ANSWER[testCase] = max;
		}
		
		for(int testCase = 0; testCase < caseCount; testCase++) {
			System.out.println(ANSWER[testCase]);
		}
	}
}