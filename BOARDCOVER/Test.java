import java.util.List;

/**
 * Created by haekyu.cho@nhnent.com on 2016. 9. 4..
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> pi = JaehaSafe.getPartialMatch("aabaabac");

        System.out.println(pi);
    }
}
