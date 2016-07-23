
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Main {
	public static int case_num;
	public static int N;
	public static int M;
	public static int[][] food_friend;
	public static Vector<Integer> friend_food[];
	public static Map<String, Integer> friend_map;

	public static int best;

	public static void main(String[] args) {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);

		try {
			case_num = Integer.parseInt(br.readLine());
			for (int i = 0; i < case_num; i++) {
				start(br);
			}
		} catch (NumberFormatException | IOException e) {
		}

	}

	private static void start(BufferedReader br) throws IOException {
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);

		String[] f_list = br.readLine().split(" ");
		friend_map = new HashMap<String, Integer>();

		Vector<Integer> chosen_friend = new Vector<Integer>(4);

		food_friend = new int[M][];

		// 선택된 친구 체크용 변수
		friend_food = new Vector[N];
		for (int i = 0; i < N; i++) {
			friend_map.put(f_list[i], i);
			chosen_friend.add(0);
			friend_food[i] = new Vector<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			f_list = br.readLine().split(" ");
			int f_cnt = Integer.parseInt(f_list[0]);
			food_friend[i] = new int[f_cnt];
			for (int j = 1; j < f_cnt + 1; j++) {
				food_friend[i][j - 1] = friend_map.get(f_list[j]);
				friend_food[food_friend[i][j - 1]].add(i);
			}
		}

		best = M;
		solve2(chosen_friend, 0);
		System.out.println(best);
	}

	private static void solve2(Vector<Integer> chosen_friend, int chosen_cnt) {
		if (chosen_cnt >= best)
			return;

		int first = 0;
		while (first < N && chosen_friend.get(first) > 0)
			first++;

		if (first == N) {
			best = chosen_cnt;
			return;
		}

		for (int i = 0; i < friend_food[first].size(); ++i) {
			int food = friend_food[first].get(i);
			for (int j = 0; j < food_friend[food].length; j++) {
				chosen_friend.set(food_friend[food][j], chosen_friend.get(food_friend[food][j]) + 1);
			}
			solve2(chosen_friend, chosen_cnt + 1);
			for (int j = 0; j < food_friend[food].length; j++) {
				chosen_friend.set(food_friend[food][j], chosen_friend.get(food_friend[food][j]) - 1);
			}
		}
	}

}
