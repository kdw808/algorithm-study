import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haekyu.cho@nhnent.com
 */
public class Josephus {

    public static void main(String[] args) {
        int N = 6;
        int K = 3;
        calc(N, K);
    }

    public static void calc(int n, int k) {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }

        Integer kill = list.get(0);

        while (n > 2) {
            int index = list.indexOf(kill);
            list.remove(kill);
            if (list.size() == 2)
                break;

            if (index == list.size()) {
                kill = list.get(0);
            } else {
                kill = list.get(index);
            }

            for (int i = 0; i < k - 1; i++) {
                if (kill == list.get(list.size() - 1)) {
                    kill = list.get(0);
                } else {
                    kill = list.get(list.indexOf(kill) + 1);
                }
            }

            n--;
        }

        String result = list.stream().map(v -> String.valueOf(v)).collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
