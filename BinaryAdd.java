package leetcod;
import java.util.ArrayList;
import java.util.List;

/*
 * 1. forgot to take care of boundary condition. index2--
 * 2. missed the last carry for highest digit.
 * 3. after changing len to index, forgot to change >0 to >=0
 * 4. after fixing 3 for the first while loop, forgot about 2nd and 3rd while loop
 *      with the same problem
 * 5. detected 2^3 complexity too deep into coding. Should think deeper before coding.
 */
public class BinaryAdd {

    public String addBinary(String a, String b) {
        if (a == null || "".equals(a.trim())) {
            return b;
        }
        if (b == null || "".equals(b.trim())) {
            return a;
        }

        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        List reverseResult = new ArrayList();

        int index1 = c1.length - 1;
        int index2 = c2.length - 1;
        boolean carry = false;

        while (index1 >= 0 && index2 >= 0) {
            int val1 = Integer.parseInt(String.valueOf(c1[index1]));
            int val2 = Integer.parseInt(String.valueOf(c2[index2]));

            int add = val1 + val2 + (carry ? 1 : 0);// [0,3]
            carry = (add > 1);
            reverseResult.add(add % 2 == 0 ? "0" : "1");

            index1--;
            index2--;
        }        
        
        while (index1 >= 0) {
            char ch = c1[index1];
            if (carry) {
                if (ch == '0') {
                    reverseResult.add("1");
                    carry = false;
                } else {
                    reverseResult.add("0");                    
                }
            } else {
                if (ch == '0') {
                    reverseResult.add("0");
                } else {
                    reverseResult.add("1");
                }
            }
            index1--;
        }

        while (index2 >= 0) {
            char ch = c2[index2];
            if (carry) {
                if (ch == '0') {
                    reverseResult.add("1");
                    carry = false;
                } else {
                    reverseResult.add("0");                    
                }
            } else {
                if (ch == '0') {
                    reverseResult.add("0");
                } else {
                    reverseResult.add("1");
                }
            }
            index2--;
        }
        
        if (carry) {
            reverseResult.add("1");
        }
        
        StringBuffer sb = new StringBuffer();
        for(int i=reverseResult.size()-1; i>=0; i--){
            sb.append(reverseResult.get(i));
        }
        
        return sb.toString();
    }

}
