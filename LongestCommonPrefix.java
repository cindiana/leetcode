package leetcod;

/*
 * 1. ["",""] should return "". If strs has "", then should just return ""
 * 2. wrong index upper boundary in while loop.
 * 3. ["abab","aba","abc"]. Problem in compareTo, typo, boundary condition error,
 */
import java.util.Arrays;

public class LongestCommonPrefix {
	public String longestCommonPrefix_2013(String[] strs) {
        if (strs.length<1) return "";
        if (strs.length==1) return strs[0];
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length-1];
        
        int i=0;
        while(i<first.length()&&i<last.length() && first.charAt(i)==last.charAt(i)) {i++;}
        return first.substring(0,i);
	}
	
	public String longestCommonPrefix(String[] strs) {
        if (strs==null || strs.length==0) {
        	return "";
        }
        if (strs.length==1) {
        	return strs[0];
        }
        WrapString[] wrappedList = new WrapString[strs.length];
        for (int i=0; i<strs.length; i++) {
        	if (strs[i]==null || strs[i].length()==0) {
        		return "";
        	}
        	wrappedList[i] = new WrapString(strs[i]);
        }
        
        Arrays.sort(wrappedList);
        
        String first = wrappedList[0].value;
        String last = wrappedList[wrappedList.length-1].value;
        int length = 0;
        while(length<first.length() && length<last.length() && first.charAt(length)==last.charAt(length)) {
        	length++;
        }
        return first.substring(0, length);
    }
	
	private class WrapString implements Comparable<WrapString>{
		String value;
		WrapString(String val) {
			value = val;
		}
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof WrapString) {
				return ((WrapString)obj).value.equals(value);
			}
			return false;
		}
		public int compareTo(WrapString obj) {
			if (value==null && obj.value==null) {
				return 0;
			}
			if (value==null && obj!=null) {
				return -1;
			}
			if (value!=null && obj==null) {
				return 1;
			}
			int i=0;
			while(i<value.length() && i<obj.value.length()) {
				if (value.charAt(i)<obj.value.charAt(i)){
					return -1;
				}
				else if (value.charAt(i)>obj.value.charAt(i))
				{
					return 1;
				}
				i++;
			}
			if (value.length() ==obj.value.length()) {
				return 0;
			}
			if (value.length()>obj.value.length()) {
				return 1;
			}
			return -1;
		}
	}
	
	public static void main(String[] args) {
		LongestCommonPrefix lcp = new LongestCommonPrefix();
		String[] input = new String[] {"abab","aba","abc"};
		System.out.println(lcp.longestCommonPrefix(input));
	}
}
