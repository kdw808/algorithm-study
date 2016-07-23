import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	// 친구의 수
	static int n;
	
	// 음식의 수
	static int m;
	static int best;
	static int friend[][];

	
	public static void solve(Vector<Integer> chose_food, int food, int chosen) {

		// 모든 음식을 다 선택했으면 종료한다.
		if (food >= m) return;
		
		// 모든 친구가 만족하면 종료
		if (isAllEat(chose_food)) {
			best = Math.min(best, chosen);
		}

		// 먹을 사람이 있으면 음식 선택 후 다음 음식 선택
		if (isEat(chose_food, food)) {
			for (int i = 0; i < friend[food].length; i++) {
				if (chose_food.get(friend[food][i]) == -1) {
					chose_food.set(friend[food][i], food);
				}
			}
			solve(chose_food, food + 1, chosen+1);
			for (int i = 0; i < friend[food].length; i++) {
				if (chose_food.get(friend[food][i]) == food) {
					chose_food.set(friend[food][i], -1);
				}
			}
		}

		// 없으면 다음 음식 선
		solve(chose_food, food + 1, chosen);

	}

	private static boolean isAllEat(Vector<Integer> chose_food){
		for (int i = 0; i < chose_food.size(); i++) {
			if(chose_food.get(i) == -1)
				return false;
		}
		return true;
	}
	
	private static boolean isEat(Vector<Integer> chose_food, int food) {
		for(int i = 0; i < friend[food].length; i++){
			if(chose_food.get(friend[food][i]) == -1 ){
				return true;
			}
		}
		return false;
	}

}