package leetcod;
import java.util.ArrayList;
import java.util.List;

public class Palindrome {
    private static int[] convert(int i) {
        List<Integer> a = new ArrayList<Integer>();

        while (i > 0) {
            a.add(i % 10);
            i = i / 10;
        }

        int[] toreturn = new int[a.size()];
        for (int j = 0; j < a.size(); j++) {
            toreturn[j] = a.get(j).intValue();
            System.out.println(toreturn[j]);
        }
        return toreturn;
    }

    private static int reverseNumber(int i) {
        System.out.println(i);
        int rev = 0;
        while (i > 0) {
            rev = rev * 10 + i % 10;
            i /= 10;
        }
        System.out.println(rev);
        return rev;
    }

    private static String longestP(String s) {
        boolean[][] isP = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            isP[i][i] = true;
            for (int j = i + 1; i < s.length(); j++) {
                isP[i][j] = (s.charAt(i) == s.charAt(j)) && isP[i + 1][j - 1];
            }
        }
        // the most upper right isP is our answer
        int i = 0, j = s.length() - 1;
        while (i <= s.length() && j >= i) {
            if (isP[i][j]) {
                break;
            }
            //if (isP[i])

        }
        System.out.println("i=" + i + ",j=" + j + "pan:"
                + s.substring(i, j - i + 1));
    }

    public static void main(String[] args) {
        // convert(123456);
        // convert(3);
        // reverseNumber(44719819);
    }
}
