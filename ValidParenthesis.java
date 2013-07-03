package leetcod;

import java.util.Stack;

/*
 * 1. fail all. brain damage. it's a () match, not a ==
 * 2. it's a !match return false. tired.
 */
public class ValidParenthesis {
	
	public boolean isValid_20130504(String s) {
        if (s==null ||s.length()==0) return true;
        if (s.length()%2==1) return false;
        
        Stack<Character> lefts = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c=='(' || c=='[' || c=='{') {
                lefts.push(c);
            }
            else {
                if (lefts.isEmpty()) return false;
                char lastLeft = lefts.pop();
                if (c==')'&&lastLeft!='(') return false;
                if (c==']'&&lastLeft!='[') return false;
                if (c=='}'&&lastLeft!='{') return false;
            }
        }
        return lefts.isEmpty();
    }
	
	public boolean isValid(String s) {
        Stack<Character> left = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (char c: chars) {
        	if (c=='[' || c== '{' || c=='(') {
        		left.push(c);
        	}
        	else {
        		if (left.isEmpty() || !match(left.pop(),c)) {
        			return false;
        		}
        	}
        }
        return left.isEmpty();        
    }
	
	private boolean match(char char1, char char2) {
		return char1=='(' && char2==')' ||
				char1=='['&& char2==']' ||
				char1=='{'&& char2=='}'; 
	}
	
	public static void main(String[] args) {
		ValidParenthesis vp = new ValidParenthesis();
		vp.isValid("()");
	}
}
