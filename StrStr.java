
package leetcod;
/*
 * 1. overlooked running out of range of haystack.
 * 2. substring is exclusive on the end.
 * 3. "" is in "". Misinterpreted requirement, and caused run time error.
 * 4. "" is a valid needle. Overlooked.
 */
public class StrStr {
	public String strStr_20130530(String haystack, String needle) {
        if (haystack==null || needle==null || haystack.length()<needle.length()) return null;
        if (needle.equals("")) return haystack;
        if (haystack.equals(needle)) return haystack;
        int i = haystack.indexOf(needle.charAt(0));
        while(i>=0) {
            if (haystack.length()-i<needle.length()) return null;
            boolean match = true;
            for (int j=1; j<needle.length(); j++) {
                if (haystack.charAt(i+j)!=needle.charAt(j)){
                    match = false;
                    break;
                }
            }
            if (match) return haystack.substring(i);
            i = haystack.indexOf(needle.charAt(0), i+1);
        }
        return null;
    }
	
    public String strStr(String haystack, String needle) {
        if (haystack == null || needle == null
                || haystack.length() < needle.length()) {
            return null;
        }
        if (needle.length() == 0) {
            return haystack;
        }       

        int index = haystack.indexOf(needle.charAt(0));
        while (index >= 0) {
            if (index + needle.length() > haystack.length()) {
                return null;
            }
            if (needle
                    .equals(haystack.substring(index, index + needle.length()))) {
                return haystack.substring(index);
            }
            index = haystack.indexOf(needle.charAt(0), index + 1);
        }

        return null;
    }

    public static void main(String[] args) {
        StrStr ss = new StrStr();
        System.out.println(":" + ss.strStr("a", "") + ".");
    }
}
