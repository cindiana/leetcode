package leetcod;

public class ValidPalindrome {
	public boolean isPalindrome(String s) {
        if (s==null || s.length()==1) {
            return true;
        }
        char[] chars = s.toLowerCase().toCharArray();
        int i=0; int j=chars.length-1;
        
        while(i<j)
        {
            while (i<j && !(chars[i]<='z'&& chars[i]>='a'||chars[i]>='0'&&chars[i]<='9')) {
                i++;
            }
            while(i<j && !(chars[j]<='z'&& chars[j]>='a'||chars[j]>='0'&&chars[j]<='9')) {
                j--;
            }
            if (i>=j) return true;
            if (chars[i]!=chars[j]) return false;
            i++;
            j--;
        }
        return true;
    }
}
