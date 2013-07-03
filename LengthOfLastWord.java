package leetcod;

public class LengthOfLastWord {
	public int lengthOfLastWord(String s) {
        if (s==null || s.length()==0) return 0;
        if (s.indexOf(' ')<0) return s.length();
        if (s.trim().length()==0) return 0;
        String[] splices = s.split(" ");
        return splices[splices.length-1].length();
    }
}
