package leetcod;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
	public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> result = new ArrayList<String>();
        if (words==null || words.length==0) return result;
        
        int i=0;
        List<String> currentLine = new ArrayList<String>();
        int currentCharCount = 0;
        while(i<words.length) {
            if (L-currentCharCount-currentLine.size()>=words[i].length()) {
                currentCharCount+=words[i].length();
                currentLine.add(words[i]);                
                i++;
            }
            else 
            {
                result.add(processLine(currentLine, currentCharCount, L));
                currentLine = new ArrayList<String>();
                currentCharCount = 0;
            }
        }
        result.add(processLastLine(currentLine, L));
        return result;
    }
    
    public String processLastLine(List<String> words, int L) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<words.size()-1; i++) {
            sb.append(words.get(i));
            sb.append(" ");
        }
        sb.append(words.get(words.size()-1));
        int numSpaces = L-sb.length();
        for (int i=0; i<numSpaces; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public String processLine(List<String> words, int charCount, int L) {
        StringBuffer sb = new StringBuffer();
        if (words.size()==1) {
            sb.append(words.get(0));
            for (int i=0; i<L-words.get(0).length(); i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        int slots = words.size()-1;
        int numSpaces = L-charCount;
        int defaultSpaces = numSpaces/slots;
        int numExtraLeftSlots = numSpaces%slots;
        
        for (int i=0; i<words.size()-1; i++) {
            sb.append(words.get(i));
            for (int j=0; j<defaultSpaces; j++) {
                sb.append(" ");
            }
            if (i<numExtraLeftSlots) {
                sb.append(" ");
            }
        }
        sb.append(words.get(words.size()-1));
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	TextJustification tj = new TextJustification();
    	ArrayList<String> result = tj.fullJustify(new String[] {""}, 2);
    	int k=0;
    	k++;
    	System.out.println(k);
    }
}
