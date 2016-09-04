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

        // 3 1 2
        //
        for (String[] ary : in) {
            int shiftCount = 0;

            for (int i = 0; i < ary.length - 1; i++) {

                String first = ary[i];
                String second = ary[i + 1];

                if (i % 2 == 0) {
                    // shiftCount += getShiftCount(first, second);
                    shiftCount += kmpSearch(first + first, second).get(0);

                } else {
                    // shiftCount += getShiftCount(second, first);
                    shiftCount += kmpSearch(second + second, first).get(0);
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

    // KMP Search
    // 시간 복잡 : O(|H|)최대, 바늘 N길이는 상관없다.
	// H의 부분 문자열로 N이 출현하는 시작 위치들을 모두 반환한다.
    // 전체 시간 복잡도는 O(|N| * [H])
    static List<Integer> kmpSearch(final String H, final String N) {
        int n = H.length(), m = N.length();
        List<Integer> ret = new ArrayList<>();
        // getPartialMatch
        List<Integer> pi = getPartialMatch(N);

        // begin = matched=0에서 부터 시작
        int begin = 0, matched = 0;

        while (begin <= n - m) {
            // 만약 짚더미(H)의 해당 글자가 바늘(N)의 해당 글자와 같다면
            if (matched < m && H.charAt(begin + matched) == N.charAt(matched)) {
                ++matched;
                // 결과적으로 m글자가 모두 일치했다면 답에 추가한다.
                if (matched == m)
                    ret.add(begin);
            } else {
                // 예외 : matched가 0인 경우에는 다음칸에서부터 계속
                if (matched == 0) {
                    ++begin;
                } else {
                    begin += matched - pi.get(matched - 1);
                    // begin을 옮겼다고 처음부터 다시 비교할 필요가 없다.
                    // 옮긴 후에도 pi.get(matched-1)만큼은 항상 일치하기 때문
                    matched = pi.get(matched - 1);
                }
            }
        }

        return ret;
    }

    // N[...i]의 접두사도 되고 접미사도 되는 문자열의 최대 길이
    // N에서 자기자신을 찾으면서 나타나는 부분 일치를 이용해 pi[]를 계산한다.
    // pi[i]=N[..i]의 접미사도 되고 접두사도 되는 문자열의 최대 길이
    // O(|N|)
    static List<Integer> getPartialMatch(final String N) {
        int m = N.length();
        List<Integer> pi = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            pi.add(0);
        }

        // KMP로 자기 자신을 찾는다
        // N을 N에서 찾는다 begin = 0이면 자기 자신을 찾아버리니깐 안됨
        int begin = 1, matched = 0;
        // 비교할 문자가 N의 끝에 도달할 때까지 찾으면서 부분 일치를 모두 기록한다

        while (begin + matched < m) {
            if (N.charAt(begin + matched) == N.charAt(matched)) {
                ++matched;
                 pi.set(begin + matched - 1, matched);

            } else {
                if (matched == 0) {
                    ++begin;
                } else {
                    begin += matched - pi.get(matched - 1);
                    matched = pi.get(matched - 1);
                }
            }
        }

        return pi;
    }

}
