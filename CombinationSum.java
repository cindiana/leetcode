package leetcod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * 1. Arrays.copyOfRange.to is exclusive
 * 2. doCombi2 can't handle {2,2,2}, 4. outputs {{2,2}{2,2}}
 */
public class CombinationSum {
	public ArrayList<ArrayList<Integer>> combinationSum2_20130527(int[] num, int target) {
        Arrays.sort(num);        
        return doWork_20130527(num, 0, target);
    }
    
    public ArrayList<ArrayList<Integer>> doWork_20130527(int[] candidates, int start, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (start>candidates.length-1 || candidates[start]>target) return result;
        if (candidates[start]==target) {
            ArrayList<Integer> r = new ArrayList<Integer>();
            r.add(target);
            result.add(r);
            return result;
        }
        
        Set<ArrayList<Integer>> resultset = new HashSet<ArrayList<Integer>>();
        for (int i=start; i<candidates.length; i++) {
            if (candidates[i]>target) break;
            if (candidates[i]==target) {
                ArrayList<Integer> r = new ArrayList<Integer>();
                r.add(target);
                resultset.add(r);
            }
            else {
                ArrayList<ArrayList<Integer>> subResult = doWork(candidates, i+1, target-candidates[i]);
                for(ArrayList<Integer> r: subResult) {
                    r.add(0, candidates[i]);
                } 
                resultset.addAll(subResult);                
            }
        }
        result.addAll(resultset);
        return result;
    }
    
	public ArrayList<ArrayList<Integer>> combinationSum_20130527(int[] candidates, int target) {        
        Arrays.sort(candidates);        
        return doWork(candidates, 0, target);
    }
    
	public ArrayList<ArrayList<Integer>> doWork(int[] candidates, int start, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (candidates[start]>target) return result;
        if (candidates[start]==target) {
            ArrayList<Integer> r = new ArrayList<Integer>();
            r.add(target);
            result.add(r);
            return result;
        }
        
        for (int i=start; i<candidates.length; i++) {
            if (candidates[i]>target) break;
            if (candidates[i]==target) {
                ArrayList<Integer> r = new ArrayList<Integer>();
                r.add(target);
                result.add(r);
            }
            else {
                ArrayList<ArrayList<Integer>> subResult = doWork(candidates, i, target-candidates[i]);
                for(ArrayList<Integer> r: subResult) {
                    r.add(0, candidates[i]);
                } 
                result.addAll(subResult);                
            }
        }
        return result;
    }
	
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (candidates == null || candidates.length < 0) {
			return result;
		}

		Arrays.sort(candidates);
		return doCombi(candidates, target);
	}
	
	public ArrayList<ArrayList<Integer>> doCombi(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> toReturn = new ArrayList<ArrayList<Integer>>();
		if (candidates.length == 0 || candidates[0] > target) {
			return toReturn;
		}
		if (candidates[0] == target) {
			ArrayList<Integer> minCandidate = new ArrayList<Integer>();
			minCandidate.add(candidates[0]);
			toReturn.add(minCandidate);
			return toReturn;
		}
		ArrayList<ArrayList<Integer>> contains = appendToEach(candidates[0],
				doCombi(candidates, target - candidates[0]));
		ArrayList<ArrayList<Integer>> notContain = doCombi(
				Arrays.copyOfRange(candidates, 1, candidates.length),
				target);
		contains.addAll(notContain);
		for(ArrayList<Integer> list: contains) {
			Collections.sort(list);
		}
		return contains;
	}
	
	public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates,
			int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (candidates == null || candidates.length < 0) {
			return result;
		}

		Arrays.sort(candidates);
		result = doCombi2(candidates, target);
		
		
		Set<ArrayList<Integer>> toReturn = new HashSet<ArrayList<Integer>>();
		for(ArrayList<Integer> list: result) {
			toReturn.add(list);
		}
		
		return new ArrayList<ArrayList<Integer>>(toReturn);
	}

	
	public ArrayList<ArrayList<Integer>> doCombi2(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> toReturn = new ArrayList<ArrayList<Integer>>();
		if (candidates.length == 0 || candidates[0] > target) {
			return toReturn;
		}
		if (candidates[0] == target) {
			ArrayList<Integer> minCandidate = new ArrayList<Integer>();
			minCandidate.add(candidates[0]);
			toReturn.add(minCandidate);
			return toReturn;
		}
		ArrayList<ArrayList<Integer>> contains = appendToEach(candidates[0],
				doCombi(Arrays.copyOfRange(candidates, 1, candidates.length), target - candidates[0]));
		ArrayList<ArrayList<Integer>> notContain = doCombi(
				Arrays.copyOfRange(candidates, 1, candidates.length),
				target);
		contains.addAll(notContain);
		for(ArrayList<Integer> list: contains) {
			Collections.sort(list);
		}
		return contains;
	}

	public ArrayList<ArrayList<Integer>> appendToEach(int toAppend,
			ArrayList<ArrayList<Integer>> appendTo) {
		for (ArrayList<Integer> list : appendTo) {
			list.add(toAppend);
		}
		return appendTo;
	}

	public static void main(String[] args) {
		CombinationSum sum = new CombinationSum();
		
		ArrayList<ArrayList<Integer>> result = sum.combinationSum_20130527(new int[] {
				1, 2 }, 2);
		for (ArrayList<Integer> list : result) {
			StringBuilder sb = new StringBuilder();
			for (Integer i : list) {
				sb.append(i + ",");
			}
			System.out.println(sb.toString());
		}
	}
}
