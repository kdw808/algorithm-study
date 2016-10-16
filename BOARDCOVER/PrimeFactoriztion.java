import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by haekyu.cho@nhnent.com on 2016. 8. 7..
 */
public class PrimeFactoriztion {

    public static void main(String[] args) {
        Integer n = 528;
        Queue<Integer> ret = new ArrayDeque<>();

        Double tmp = Math.sqrt(18);
        int sqrtn = tmp.intValue();

        for (int div = 2; div < sqrtn; ++div) {
            while (n % div == 0) {
                n /= div;
                ret.add(div);
            }

            if (n > 1)
                ret.add(n);
        }

        System.out.println(ret.size());

    }
}
