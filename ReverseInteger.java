package leetcod;

public class ReverseInteger {
	public int reverse_20130518(int x) {
        boolean sign = false;
        if (x<0) sign = true;
        
        long pos = Math.abs((long)x);
        char[] xchar = String.valueOf(pos).toCharArray();
        for (int i=0; i<xchar.length/2; i++) {
            char temp = xchar[i];
            xchar[i] = xchar[xchar.length-1-i];
            xchar[xchar.length-1-i] =temp;
        }
        long reversed = Long.parseLong(new String(xchar));
        if (reversed>Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (sign) return ((int)reversed^-1)+1;
        return (int)reversed;
    }
	
	public int reverse(int x) {
        boolean negative = x<0;
        if (negative) x = x*(-1);
        
        String strInt = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i=strInt.length()-1; i>=0; i--) {
        	sb.append(strInt.charAt(i));
        }
        
        int newInt = Integer.valueOf(sb.toString());
        return negative? (-1)*newInt: newInt;
    }
}
