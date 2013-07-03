package leetcod;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 1. naive scramble fail abcd, dbac. Impl recursive scramble
 * 2. fail aa,aa. err in loop condition. For inclusive range, i needs to start at 0.
 * 3. exceed time limit for big test. Found other algorithm.
 */
public class ScrambleString {
	/*
	 * 2013 Version
	 */
	public boolean isScramble(String s1, String s2) {
        if (s1==null && s2==null) return true;
        if (s1==null || s2==null) return false;
        if (s1.equals(s2)) return true;
        if (s1.length()!=s2.length()) return false;
        
        if (s1.length()==2) {
            if (s1.charAt(1)==s2.charAt(0) &&
                s1.charAt(0)==s2.charAt(1)) return true;
            return false;
        }
        
        int s1Hash = 0;
        for (char c: s1.toCharArray()) {
            s1Hash+=c;
        }
        int s2Hash = 0;
        for (char c: s2.toCharArray()) {
            s2Hash+=c;
        }
        if (s1Hash!=s2Hash) return false;
        
        for (int i=1; i<s1.length(); i++) {
            if (isScramble(s1.substring(0,i),s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i)) ||
                isScramble(s1.substring(0,i),s2.substring(s1.length()-i,s1.length())) 
                && isScramble(s1.substring(i), s2.substring(0,s1.length()-i))) {
                    return true;
                }
        }
        return false;     
    }
	
	public boolean isScramble_20130612(String s1, String s2) {
        if (s1.equals(s2)) return true;
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (char c: s1.toCharArray()) {
            if (freq.containsKey(c)) {
                freq.put(c,freq.get(c)+1);
            }
            else {
                freq.put(c,1);
            }
        }
        for (char c: s2.toCharArray()) {
            if (!freq.containsKey(c)) {
                return false;
            }
            else {
                if (freq.get(c)==0) return false;
                freq.put(c,freq.get(c)-1);               
            }
        }
        for (int i=1; i<s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i))
            || (isScramble(s1.substring(0,i), s2.substring(s1.length()-i)) && isScramble(s1.substring(i),s2.substring(0, s1.length()-i))))
            return true;        
        }
        return false;
    }
	public boolean isScramble_2012(String s1, String s2) {
		if (s1 == null || s1.length() == 0) {
			return true;
		}
		if (s1.length() == 1) {
			return s1.equals(s2);
		}

		Stack<Interval> stack1 = new Stack<Interval>();
		Map<Character, Integer> lastOccur = new HashMap<Character, Integer>();

		for (char c : s1.toCharArray()) {
			int index = -1;
			if (lastOccur.containsKey(c)) {
				index = s2.indexOf(c, lastOccur.get(c) + 1);
			} else {
				index = s2.indexOf(c);
			}
			if (index < 0) {
				return false;
			}
			lastOccur.put(c, index);			
			stack1.push(new Interval(index, index));
		}

		Stack<Interval> stack2 = new Stack<Interval>();
		while (!stack1.isEmpty()) {
			Interval interval1 = stack1.pop();
			if (stack2.isEmpty()) {
				stack2.push(interval1);
			} else {
				while (stack2.size() > 0
						&& (stack2.peek().start - interval1.end == 1
								|| stack2.peek().start - interval1.end == -1
								|| stack2.peek().end + 1 == interval1.start || stack2
								.peek().end == interval1.start + 1)) {
					Interval stack2Pop = stack2.pop();
					interval1 = new Interval(Math.min(stack2Pop.start,
							interval1.start), Math.max(stack2Pop.end,
							interval1.end));
				}
				stack2.push(interval1);
			}
		}
		return stack2.size() == 1;
	}

	public class Interval {
		int start;
		int end;

		public Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public boolean isScrambleRecursive(String s1, String s2) {
		if (s1 == null || s1.length() == 0) {
			return true;
		}
		if (s1.length() == 1) {
			return s1.equals(s2);
		}
		return doScramble(s1.toCharArray(), 0, s1.length() - 1,
				s2.toCharArray(), 0, s2.length() - 1);
	}

	private boolean doScramble(char[] s1, int start1, int e1, char[] s2,
			int start2, int e2) {
		if (e1 == start1) {
			return s1[e1] == s2[e2];
		}
		for (int i = 0; i < e1 - start1; i++) {
			if (doScramble(s1, start1, start1 + i, s2, start2, start2 + i)
					&& doScramble(s1, start1 + i + 1, e1, s2, start2 + i + 1,
							e2)
					|| doScramble(s1, start1, start1 + i, s2, e2 - i, e2)
					&& doScramble(s1, start1 + i + 1, e1, s2, start2, e2 - i
							- 1)) {
				return true;
			}
		}
		return false;
	}

	public boolean isNaiveScramble(String s1, String s2) {
		if (s1 == null || s1.length() == 0) {
			return true;
		}
		char[] char1 = s1.toCharArray();
		Map<Character, Integer> counter = new HashMap<Character, Integer>();
		for (char c : char1) {
			Integer count = 0;
			if (counter.containsKey(c)) {
				count = counter.get(c);
			}
			count++;
			counter.put(c, count);
		}

		char[] char2 = s2.toCharArray();
		for (char c : char2) {
			if (!counter.containsKey(c)) {
				return false;
			}
			Integer count = counter.get(c);
			count--;
			if (count > 0) {
				counter.put(c, count);
			} else {
				counter.remove(c);
			}
		}
		return counter.size() == 0;
	}
	
	

	public static void main(String[] args) {
		ScrambleString ss = new ScrambleString();
		ss.isScramble("abab", "bbaa");
	}
}
