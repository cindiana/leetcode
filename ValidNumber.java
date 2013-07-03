package leetcod;

public class ValidNumber {
	public boolean isNumber(String s) {
        if (s==null || s.length()==0) return false;
        s = s.trim();
        if (s.length()==0) return false;
        
        if (s.indexOf('.')>=0 && s.lastIndexOf('.')>=0 && s.indexOf('.')!=s.lastIndexOf('.')) return false;
        if (s.indexOf('e')==0 || s.indexOf('e')>0 && s.lastIndexOf('e')>0 && s.indexOf('e')!=s.lastIndexOf('e')) return false;
        
        if (s.indexOf('.')>=0) {
            if (s.indexOf('e')>0) {
                if (!isFloatWithSign(s.substring(0, s.indexOf('e'))) || !isIntWithSign(s.substring(s.indexOf('e')+1))) {
                    return false;
                }
            }
            else if (!isFloatWithSign(s)) return false;
        } 
        else {
            if (s.indexOf('e')>0) {
                if (!isIntWithSign(s.substring(0, s.indexOf('e'))) || !isIntWithSign(s.substring(s.indexOf('e')+1))) {
                    return false;
                }
            }
            else if (!isIntWithSign(s)) return false;
        }
        
        return true;
    }
    
    
    public boolean isFloatWithSign(String s) {
        char c0 = s.charAt(0);
        if (c0!='+' && c0!='-' && c0!='.' && (c0<'0'||c0>'9')) return false;
        if ((c0=='+'||c0=='-'||c0=='.') && s.length()==1) return false;
        if (s.indexOf('.')==s.length()-1 && s.length()>1 && 
            (s.charAt(s.indexOf('.')-1)<'0' || s.charAt(s.indexOf('.')-1)>'9')) return false;
        
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i)!='.'&&(s.charAt(i)<'0'||s.charAt(i)>'9')) 
                return false;
        }
        return true;
    }
    
    public boolean isIntWithSign(String s) {
        if (s==null || s.length()==0) return false;
        if (s.charAt(0)!='+' && s.charAt(0)!='-' && (s.charAt(0)<'0' || s.charAt(0)>'9')) return false;
        if (s.charAt(0)=='+' || s.charAt(0)=='-') {
            if (s.length()==1) return false;
        }

        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i)<'0' || s.charAt(i)>'9') return false;
        }
        return true;
    }
	public static void main(String[] args) {
		ValidNumber vm = new ValidNumber();
		vm.isNumber("2e+0");
		
	}
}
