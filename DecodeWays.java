package leetcod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DecodeWays {
	public static final String[] lookup= new String[]{"","A","B","C","D","E","F","G","H",
        "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public Map<String, HashSet<String>> cache = new HashMap<String, HashSet<String>>();
    
    public int numDecodings_O_N(String s) {
        if (s==null || s.length()==0) return 0;
        if (s.startsWith("0")) return 0;
        
        int[] ways = new int[s.length()];       
        ways[0] = 1;
        for (int i=1; i<s.length(); i++) {
            ways[i] = 0;
            char c = s.charAt(i);
            if (c=='0'){
                if (s.charAt(i-1)!='1' && s.charAt(i-1)!='2') {
                   return 0;
                }
                ways[i] = (i>1?ways[i-2]:1);
            }
            else if (c>'0'&& c<'7' && s.charAt(i-1)=='2'
                    ||c!='0' && s.charAt(i-1)=='1') {
                ways[i] = ways[i-1]+ (i>1?ways[i-2]:1);
            }else {
                ways[i] = ways[i-1];
            }
        }
        return ways[s.length()-1];
    }
    
    public int numDecodings(String s) {
        if (s==null || s.length()==0 || s.equals("0")) return 0;
        if (s.startsWith("0")) return 0;
        return ways(s).size();
        
    }
    
    public HashSet<String> ways(String s) {
        if (cache.containsKey(s)) return cache.get(s);
        
        HashSet<String> result = new HashSet<String>();
        if (s.length()==1 && !s.equals("0")) {
            result.add(lookup[Integer.parseInt(s)]);
        }
        else if (s.length()==2) {
            int val = Integer.parseInt(s);
            int val1 = Integer.parseInt(s.substring(0,1));
            int val2 = Integer.parseInt(s.substring(1));
            if (val2!=0) {
                result.add(lookup[val1]+lookup[val2]);
            }
            if (val<=26)
            {
                result.add(lookup[val]);
            }
        }
        else {           
            for (int i=1; i<s.length(); i++) {                
                String s2 = s.substring(i);
                String s1 = s.substring(0,i);
                if (s2.startsWith("0")) {
                    continue;
                }
                Set<String> way1 = ways(s1);
                Set<String> way2 = ways(s2);               
                Iterator<String> i1 = way1.iterator();                
                while(i1.hasNext()) {
                    String i1Next = i1.next();
                    Iterator<String> i2 = way2.iterator();
                    while(i2.hasNext()) {
                        result.add(i1Next+i2.next());
                    }
                }
            }
            
        }
        cache.put(s, result);
        return result;
    }
}
