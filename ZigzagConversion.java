package leetcod;

public class ZigzagConversion {
	public String convert(String s, int nRows) {
        if (nRows<=1) return s;
        if (s.length()<2) return s;
        
        int zigLen = nRows*2-2;
        int zigCount = s.length()%zigLen==0? s.length()/zigLen : s.length()/zigLen+1;
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<nRows; i++) {
            for (int j=0; j<zigCount; j++) {                
                if (i+j*zigLen<s.length()) {
                    sb.append(s.charAt(i+j*zigLen));    
                }
                if (i>0 && i<nRows-1 && zigLen-i+j*zigLen<s.length()) {
                    sb.append(s.charAt(zigLen-i+j*zigLen));
                }
            }
        }
        return sb.toString();
    }
}
