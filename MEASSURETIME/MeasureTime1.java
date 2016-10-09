//binary index tree(BIT) : O(logN)
// 전체 시간복잡도 O(NlogM)
public class MeasureTime1 {

    public static void main(String[] args) {
        int[] A = {5, 1, 4, 3, 2};

        System.out.println(countMoves(A));

        int[] B = {7, 8, 6, 6, 1, 9, 4, 4, 3, 10};
        System.out.println(countMoves(B));

    }

    public static long countMoves(int[] A) {
        FenwickTree tree = new FenwickTree(1000000);
        long ret = 0;

        for (int i = 0; i < A.length; i++) {
            ret += tree.rangeSum(999999) - tree.rangeSum(A[i]);

            tree.add(A[i], 1);
        }

        return ret;
    }

    public static class FenwickTree {

        int[] tree;

        public FenwickTree(int size) {
            tree = new int[size + 1];
        }

        // 부분합(tree[1] ~ tree[index]
        public int rangeSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];

                index -= index & (-index);
            }

            return sum;
        }

        public void add(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;

                index += index & (-index);
            }
        }
    }
}
