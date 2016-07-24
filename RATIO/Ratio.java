import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ratio {
	static int T;
	static int H, W;
	final static int MAX_HW = 20;
	final static int[][] MAP = new int[MAX_HW][MAX_HW];
	static long gameCnt;
	static long winCnt;
	static long winRate;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine().trim());

		
		for(int testCase = 0; testCase < T; testCase++) {
			String[] firstLine = in.readLine().split("\\s+");
			gameCnt = Integer.parseInt(firstLine[0]);
			winCnt = Integer.parseInt(firstLine[1]);
		
			winRate = game(gameCnt, winCnt);
		}
		System.out.println(winRate);
	}
	
	private static long rate(long n, long m) {
		return (m*100) / n; 
	}
	
	private static long game(long n, long m) {
		long max = 2000000000;
		long mid;
		long baseRate = rate(n, m);
		long l = 0;
		
		if(rate(n, m) == 100)
			return -1;

		
		while(l + 1 < max){
			mid = (l + max)/2;
			if(baseRate == rate(n + mid, m + mid)){
				l = mid; 
			}else{
				max = mid;
			}
		}
		
		return max;
	}	
}
