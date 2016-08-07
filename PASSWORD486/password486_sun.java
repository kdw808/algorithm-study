import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {

	static int n;
	static int low;
	static int hi;
	// i번째 정수의 약수의 갯수
	static int[] factors;

	private static void make() {
		int max = 10000001;
		factors = new int[max];
		for (int div = 1; div <= max; ++div) {
			for (int mul = div; mul < max; mul += div) {
				//System.out.print(mul + " ");
				factors[mul] ++;
			}
		}
		//logAll();
	}

	private static void solve() {
		int re = 0;
		for (int i = low; i <= hi; ++i) {
			if (factors[i] == n)
				re++;
		}
		System.out.println(re);
	}

	private static void logAll() {

		System.out.println("\n-----------------------------------------------------------");
		System.out.print("i\t\t");
		for (int i = 0; i <=10; ++i) {
			System.out.format("%3d|", i + low);
		}
		System.out.println();
		System.out.print("factor[" + (hi - low) + "]\t");
		for (int i = 0; i <= 10; ++i) {
			System.out.format("%3d|", factors[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		//long start = new Date().getTime();

		// sysin
		InputStreamReader inr = new InputStreamReader(System.in);
		//InputStreamReader inr = new InputStreamReader(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(inr);

		make();
		// case수 읽음.
		int c = Integer.parseInt(br.readLine());
		for (int i = 0; i < c; i++) {
			String[] tokens = br.readLine().split(" ");
			n = Integer.parseInt(tokens[0]);
			low = Integer.parseInt(tokens[1]);
			hi = Integer.parseInt(tokens[2]);
			solve();
		}

		//System.out.println("Time : " + (new Date().getTime() - start));
	}

}