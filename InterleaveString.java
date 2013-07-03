package leetcod;

import java.util.HashMap;
import java.util.Map;

public class InterleaveString {
	
	public boolean isInterleave_20130530(String s1, String s2, String s3) {
        if (s1==null || s1.length()==0) return s2.equals(s3);
        if (s2==null || s2.length()==0) return s1.equals(s3);
        if (s1.length()+s2.length()!=s3.length()) return false;
        
            if (s3.charAt(0)==s1.charAt(0) &&
                isInterleave(s1.substring(1), s2, s3.substring(1))) return true;
            if (s3.charAt(0)==s2.charAt(0) &&
                isInterleave(s1, s2.substring(1), s3.substring(1))) return true;
            return false;
    }
	
	public boolean isInterleave_20130530DP(String s1, String s2, String s3) {
		if (s1==null || s1.length()==0) return s2.equals(s3);
        if (s2==null || s2.length()==0) return s1.equals(s3);
        if (s1.length()+s2.length()!=s3.length()) return false;
        
        boolean[][] m = new boolean[s1.length()+1][s2.length()+1];
        for (int i=1; i<=s1.length(); i++) {
            if (s3.charAt(i-1)==s1.charAt(i-1)) m[i][0] = true;
            else break;
        }
        for (int i=1; i<=s2.length(); i++) {
            if (s3.charAt(i-1)==s2.charAt(i-1)) m[0][i] = true;
            else break;
        }
        for (int i=1; i<=s1.length(); i++) {
            for (int j=1; j<=s2.length(); j++) {
                if (m[i-1][j] && s3.charAt(i+j-1)==s1.charAt(i-1) ||
                    m[i][j-1] && s3.charAt(i+j-1)==s2.charAt(j-1))            
                m[i][j] = true;
            }
        }    
        return m[s1.length()][s2.length()];
    }
	
	public static int counter = 0;
	public boolean isInterleave(String s1, String s2, String s3) {
		Map<Triple, Boolean> cache = new HashMap<Triple, Boolean>();
        return doCheck(s1, s2, s3, cache);
    }
	
	public boolean doCheck(String s1, String s2, String s3, Map<Triple, Boolean> cache) {
		if (s1==null || s1.length()==0) return s2.equals(s3);
        if (s2==null || s2.length()==0) return s1.equals(s3);
        
        if (s3.length()!=s1.length()+s2.length()) return false;
        Triple thisTriple = new Triple(s1, s2, s3);
        if (cache.containsKey(thisTriple)) {
        	return cache.get(thisTriple);
        }
        System.out.println("checking " + s1 + "/  /" + s2 + "/  /" + s3);
        counter++;
        if (counter>20) return false;
        int startIndex = 0;
        while(s3.indexOf(s1.charAt(0), startIndex) >-1) {
            int snip = s3.indexOf(s1.charAt(0), startIndex);
            if (snip<=s2.length()) {
            	if (!s2.substring(0,snip).equals(s3.substring(0,snip))){
            		break;
            	}
                if (isInterleave(s1.substring(1), s2.substring(snip), s3.substring(snip+1))) 
            	{
            		cache.put(thisTriple, Boolean.TRUE);
            		return true;            		
                }
            }
            startIndex = snip+1;
        }
        cache.put(thisTriple, Boolean.FALSE);
        return false;
	}
	
	public class Triple {
		String s1;
		String s2;
		String s3;
		public Triple(String s, String ss, String sss) {
			s1 = s;
			s2 = ss;
			s3 = sss;
		}
	}
	
	public static void main(String[] args) {
		String s1 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
		String s2 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
		String s3 = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
		InterleaveString me = new InterleaveString();
		System.out.println(me.isInterleave_20130530DP("a", "b", "ab"));
	}
}
