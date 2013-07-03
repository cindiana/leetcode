package leetcod;

public class SubstringWithConcatOfAllWords {
	public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (S==null || L==null) return result;
        int substringLen = L[0].length()*L.length;
        HashMap<String,Integer> dict = new HashMap<String,Integer>();
        for (String l : L) {
            if (dict.containsKey(l)) {
                dict.put(l, dict.get(l)+1);
            }
            else{
                dict.put(l, 1);
            }
        }
        
        for (int i=0; i<=S.length()-substringLen; i++) {
            HashMap<String,Integer> runDict = (HashMap<String,Integer>)dict.clone();
            int runIndex = i;
            
            while(runIndex<S.length()) {
                String runner = S.substring(runIndex, runIndex+L[0].length());
                if (!runDict.containsKey(runner)) break;
                if (runDict.get(runner)==1) runDict.remove(runner);
                else runDict.put(runner, runDict.get(runner)-1);
                
                if (runDict.size()==0) {
                    result.add(i);
                    break;
                }
                runIndex+=L[0].length();
            }
            
            
        }
        return result;
    }
	
	public static void main(String[] args) {
		SubstringWithConcatOfAllWords sb = new SubstringWithConcatOfAllWords();
		sb.findSubstring(S, L)
	}
}
