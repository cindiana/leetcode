package leetcod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 1. runtime err. ClassCast from Collection<Integer> to List<Integer>
 * 2. fail (BECB, BC). err coding correct algorithm. confuse among variables
 * 3. runtime err. T contains 1 unique char. Algorithm needs rework, keep count for any char.
 * 4. new algorithm fail (aa,aa) still. init min length = T.length made it unable to handle complete match.
 * 5. fail (abc, ac). handling complete match is only part of minLen==S.len cases.
 * 6. fail (abc, cba). wrong special case: S.len==T.len, it's a shuffle, instead of straight match.
 * 7. fail (bba, ab). in else block, remove charIndex at 0, append new index. it's a replace.
 * 8. exceed time limit for big test. enhance: discard [1,~] elements in charIndex's linkedList
 * 9. fail (baba, abb). fresh start happens only once. use boolean to control init.
 * I wonder if there's better algorithm?
 */
public class MinWindowSubstring {
	/*
	 * passed OJ big and small
	 * Main improvement: 
	 * 1) use int[] needToFind, and int[] found
	 * 2) use count to determine if T is complete
	 * 3) only slide window when T is complete, and slide with 2 simple conditions.
	 */
	public String minWindow_20130609try2(String S, String T) {
        if (S==null || T==null || S.length()<T.length()) return "";
        int[] needToFind = new int[256];
        int[] found = new int[256];
        for (int i=0; i<T.length(); i++) {
            needToFind[T.charAt(i)]+=1;
        }
        int leftWindow = 0;
        int minLen = Integer.MAX_VALUE;
        String window = null;
        int count=0;
        for (int i=0; i<S.length(); i++) {
            if (needToFind[S.charAt(i)]==0) continue;
            found[S.charAt(i)]+=1;
            if (needToFind[S.charAt(i)]>=found[S.charAt(i)]) {
                count++;
            }
            if (count==T.length()) {
                while(needToFind[S.charAt(leftWindow)]==0 || 
                    needToFind[S.charAt(leftWindow)]<found[S.charAt(leftWindow)]) {
                        if (needToFind[S.charAt(leftWindow)]<found[S.charAt(leftWindow)]) {
                            found[S.charAt(leftWindow)]--;
                        }
                        leftWindow++;
                }
                if (i-leftWindow+1<minLen) {
                    if (i==S.length()-1) window = S.substring(leftWindow);
                    else window = S.substring(leftWindow, i+1);
                    minLen = i-leftWindow+1;
                }
            }
        }
        return window==null? "":window;
    }
	
	// pass OJ small, time-out OJ big
	public String minWindow_20130609(String S, String T) {
        if (S==null || T==null || S.length()<T.length()) return "";
        Map<Character, Integer> charFreqInT = new HashMap<Character, Integer>();
        Map<Character, Integer> charFreqInTStatic= new HashMap<Character, Integer>();
        for (char c: T.toCharArray()) {
            if (!charFreqInT.containsKey(c)) {
                charFreqInT.put(c, 1);
                charFreqInTStatic.put(c,1);
            }
            else {
                charFreqInT.put(c, charFreqInT.get(c)+1);
                charFreqInTStatic.put(c, charFreqInTStatic.get(c)+1);
            }
        }
        Map<Character, ArrayList<Integer>> runningIndices = new HashMap<Character, ArrayList<Integer>>();
        String window = "";
        int windowLeftIndex = -1;
        int i=0;
        while(i<S.length()) {
            char c = S.charAt(i);
            if (!charFreqInTStatic.containsKey(c)) { i++; continue; }
            if (charFreqInT.size()>0) { //haven't found 1st window
                if (!runningIndices.containsKey(c)) {
                    if (runningIndices.size()==0) windowLeftIndex = i;
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    indices.add(i);
                    runningIndices.put(c, indices);
                }
                else {
                    runningIndices.get(c).add(i);
                }
                if (charFreqInT.containsKey(c)) {
		            if (charFreqInT.get(c)==1) {
		            	charFreqInT.remove(c);
		                if (charFreqInT.size()==0) {// found 1st window
		                	while (runningIndices.get(S.charAt(windowLeftIndex)).size()>charFreqInTStatic.get(S.charAt(windowLeftIndex))) {		                		
		                		runningIndices.get(S.charAt(windowLeftIndex)).remove(0);
		                		windowLeftIndex++;
		                		while(!charFreqInTStatic.containsKey(S.charAt(windowLeftIndex))) {
		                			windowLeftIndex++;
		                		}
		                	}
		                    window = S.substring(windowLeftIndex, i+1);
		                }
		            }
		            else {
		                charFreqInT.put(c, charFreqInT.get(c)-1);
		            } 
                }
            }
            else { //found 1st, now slide window, we will
                if (runningIndices.get(c).get(0)==windowLeftIndex) {
                    windowLeftIndex++;
                    runningIndices.get(c).remove(0);
                    while(windowLeftIndex<S.length()) {
                    	if (windowLeftIndex<S.length() && !charFreqInTStatic.containsKey(S.charAt(windowLeftIndex))) { windowLeftIndex++; continue; }
                    	if (charFreqInTStatic.containsKey(S.charAt(windowLeftIndex))) {
                    		if (charFreqInTStatic.get(S.charAt(windowLeftIndex))<runningIndices.get(S.charAt(windowLeftIndex)).size()) {                               
                                runningIndices.get(S.charAt(windowLeftIndex)).remove(0);
                                windowLeftIndex++;
                            }
                    		else break;
                    	}                    	
                    }
                   
                    if (i+1-windowLeftIndex < window.length()) {
                    	window = S.substring(windowLeftIndex, i+1);
                    }                    
                    runningIndices.get(c).add(i);                    
                }
                else {
                    runningIndices.get(c).add(i);
                }
            }
            i++;
        }
        return window;
    }
	
	
	
	public String minWindow(String S, String T) {
		if (S == null || S.length() == 0 || T == null || T.length() == 0
				|| S.length() < T.length()) {
			return "";
		}
		if (T.length() == 1) {
			return S.indexOf(T.charAt(0)) >= 0 ? T : "";
		}

		Map<Integer, Integer> thisRun = new HashMap<Integer, Integer>();
		Map<Integer, Integer> allCharInT = new HashMap<Integer, Integer>();

		int minLen = S.length() + 1;
		String minWindowSoFar = "";

		for (int i = 0; i < T.length(); i++) {
			Integer key = Integer.valueOf(T.charAt(i));
			if (!thisRun.containsKey(key)) {
				thisRun.put(key, Integer.valueOf(1));
				allCharInT.put(key, Integer.valueOf(1));
			} else {
				int oldCount = thisRun.get(key).intValue();
				thisRun.put(key, Integer.valueOf(oldCount + 1));
				allCharInT.put(key, Integer.valueOf(oldCount + 1));
			}
		}

		int thisStart = 0;
		Map<Integer, List<Integer>> charIndex = new HashMap<Integer, List<Integer>>();
		boolean started = false;

		for (int i = 0; i < S.length(); i++) {
			Integer wrapee = Integer.valueOf(S.charAt(i));
			if (thisRun.containsKey(wrapee)) {
				if (charIndex.containsKey(wrapee)) {
					List<Integer> list = charIndex.get(wrapee);
					list.add(Integer.valueOf(i));
				} else {
					List<Integer> list = new ArrayList<Integer>();
					list.add(Integer.valueOf(i));
					charIndex.put(wrapee, list);
				}
				if (!started && thisRun.size() == allCharInT.size()
						&& thisRun.get(wrapee).intValue() == allCharInT.get(
								wrapee).intValue()) {
					thisStart = i;
					started = true;
				}

				int oldCount = thisRun.get(wrapee).intValue();
				if (oldCount == 1) {
					thisRun.remove(wrapee);
				} else {
					thisRun.put(wrapee, Integer.valueOf(oldCount - 1));
				}
				if (thisRun.size() == 0) {
					int thisRunWindowLen = i - thisStart + 1;
					if (thisRunWindowLen < minLen) {
						minLen = thisRunWindowLen;
						minWindowSoFar = S.substring(thisStart, i + 1);
					}

					thisRun.put(Integer.valueOf(S.charAt(thisStart)),
							Integer.valueOf(1));
					List<Integer> list = charIndex.get(Integer.valueOf(S
							.charAt(thisStart)));
					if (list.size() == 1) {
						charIndex.remove(Integer.valueOf(S.charAt(thisStart)));
					} else {
						list.remove(0);
					}
					Collection<List<Integer>> indices = charIndex.values();
					List<Integer> temp = new ArrayList<Integer>();
					for (List<Integer> index : indices) {
						temp.add(index.get(0));
					}
					Collections.sort(temp);
					thisStart = temp.get(0).intValue();
				}
			} else if (allCharInT.containsKey(wrapee)) {
				if (charIndex.containsKey(wrapee)) {
					List<Integer> list = charIndex.get(wrapee);
					list.add(Integer.valueOf(i));
					list.remove(0);
				} else {
					List<Integer> list = new ArrayList<Integer>();
					list.add(Integer.valueOf(i));
					charIndex.put(wrapee, list);
				}

				if (S.charAt(i) == S.charAt(thisStart)) {
					Collection<List<Integer>> indices = charIndex.values();
					List<Integer> temp = new ArrayList<Integer>();
					for (List<Integer> index : indices) {
						temp.add(index.get(0));
					}
					Collections.sort(temp);
					thisStart = temp.get(0).intValue();
				}
			}
		}

		return minWindowSoFar;
	}

	public static void main(String[] args) {
		MinWindowSubstring mws = new MinWindowSubstring();
		String result = mws.minWindow_20130609try2("acbdbaab", "aabd");
		System.out.println(result);
	}
}
