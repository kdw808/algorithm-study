import java.util.Scanner;

public class DragonCurve {

    final static String ZERO = "FX";
    final static String X = "X+YF";
    final static String Y = "FX-Y";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int cnt = Integer.parseInt(sc.nextLine());
        int[][] input = new int[cnt][3];

        for (int i = 0; i < cnt; i++) {
            String tmp = sc.nextLine();
            String[] ary = tmp.split(" ");

            for (int x = 0; x < ary.length; x++) {
                input[i][x] = Integer.parseInt(ary[x]);
            }
        }

        for (int i = 0; i < input.length; i++) {
            String result = recursive(ZERO, 0, input[i][0]);
            System.out.println(result.substring(input[i][1] - 1, input[i][1] - 1 + input[i][2]));
        }
    }

    public static String recursive(String exp, int cnt, int end) {
        if (cnt == end) {
            return exp;
        }

        for (int i = 0; i < exp.length(); i++) {
            String s = exp.substring(0, i);
            String e = exp.substring(i + 1, exp.length());

            if (exp.charAt(i) == 'X') {
                exp = s + X + e;
                i = i + 4;
            } else if (exp.charAt(i) == 'Y') {
                exp = s + Y + e;
                i = i + 4;
            }
        }

        return recursive(exp, cnt + 1, end);
    }
}
