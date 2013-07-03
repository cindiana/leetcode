package leetcod;

public class WildcardMatching {
	public boolean isMatch_20130528(String s, String p) {
		if (p==null || p.length()==0) return s==null || s.length()==0;
		int star=Integer.MAX_VALUE;
        int ss=0;
        int ps=0;
        int pp=0;
        while (ps < s.length()){
        	if (pp>p.length()-1) {
        		if (star<pp){ 
                	pp = star+1; 
                	ps = ++ss;
                }
                else return false;
        	}
        	else {
	            if (p.charAt(pp)=='?'||p.charAt(pp)==s.charAt(ps)){	
	            	ps++;pp++;
	            }
	            else if (p.charAt(pp)=='*') {
	            	star=pp; pp++; ss=ps;
	            }
	            else {
	                if (star< pp){ 
	                	pp = star+1; 
	                	ps = ++ss;
	                }
	                else return false;
	            }
        	}
        }
        while (pp<p.length() && p.charAt(pp)=='*'){pp++;}
        return pp==p.length();
    }
	
	public boolean isMatch(String s, String p) {
        if (s==null && p==null) return true;
        if (s.length()==0) {//p must be all *
            for (int i=0; i<p.length(); i++) {
                if (p.charAt(i)!='*') return false;
            }
            return true;
        }
        if (p.length()==0) return s.length()==0;
        if (p.equals(s)) return true;
        
        
        if (p.charAt(0)=='?') {
            return isMatch(s.length()>1? s.substring(1):"", p.length()>1? p.substring(1):"");    
        }
        else if(p.charAt(0)=='*') {
            //find next non-*
            int index=1;
            while(p.length()>index && p.charAt(index)=='*') {
                index++;
            }
            String subP = index>p.length()-1? "":p.substring(index);
            if (isMatch("", subP)) return true;
            
            for (int i=0; i<s.length(); i++) {
                if (isMatch(s.substring(i), subP)) return true;
            }
            return false;
        }
        else {
            if (s.charAt(0)!=p.charAt(0)) return false;
            return isMatch(s.length()>1?s.substring(1):"", p.length()>1?p.substring(1):"");
        }
    }
	
	public static void main(String[] args) {
		WildcardMatching wm = new WildcardMatching();
		wm.isMatch_20130528("aa", "*");
	}
}
