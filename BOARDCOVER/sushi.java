import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by haekyu.cho@nhnent.com on 2016. 7. 10..
 */
public class sushi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = Integer.parseInt(sc.nextLine());

        int[][] input = new int[cnt][2];
        List list = new ArrayList();

        for (int i = 0; i < cnt; i++) {
            String tmp = sc.nextLine();
            String[] ary = tmp.split(" ");

            for (int x = 0; x < ary.length; x++) {
                input[i][x] = Integer.parseInt(ary[x]);
            }

            int menuCount = input[i][0];
            int[][] menu = new int[menuCount][2];

            for (int x = 0; x < menuCount; x++) {
                tmp = sc.nextLine();
                ary = tmp.split(" ");

                menu[x][0] = Integer.parseInt(ary[0]);
                menu[x][1] = Integer.parseInt(ary[1]);
            }

			list.add(menu);
        }

		//풀자



        System.out.println(input);
    }

	public static void calc(int[][] menu, int price) {



	}
}
