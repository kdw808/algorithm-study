import java.util.ArrayDeque;
import java.util.Queue;

public class SpaceSignal {

    public static void main(String[] args) {
        System.out.println(countRanges(8791, 20));
        System.out.println(countRanges(5265, 5000));
        System.out.println(countRanges(3578452, 5000000));

    }

    public static int countRanges(int k, int n) {
        RandomNumber randomNumber = new RandomNumber();
        // 현재 구간에 들어 있는 숫자들을 저장하는 큐
        Queue<Integer> range = new ArrayDeque<>();
        int ret = 0, rangeSum = 0;

        for (int i = 0; i < n; i++) {
            // 구간에 숫자를 추가한다.
            int newSignal = randomNumber.next();
            rangeSum += newSignal;
            range.add(newSignal);

            // 구간의 합이 k를 초과하는 동안 구간에서 숫자를 뺸다
            while (rangeSum > k) {
                rangeSum -= range.peek();
                range.poll();
            }

            if (rangeSum == k)
                ret++;

        }

        return ret;
    }

    public static class RandomNumber {

        long seed;

        public RandomNumber() {
            seed = 1983;
        }

        public int next() {
            long ret = seed;
            seed = (long)(((seed * 214013) + 2531011) % Math.pow(2, 32));
            Long tmp = ret % 10000 + 1;

            return tmp.intValue();
        }
    }

}
