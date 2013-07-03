package leetcod;

import java.util.Stack;

/*
 * 1. fail ((). algorithm flaw: when end is reached and leftCount!=rightCount.
 * 2. fail (() by returning 1. Should be rightCount*2
 * 3. fail ()). bug introduced by fixing #2.
 * 4. fail ()((). algorithm flaw. Re-do algorithm.
 * 5. fail ()(). didn't update prevClosureEnd.
 * 6. fail (()((((). can't use rightCount*2. prematureMax needs to be valid closure too.
 * 7. looked for algorithm. sweep in both directions and get maxLen.
 */

public class LongestValidParenthesis {
	public int longestValidParentheses_20130526(String s) {
        int max = 0;
        int runStart = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)=='(') stack.push(i);
            else {
                if (stack.isEmpty()) {
                    runStart = s.length();//the run is over by an extra ')', reset runStart
                    continue;
                }
                int closedLeft= stack.pop();
                runStart = Math.min(closedLeft, runStart);//run continues, keep track of min left index as runStart
                int length;
                if (stack.isEmpty()) {
                	length = i-runStart+1;//()(), run can still go on, length should start from runStart;
                }
                else{
                    length = i-stack.peek();//()((), runStart is 0, but we have not matched all ( yet. Can only go so far back 
                }
                if (length>max) max = length;
            }
        }
        
        return max;
    }
	
	public int longestValidParentheses(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}

		int i = 0;
		int count = 0;
		int thisLen = 0;
		int maxLen = 0;
		while(i<s.length()) {
			if (s.charAt(i)=='(') {
				count++;
				thisLen++;
			}
			else {
				count--;
				thisLen++;
			}
			if (count==0) {
				maxLen = Math.max(thisLen, maxLen);
			}
			if (count<0) {
				thisLen = 0;
				count = 0;
			}
			i++;			
		}
		count = 0;
		thisLen = 0;
		while(i>0) {
			i--;
			if (s.charAt(i)==')') {
				count++;
				thisLen++;
			}
			else {
				count--;
				thisLen++;
			}
			if (count==0) {
				maxLen = Math.max(thisLen, maxLen);
			}
			if (count<0) {
				thisLen = 0;
				count = 0;
			}
		}
		return maxLen;
		/*int i = 0;
		int maxLen = 0;
		int prevClosureEnd = -1;
		int prevClosedLen = 0;

		while (i < s.length() - 1) {
			while (s.charAt(i) == ')' && i < s.length() - 1) {
				i++;
			}
			if (i==s.length()-1) return maxLen;
			
			int leftCount = 1;
			int rightCount = 0;
			int j = i + 1;

			while (leftCount > rightCount && j < s.length()) {
				if (s.charAt(j) == '(') {
					leftCount++;
				} else {
					rightCount++;
				}
				j+=1;
			}
			
			if (leftCount == rightCount) {
				int closedLen = leftCount*2;
				if (prevClosureEnd == i-1) {
					closedLen+=prevClosedLen;	
				}
				
				maxLen = Math.max(maxLen, closedLen);
				prevClosedLen = closedLen;
				prevClosureEnd = j-1;
				i = j;
			}
			else {//j reach end
				int prematureMax = rightCount*2;
				return Math.max(prematureMax, maxLen);
			}
		}

		return maxLen;*/
	}
	
	public static void main(String[] args) {
		LongestValidParenthesis lvp = new LongestValidParenthesis();
		lvp.longestValidParentheses("()()");
	}
}
