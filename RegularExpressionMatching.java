package leetcod;
/*
 * 1. runtime err. failed to check s.length()>0 for p.next=* case.
 * 2. fail aab, c*a*b. tweak misunderstanding in recursive case.
 * 3. fail "bbbba", ".*a*a". should substring s after recursion.
 * 4. fail "", ".*", add base case.
 * 5. still fail "", ".*", take out incorrect sanity check case
 * 6. fail "a", "", add base case in sanity check area
 * 7. fail "bb", "c*", add similar base condition as recursion base case.
 * 8. broke "aa", "a*". Naive base case added 4 was incorrect.
 */
public class RegularExpressionMatching {
	public boolean isMatch_20130623(String s, String p) {
        if (s==null && p==null) return true;
        if (s==null || s.length()==0) {
            //p must be x*x* etc
            if (p.length()%2!=0) return false;
            for (int i=1; i<p.length(); i+=2) {
                if (p.charAt(i)!='*') return false;
            }
            return true;
        }
        if (p==null || p.length()==0) return s==null || s.length()==0;
        if (s.equals(p)) return true;
        
        if (p.length()>1 && p.charAt(1)=='*') {
            if (p.charAt(0)=='.') {
                for (int i=0; i<s.length(); i++) {
                    if (isMatch(s.substring(i), p.length()>2? p.substring(2):null)) return true;
                }
                return isMatch(null, p.length()>2? p.substring(2):null);
            }
            else {
                if (s.charAt(0)!=p.charAt(0)) return isMatch(s, p.length()>2? p.substring(2):null);
                if (isMatch(s, p.length()>2? p.substring(2):null)) return true;
                int i=0;
                while(i<s.length() && s.charAt(i)==p.charAt(0)) {
                    i++;
                    if (isMatch(i==s.length()? null:s.substring(i),p.length()>2? p.substring(2):null)) return true;
                }
                return false;
            }
        }
        else if (p.charAt(0)=='.') {
            return isMatch(s.length()>1? s.substring(1):null, p.length()>1?p.substring(1):null);
        }
        
        if (s.charAt(0)!=p.charAt(0)) return false;
        return isMatch(s.length()>1? s.substring(1):null, p.length()>1?p.substring(1):null);
        
    }
	public boolean isMatch(String s, String p) {
        if (s==null&&p==null) return true;
        if (p.length()==0) return s.length()==0;
        if (s.length()==0) {
            if (p.length()%2!=0) return false;
            for (int i=1; i<p.length(); i+=2) {
                if (p.charAt(i)!='*') return false;
            }
            return true;
        }
        if (s.equals(p)) return true;
        if (s.length()==1 && p.length()==1) return p.equals(".");
        if (p.indexOf(".*")>=0 && p.length()%2==0) {
            boolean allStar = true;
            for (int i=1; i<p.length(); i+=2) {
                if (p.charAt(i)!='*') allStar = false;
            }
            if (allStar) return true;
        }
        
        if (p.length()>1 && p.charAt(1)=='*') {
            if (p.charAt(0)=='.') {
                for (int i=0; i<s.length(); i++) {
                    if (isMatch(s.substring(i), p.length()>2? p.substring(2):"")) return true;
                }
            }
            else {
                if (isMatch(s, p.length()>2?p.substring(2):"")) return true;
                int i=0;
                while(i<s.length() && s.charAt(i)==p.charAt(0)) {
                    if (isMatch(i==s.length()-1? "":s.substring(i+1), p.length()>2?p.substring(2):"")) return true;
                    i++;
                }
            }
            return false;
        }
        else {
            if (p.length()==1) {
                return false;
            }
            else {
                if (p.charAt(0)=='.') {
                    return isMatch(s.substring(1), p.substring(1));
                }
                else {
                    if (s.charAt(0)!=p.charAt(0)) return false;
                    return isMatch(s.substring(1),p.substring(1));
                }
            }
        }
    }
	
	
	
	public boolean isMatch_old(String s, String p) {
		if (p==null || p.length()==0) return s==null || s.length()==0;
        if (!validatePattern(p)) {
        	return false;
        }
        return doMatch(s,p);        
    }
	
	private boolean doMatch(String s, String p) {
		if (p.length()==0) return s==null || s.length()==0;
		if (p.length()==1) {
			return s.length()==1 && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.');
		}
		if (p.length()>1 && p.charAt(1)!='*') {
			return s.length()>0 && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.') &&
					doMatch(s.substring(1),p.substring(1));
		}
		while(s.length()>0 && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.')) {
			if (doMatch(s, p.substring(2))) return true;	
			s=s.substring(1);
		}
		return doMatch(s, p.substring(2));
	}

	private boolean validatePattern(String p) {
		if (p.startsWith("*")) return false;
		int index = p.indexOf('*');
		while(index>0) {
			if (p.charAt(index-1)=='*') {
				return false;
			}
			index= p.indexOf('*', index+1);
		}
		return true;
	}
	
	public static void main(String[] args) {
		RegularExpressionMatching rem = new RegularExpressionMatching();
		System.out.println(rem.isMatch("bcccccbaccccacaa", ".*bb*c*a*b*.*b*b*c*"));
	}
}
