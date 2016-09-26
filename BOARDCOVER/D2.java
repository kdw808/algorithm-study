import java.util.ArrayList;
import java.util.List;

public class D2 {

    public static void main(String[] args) {
        String p = "XYZ";
        String q = "XY";
        String r = "6PP";

        int a1 = p.length();
        int a2 = q.length();
        int a3 = r.length();

        int r1 = r.length();

        // 자리수 3자리 + 2자리 = 3자리
        // 2자리 + 2자리 = 3자리

        int x = 0;
        int y = 0;
        int z = 0;

        int retP = 0;

        int left = x * ((int)Math.pow(10, 2)) + y * ((int)Math.pow(10, 1)) + z * ((int)Math.pow(10, 0)) + x * ((int)Math.pow(10, 1)) + y * ((int)Math.pow(10, 0));
        int right = 6 * ((int)Math.pow(10, 2)) + retP * ((int)Math.pow(10, 1)) + retP * (int)Math.pow(10, 0);

        char[] retAry = new char[3];
        retAry[0] = 6;
        retAry[1] = 'P';
        retAry[2] = 'P';

        // 결과값을 먼저 구한다
        List<Integer> ret = new ArrayList<>();

        for (int P = 0; P < 10; P++) {
            // ret.add(retAry[0] * )
        }
    }

    public static int calcResult(char[] retAry) {
        for (int i = 0; i < retAry.length; i++) {
            int start = 0;
            if (i == 0) {
                start = 1;
            }

            // 숫자
            if (retAry[i] >= 0 || retAry[i] <= 9) {

            } else {// 변수

            }
        }

        return 0;
    }
}
