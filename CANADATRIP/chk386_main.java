import java.util.Scanner;

public class Canadatrip {

    static int n, k; // 도시수, x번째 표지판

    static int[] l = new int[5000]; // 시작점에서 도시까지 거리
    static int[] m = new int[5000]; // 표지판이 나오는 시작점
    static int[] g = new int[5000]; // 표지판 간격

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int repeat = Integer.parseInt(sc.nextLine());
        int[] city = new int[repeat];
        int[] sign = new int[repeat];
        int[][][] in = new int[repeat][][];

        for (int i = 0; i < repeat; i++) {
            String[] tmp = sc.nextLine().split(" ");
            city[i] = Integer.parseInt(tmp[0]);
            sign[i] = Integer.parseInt(tmp[1]);

            int[][] t = new int[city[i]][3];

            for (int x = 0; x < city[i]; x++) {
                int[] input = new int[3];
                String[] tmp2 = sc.nextLine().split(" ");
                input[0] = Integer.parseInt(tmp2[0]);
                input[1] = Integer.parseInt(tmp2[1]);
                input[2] = Integer.parseInt(tmp2[2]);

                t[x] = input;
            }

            in[i] = t;
        }

        long s = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            n = city[i];
            k = sign[i];

            for (int x = 0; x < n; x++) {
                int[] tmp = in[i][x];
                l[x] = tmp[0];
                m[x] = tmp[1];
                g[x] = tmp[2];
            }

            System.out.println(optimize());
            l = new int[5000];
            m = new int[5000];
            g = new int[5000];
        }
    }

    public static boolean decision(int dist) {
        int ret = 0;

        for (int i = 0; i < n; ++i) {
            if (dist >= l[i] - m[i]) {
                ret += (Math.min(dist, l[i]) - (l[i] - m[i])) / g[i] + 1;
            }
        }

        return ret >= k;
    }

    public static int optimize() {
        int low = -1;
        int high = 8030001;

        while (low + 1 < high) {
            int mid = (low + high) / 2;
            if (decision(mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return high;
    }
}
