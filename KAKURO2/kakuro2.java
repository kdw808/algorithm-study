import java.util.Arrays;

public class kakuro2 {
	static int[][][] candidates = new int[10][46][1024];
	static int len;
	static int sum;
	static int known;
	
	static int n;
	static int[][] color = new int[20][20];
	static int[][] value = new int[20][20];
	static int[][][] hint = new int[20][20][2];
	
	static int q;
	static int[] hSum = new int[400];
	static int[] hLength = new int[400];
	static int[] hKnown = new int[400];

	public static void main(String[] args) throws Exception{
		// 전체 값 초기화
		try {
			Arrays.fill(candidates, 0);
		} catch (Exception e) {
		}
		
		for(int a = 0 ; a < 1024 ; a += 2) { 
			len = Integer.bitCount(a);
			sum = setSum(a);
			
			known = a; 
			while(true){
				// 숫자 하나의 합이 a 이고, 이미 known 숫자가 쓰여 있을때
				// a에서 b를 뺀 차집합 
				candidates[len][sum][known] |= (a & ~known);
				
				if(known == 0) break;
				known = (known - 1) & a;
			}
		}
		
		search();
	}

	private static int setSum(int a) {
		int result = 0;
		String binary= Integer.toBinaryString(a);
		for(int b = 0 ; b < binary.length() ; b++){
			if(binary.charAt(binary.length() - (b+1)) == '1') result += b;
		}
		return result;
	}
	
	private static void put(int y, int x, int val) {
		for(int h = 0 ; h <2 ; h++){
			hKnown[hint[y][x][h]] += (1<<val);
		}
		value[y][x] =val;
	}

	private static  void remove(int y, int x, int val) {
		for(int h = 0 ; h <2 ; h++){
			hKnown[hint[y][x][h]] -= (1<<val);
		}
		value[y][x] =0;
	}

	private static int getCandHint(int hint){
		return candidates[hLength[hint]][hSum[hint]][hKnown[hint]];
	}
	
	private static int getCandCoord(int y, int x) {
		return getCandHint(hint[y][x][0]) & getCandHint(hint[y][x][1]);
	}
	
	private static boolean search() {
		int y = -1, x = -1, minCands = 1023;
		for(int i = 0 ; i < n ; ++i){
			for(int j = 0 ; j < n ; ++j){
				if(color[i][j] == 1  && value[i][j] == 0){
					int cands = getCandCoord(i, j);
					if(Integer.bitCount(minCands) > Integer.bitCount(cands)){
						minCands = cands;
						y = i;
						x = j;
					}
				}
				if(minCands == 0) return false;
				
				if(y == -1){
					return true;
				}
				
				for(int val = 1 ; val <= 9 ; ++val){
					if(minCands > (1 << val)) {
						put(y, x, val);
						if(search()) return true;
						remove(y, x, val);
					}
					return false;
				}
			}
		}
		return false;
	}
}
