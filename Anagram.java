package leetcod;

import java.util.ArrayList;


/*
 * Given an array of strings, return all groups of strings that are anagrams.
 */
public class Anagram {
	public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        if (strs==null) return result;
        
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String s:strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sig = new String(chars);
            if (map.containsKey(sig)) map.get(sig).add(s);
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(s);
                map.put(sig, list);
            }
        }
        
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            if (entry.getValue().size()>1) {
                result.addAll(entry.getValue());
            }
        }
        
        return result;
    }
}
