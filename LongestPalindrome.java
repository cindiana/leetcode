package leetcod;
/*
 * 0-error code first pass ^^
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s==null) return null;
        if (s.length()==0) return String.valueOf("");
        if (s.length()==1) return s;
        
        int i = s.length()/2;
        int currentMax = 0;
        String maxPalindrome = null;
        
        while(i>=currentMax/2) {
            String thisPalindrome = palindromeFromHere(s, i);
            if (thisPalindrome.length()>currentMax) {
                maxPalindrome = thisPalindrome;
                currentMax = thisPalindrome.length();
            }
            i--;
        }
        i = s.length()/2+1;
        while(i<=s.length() - currentMax/2) {
            String thisPalindrome = palindromeFromHere(s, i);
            if (thisPalindrome.length()>currentMax) {
                maxPalindrome = thisPalindrome;
                currentMax = thisPalindrome.length();
            }
            i++;
        }
        return maxPalindrome;
    }
    
    /*
     * 2 cases: 1)use i as center 2)use space between i and i-1 as center
     */
    private String palindromeFromHere(String s, int i) {
        int step = 1;
        int len1 = 0;
        while((step+i)<s.length() && (i-step)>=0 && s.charAt(i+step)==s.charAt(i-step)) {
            step ++;
        }
        if (step>1) {
            len1 = (step-1)*2 + 1;
        }
        
        int step2 = 1, len2 = 0;
        while((step2-1+i)<s.length() && (i-step2)>=0 && s.charAt(i+step2-1)==s.charAt(i-step2)){
            step2++;
        }
        if (step2>1) {
            len2 = (step2-1)*2;
        }
        
        if (len1>len2) {
            return s.substring(i-step+1, i+step);
        }
        else 
        {
            return s.substring(i-step2+1, i+step2-1);
        }
    }
}
