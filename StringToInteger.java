package leetcod;

public class StringToInteger {
	public int atoi(String str) {
        if (str==null || str.length()==0) return 0;
        str = str.trim();
        if (str.startsWith("+")) str = str.substring(1);
        
        if (str.charAt(0)!='-' && (str.charAt(0)<'0' || str.charAt(0)>'9')) return 0;
        
        int i = 1;
        while(i<str.length() && str.charAt(i)>='0' && str.charAt(i)<='9') {
            i++;
        }
        
        
        if (str.startsWith("-") && i<=1) return 0;
        str = str.substring(0,i);
        
        int maxLen = String.valueOf(Long.MAX_VALUE).length();
        if (str.startsWith("-")) {
            if (str.length()>maxLen+1) return 0;
        }
        else if (str.length()>maxLen) return 0;
        
        
            
        long longValue = Long.parseLong(str);
        if (longValue<Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (longValue>Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int)longValue;
    }
	
	public static void main(String[] args) {
		StringToInteger atoi = new StringToInteger();
		atoi.atoi("      -11919730356x");
	}
}
