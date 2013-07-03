package leetcod;

public class LongestPalindromicSubstring {
	public String longestPalindrome_20130623(String s) {
        if (s==null || s.length()==0) return s;
        int max = 1;
        String maxP = String.valueOf(s.charAt(0));
        
        int center = 1;// split between (center-1, center)
        while(center*2>max && (s.length()-center)*2>max) {
            int l =center-1;
            int r =center;
            while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)) {
                if (r-l+1>max) {
                    max = r-l+1;
                    maxP = (r==s.length()? s.substring(l):s.substring(l,r+1));
                }
                l--; r++;
            }
            center++;         
        }
        center = max==1? 1: max/2;// split at center
        while(center*2+1>max && (s.length()-center)*2-1>max) {
            int l =center-1;
            int r =center+1;
            while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)) {
                if (r-l+1>max) {
                    max = r-l+1;
                    maxP = (r==s.length()? s.substring(l):s.substring(l,r+1));
                }
                l--; r++;
            }
            center++;         
        }
        
        return maxP;
    }
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return "";
		int longest = 1;
		String pal = s.substring(0, 1);
		for (int i = 1; i < s.length() - longest / 2; i++) {
			int c = 0;
			while (i - c - 1 >= 0 && i + c < s.length()
					&& s.charAt(i - c - 1) == s.charAt(i + c)) {
				c++;
			}
			if (c * 2 > longest) {
				longest = c * 2;
				if (i + c >= s.length())
					pal = s.substring(i - c);
				else
					pal = s.substring(i - c, i + c);
			}
			c = 0;
			while (i - c - 1 >= 0 && i + c + 1 < s.length()
					&& s.charAt(i - c - 1) == s.charAt(i + c + 1)) {
				c++;
			}
			if (c * 2 + 1 > longest) {
				longest = c * 2 + 1;
				if (i + c + 1 >= s.length())
					pal = s.substring(i - c);
				else
					pal = s.substring(i - c, i + c + 1);
			}
		}
		return pal;
	}

	public static void main(String[] args) {
		LongestPalindromicSubstring me = new LongestPalindromicSubstring();
		me.longestPalindrome("bb");
	}
}
