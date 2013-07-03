package leetcod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
 * 1. Runtime err. Boundary line 31, missed by 1.
 */
public class LongestSubstringWoDup {
	public int lengthOfLongestSubstring_20130614(String s) {
        if (s==null || s.length()==0) return 0;
        int[] pos = new int[256];
        for (int i=0; i<256; i++) pos[i] = -1;
        int max = 1;
        int runningStart = 0;
        for (int i=0; i<s.length(); i++) {
            if (pos[s.charAt(i)]>=0) {                                              
                int runLen = i-runningStart;
                if (runLen>max) max = runLen;
                runningStart = Math.max(runningStart, pos[s.charAt(i)]+1); 
            }
            pos[s.charAt(i)] = i;
        }
        max = Math.max(s.length()-runningStart, max);
        return max;
    }
	
	public int lengthOfLongestSubstring_20130518(String s) {
		if (s==null || s.length()==0) return 0;
        int max = 0;
        Map<Character, Integer> run = new HashMap<Character, Integer>();
        int runStart = 0;
        
        for (int i=0;i<s.length(); i++) {
            if (!run.containsKey(s.charAt(i))) {
                run.put(s.charAt(i), i);
                max = Math.max(max, i-runStart+1);
            }
            else {
                int dupIndex = run.get(s.charAt(i));
                if (dupIndex>=runStart) {
                    runStart = dupIndex+1;
                }
                else {
                    max = Math.max(max, i-runStart+1);
                }
                run.put(s.charAt(i), i);
            }
        }
        return max;
    }


	public int lengthOfLongestSubstring(String s) {
        if (s==null || s.length()==0) return 0;
        if (s.length()==1) return 1;
        
        int maxSoFar = 0;
        int i = 0;
        
        while(i<s.length() - maxSoFar) {
        	HashSet thisRun = new HashSet();
        	thisRun.add(s.charAt(i));
        	int thisMax = 1;
        	int j=i+1;
        	while(j<s.length()&&!thisRun.contains(s.charAt(j))){
        		thisRun.add(s.charAt(j));
        		j++;
        		thisMax++;        		
        	}
        	
        	if (thisMax>maxSoFar) {
        		maxSoFar = thisMax;       		
        	}
        	
        	if (j>=s.length()-1) {
        		break;
        	}
        	int placeOfjPlusOne = s.indexOf(s.charAt(j), i);
    		i = placeOfjPlusOne+1;
        }
        
        return maxSoFar;
    }
	public static void main(String[] args) {
		LongestSubstringWoDup lwd = new LongestSubstringWoDup();
		lwd.lengthOfLongestSubstring_20130518("qguossosmlvwlmpbfrzgrjxhgubkyfqom");
	}
}
