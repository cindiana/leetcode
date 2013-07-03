package leetcod;

import java.util.ArrayList;
import java.util.List;
/*
 * 1. sanity check bug: c<'0' instead of c<0
 * 2. fail "123456789", "987654321". int*int will overflow long.
 * 3. Integer.valueOf used on char will return ascii value of char. use -'0' instead.
 * 4. "123456789", "987654321" exposed many index misuses in the part computing rows[][].
 * 5. extra 0 prefixing result. err in counting total digits.
 * 6. fail big:"29612", "60594950""0017617659400""1794337659400". missed taking out loop condition when fixing bug.
 * 7. fail "508591", "41609063""691092841""21161994960233". Can't use negative long to detect overflow.
Ê* 8. can use negative to detect long overflow. but should parse n1 and n2 to long instead of int.
 * 9. exceed time limit: speed up by avoiding exception handling.
 * 10. extra 0 prefixing result. must handle after all summing up is done.
 */
public class MultiplyStrings {
	public String multiply_20130618(String num1, String num2) {
        int[] r = new int[num1.length() + num2.length()];
        
        for (int i=0; i<num1.length(); i++) {
            int digit1 = num1.charAt(num1.length()-1-i)-'0';
            int carry = 0;
            for (int j=0; j<num2.length(); j++){    
                int digit2 = num2.charAt(num2.length()-1-j)-'0';
                int m = digit1*digit2;
                r[r.length-1-i-j]+= (m+carry);
                carry = r[r.length-1-i-j]/10;
                r[r.length-1-i-j] = r[r.length-1-i-j]%10;
            }
            r[r.length-1-num2.length()-i]+=carry;
        }
        
        int start = r.length-1;
        for (int i=0; i<r.length; i++) {
            if (r[i]!=0) {
                start = i;
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i=start; i<r.length; i++) {
            sb.append(r[i]);
        }
        return sb.toString();
    }
	public String multiply_20130608(String num1, String num2) {
		if (num1==null || num1.length()==0 || num2==null || num2.length()==0) return "";
        String temp = "";
        
        for (int i=num2.length()-1; i>=0; i--) {
            StringBuffer sb = new StringBuffer();
            int carry = 0;
            int num2Digit = num2.charAt(i)-'0';
            for (int j=num1.length()-1; j>=0; j--) {
                int num1Digit = num1.charAt(j)-'0';
                int digit = num2Digit*num1Digit + carry;
                sb.insert(0, digit%10);
                carry = digit/10;
            }
            while (carry!=0) {
                sb.insert(0, carry%10);
                carry=carry/10;
            }
            if (i==num2.length()-1) { temp = sb.toString(); }
            else {
                StringBuffer newTemp = new StringBuffer();
                 for (int k=0; k<num2.length()-i-1; k++) {
                    newTemp.insert(0, temp.charAt(temp.length()-1-k));
                }
                int ptemp = temp.length()-1-(num2.length()-i-1);
                int pthis = sb.length()-1;
                int c = 0;
                while(pthis>=0 && ptemp>=0) {
                    int tempChar = temp.charAt(ptemp)-'0';
                    int thisChar = sb.charAt(pthis)-'0';
                    int digit = tempChar+thisChar+c;
                    newTemp.insert(0, digit%10);
                    c = digit/10;
                    pthis--;
                    ptemp--;
                }
                while(ptemp>=0) {
                    int digit = temp.charAt(ptemp)-'0' + c;
                    c = digit/10;
                    newTemp.insert(0, digit%10);
                    ptemp--;
                }
                while(pthis>=0) {
                    int digit = sb.charAt(pthis)-'0' + c;
                    c = digit/10;
                    newTemp.insert(0, digit%10);
                    pthis--;
                }
                while (c!=0) {
                    newTemp.insert(0, c%10);
                    c=c/10;
                }
                
                temp = newTemp.toString();
            }
        }
        StringBuffer removeHeadingZero = new StringBuffer(temp);
        while(removeHeadingZero.charAt(0)=='0' && removeHeadingZero.length()!=1) removeHeadingZero.delete(0,1);
        return removeHeadingZero.toString();
    }
	public String multiply(String num1, String num2) {
		if (num1 == null || num2 == null || num1.length() == 0
				|| num2.length() == 0) {
			return "";
		}
		boolean negative1 = false;
		if (num1.charAt(0) == '-') {
			negative1 = true;
			num1 = num1.substring(1);
		}
		for (char c : num1.toCharArray()) {
			if (c < '0' || c > '9') {
				return "";
			}
		}
		boolean negative2 = false;
		if (num2.charAt(0) == '-') {
			negative2 = true;
			num2 = num2.substring(1);
		}
		for (char c : num2.toCharArray()) {
			if (c < '0' || c > '9') {
				return "";
			}
		}
		boolean negativeResult = negative1 ^ negative2;

		if (num1.length()<10 && num2.length()<10) {//2^32 = 4294967296
			try {			
				long number1 = Long.valueOf(num1).intValue();
				long number2 = Long.valueOf(num2).intValue();
				long r = number1 * number2;		
				if (r>=0)
				{
					String result = String.valueOf(r);
					return negativeResult ? "-" + result : result;
				}
			} catch (Exception ex) {
				// proceed to raw multiply
			}
		}
		
		String result = rawMultiply(num1, num2);
		return negativeResult ? "-" + result : result;
	}

	private String rawMultiply(String num1, String num2) {
		char[] char1 = num1.toCharArray();
		char[] char2 = num2.toCharArray();
		int[][] rows = new int[char2.length][char1.length + 1];

		for (int j = char2.length-1; j>=0; j--) {
			int n2 = char2[j]-'0';
			int carryOver = 0;
			for (int i = char1.length - 1; i >= 0; i--) {
				int n1 = char1[i]-'0';
				int mul = n1 * n2 + carryOver;
				rows[char2.length-1-j][i+1] = mul % 10;
				carryOver = mul / 10;
			}
			if (carryOver > 0) {
				rows[char2.length-1-j][0] = carryOver;
			}
		}

		List<String> reversedResult = new ArrayList<String>();
		int digitsCountWOCarry = char1.length + char2.length;
		int carry = 0;
		for (int i = 0; i < digitsCountWOCarry; i++) {
			int addResult = 0;
			for (int j = 0; j <= i; j++) {
				if (j < char2.length && (char1.length - i + j)>=0 && (char1.length - i + j)<=char1.length) {
					addResult += rows[j][char1.length - i + j];
				}
			}
			addResult += carry;			
			reversedResult.add(String.valueOf(addResult % 10));			
			carry = addResult / 10;
		}
		if (carry > 0) {
			reversedResult.add(String.valueOf(carry));
		}
		if (reversedResult.get(reversedResult.size()-1).equals("0")) {
			reversedResult.remove(reversedResult.size()-1);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = reversedResult.size() - 1; i >= 0; i--) {
			sb.append(reversedResult.get(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		MultiplyStrings ms = new MultiplyStrings();
		System.out.println(ms.multiply_20130608("9311", "0"));
	}
}
