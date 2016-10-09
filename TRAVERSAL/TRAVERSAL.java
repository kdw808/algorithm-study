import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TRAVERSAL {
	final static int MAX_TEST_CASE = 100;
	final static int MAX_NODE = 100;
	
	final static int[][] test_case = new int[MAX_TEST_CASE][MAX_NODE];

	public static void main(String[] args) throws Exception {
		
		String[] preOrder = null;
		String[] inOrder = null;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for(int testCase = 0; testCase < T; testCase++) {
			preOrder = in.readLine().split("\\s+");
			inOrder = in.readLine().split("\\s+");
		}
		
		for(int testCase = 0; testCase < T; testCase++) {
			printPostOrder(preOrder, inOrder);
		}
	}
	
	private static void printPostOrder(String[] preOrder, String[] inOrder) {
		if(preOrder.length == 0) return;
		
		String root = preOrder[0];
		int root_seq = 0;
		
		for(int i = 0 ;  i < inOrder.length;  i++){
			if(inOrder[i].equals(root)){
				root_seq = i;
			}
		}
		printPostOrder(slice(preOrder, 1, root_seq +1 ), slice(inOrder, 0, root_seq));
		printPostOrder(slice(preOrder, 1+root_seq, preOrder.length), slice(inOrder, 1+root_seq, inOrder.length));
		
		System.out.print(root + " ");
	}
	
	private static String[] slice(String[] a, int sIndex, int eIndex) {
		int length = eIndex - sIndex;
		String[] result = new String[length];
		
		
		for(int i = 0 ; i < length; i++){
			result[i] = a[sIndex];
			sIndex++;
		}
	
		
		return result;
		
	}
	
}
