package leetcod;
/*
 * 1. Can't  handle all ''. Missing index non-negative check in first while loop.
 */
public class LastWord {
    public int lengthOfLastWord(String s) {
        if (s==null || s.length()==0) {
            return 0;
        }
        int index = s.length()-1;
        int charCount = 0;
        while(index>=0 && s.charAt(index)==' '){
            index--;
        }
        
        while(index>=0 && s.charAt(index--)!=' ') {
            charCount++;
        }
        
        return charCount;
    }
    
    public static void main(String[] args) {
        LastWord lw = new LastWord();
        System.out.println(lw.lengthOfLastWord("     "));
    }
}
