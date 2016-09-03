import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JaehaSafe {

    static List<String[]> in = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        int cnt = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < cnt; i++) {
            int size = Integer.parseInt(bufferedReader.readLine());
            String[] ary = new String[size + 1];

            for (int x = 0; x < size + 1; x++) {
                String tmp = bufferedReader.readLine();
                ary[x] = tmp;
            }

            in.add(ary);
        }

        for (String[] ary : in) {
            int shiftCount = 0;

            for (int i = 0; i < ary.length - 1; i++) {

                String first = ary[i];
                String second = ary[i + 1];

                if (i % 2 == 0) {
                    shiftCount += getShiftCount(first, second);
                } else {
                    shiftCount += getShiftCount(second, first);
                }
            }

            System.out.println(shiftCount);
        }
    }

    static int getShiftCount(String first, String second) {
        String first2 = first + first;
        int length = first.length();

        for (int i = 1; i < length; i++) {
            int end = length * 2 - i;
            int start = length - i;

            if (first2.substring(start, end).equals(second)) {

                return i;
            }
        }

        return 0;
    }

    // 접미사
    public static class SuffixComparator {

        String str;

        public SuffixComparator(String str) {
            this.str = str;
        }

        boolean operator(int i, int j) {
            return true;
        }
    }

}
