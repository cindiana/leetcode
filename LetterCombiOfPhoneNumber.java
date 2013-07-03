package leetcod;
import java.util.ArrayList;

/*
 * 1. Failed "", []. should be [""]. Make sure about corner case return value requirements. 
 */
public class LetterCombiOfPhoneNumber {
	public ArrayList<String> letterCombinations_20130623_iterative(String digits) {
        ArrayList<String> result = new ArrayList<String>();
        if (digits==null || digits.length()==0) {result.add(""); return result;}
        
        char[] firstDigit = map(digits.charAt(0));
        for (char c: firstDigit) {
            result.add(String.valueOf(c));  
        }
        
        for (int i=1; i<digits.length(); i++) {
            ArrayList<String> oneMore = new ArrayList<String>();
            char[] currDigit = map(digits.charAt(i));
            for (String s: result) {
                for(char c: currDigit) {
                    oneMore.add(s+c);
                }
            }
            result = oneMore;
        }
        return result;
    }
    
    private char[] map(char digit) {
        switch(digit) {            
            case '2': return new char[]{'a','b','c'};
            case '3': return new char[]{'d','e','f'};
            case '4': return new char[]{'g','h','i'};
            case '5': return new char[]{'j','k','l'};
            case '6': return new char[]{'m','n','o'};
            case '7': return new char[]{'p','q','r','s'};
            case '8': return new char[]{'t','u','v'};
            case '9': return new char[]{'w','x','y','z'};
            default: return new char[]{};
        }
    }
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            result.add("");
            return result;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<digits.length(); i++) {
            char thisDigit = digits.charAt(i);
            if (thisDigit<='9' && thisDigit>='2')
            {
                sb.append(thisDigit);
            }
        }
        if (sb.toString().length()>0) {
            return doPermute(sb.toString());
        }
        result.add("");
        return result;
    }

    private ArrayList<String> doPermute(String digits) {
        ArrayList<String> thisPermute = new ArrayList<String>();
        if (digits.length() == 1) {
            for (char c : getChars(digits.charAt(0))) {
                thisPermute.add(String.valueOf(c));
            }
            return thisPermute;
        }

        for (String sub : doPermute(digits.substring(1))) {
            for (char c : getChars(digits.charAt(0))) {
                thisPermute.add(c + sub);
            }
        }
        return thisPermute;
    }

    private char[] getChars(char input) {
        switch (input) {
            case '2':
                return new char[] {'a','b','c'};
            case '3':
                return new char[] {'d','e','f'};
            case '4':
                return new char[] {'g','h','i'};
            case '5':
                return new char[] {'j','k','l'};                
            case '6':
                return new char[] {'m','n','o'};         
            case '7':
                return new char[] {'p','q','r','s'};
            case '8':
                return new char[] {'t','u','v'};
            case '9':
                return new char[] {'w','x','y','z'};           
            default:
                return new char[] {};
        }
    }
    
    public static void main(String[] args) {
        LetterCombiOfPhoneNumber lcp = new LetterCombiOfPhoneNumber();
        ArrayList<String> result = lcp.letterCombinations("7");
        for (String s : result) {
            System.out.println(s);
        }
    }
}
