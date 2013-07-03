package leetcod;
/*
 * Almost. n=2, output "11". I am off by 1 in the outter most for loop 
 */
public class CountAndSay {
	public String countAndSay_20130528(int n) {
        String base = "1";
        if (n==1) return base;
        for (int i=2; i<=n; i++) {
            base = read(base);
        }
        return base;
    }
    
    public String read(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i<s.length()) {
            int runStart = i;
            while(i+1<s.length() && s.charAt(i+1) == s.charAt(i)) {
                i++;
            }
            int runLength = i-runStart+1;
            sb.append(runLength);
            sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }
    
    public String countAndSay(int n) {
        String prev = "1";
        for(int i=1; i<n; i++) {
            prev = speak(prev);
        }
        return prev;
    }
    
    private String speak(String digits) {
        int index = 0;
        int count = 1;
        StringBuilder sb = new StringBuilder();
        while(index<digits.length()) {
            if (index<digits.length()-1 && digits.charAt(index)==digits.charAt(index+1)) {
                count++;
                index++;
            }
            else {
                sb.append(String.valueOf(count));
                sb.append(digits.charAt(index));
                count = 1;
                index++;
            }
        }        
        
        return sb.toString();
    }    
   
}
