package leetcod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * 1. [] output for everything. typo in appendAll to use result instead of partialResult
 * 2. Time limit exceeded. avoid Array.copyOfRange()
 * 3. runtime error. count++ won't work, use count+1.
 * 4. Time limit exceeded. Adopt permuting with boolean mask
 * 5. runtime err. Collections.copy needs dest collection initialized with a size.
 * 6. runtime err. Should not use Collections.copy since capacity!=size.
 * 
 * Perm II: unique
 * 1. fail apparent case: typo is Equals, missing a !
 * 2. exceed time limit for big test. use set in recursion.
 */
public class Permutations {
	public ArrayList<ArrayList<Integer>> permute_20130510(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num==null || num.length==0) return result;
        
        return doPermute(num, 0);
    }
    
    ArrayList<ArrayList<Integer>> doPermute_20130510(int[] num, int start) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num.length-start==1) {
            ArrayList<Integer> justme = new ArrayList<Integer>();
            justme.add(num[start]);
            result.add(justme);
            return result;
        }
        
        ArrayList<ArrayList<Integer>> minus1 = doPermute(num, start+1);
        for (ArrayList<Integer> subPerms: minus1) {           
            for (int i=0; i<=subPerms.size(); i++) {
                ArrayList<Integer> insert = (ArrayList<Integer>)subPerms.clone();
                insert.add(i, num[start]);
                result.add(insert);
            }
        }
        return result;        
    }
	
	
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
        if (num==null || num.length==0) {
        	return new ArrayList<ArrayList<Integer>>();
        }
        //return doPermute(num, 0);
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        boolean[] used = new boolean[num.length];
        otherPermute(result, new ArrayList<Integer>(), num, used, 0);
        return result;
	}
	
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		if (num==null || num.length==0) {
        	return new ArrayList<ArrayList<Integer>>();
        }
        
        
        boolean[] used = new boolean[num.length];
        Set<Perm> uniques = new HashSet<Perm>();
        otherPermuteUnique(uniques, new ArrayList<Integer>(), num, used, 0);
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Iterator<Perm> p = uniques.iterator();
        while(p.hasNext()) {
        	result.add(p.next().thePerm);
        }
        return result;
        
    }
	
	class Perm {
		ArrayList<Integer> thePerm;
		public Perm(ArrayList<Integer> perm) {
			thePerm = perm;
		}
		
		@Override 
		public boolean equals(Object bo) {
			if (!(bo instanceof Perm)){
				return false;
			}
			Perm permBo = (Perm)bo;
			if (permBo.thePerm==null && thePerm==null) return true;
			if (permBo.thePerm==null && thePerm!=null ||
				permBo.thePerm!=null && thePerm==null)
				return false;
			if (permBo.thePerm.size()!=thePerm.size()) return false;
			for(int i=0; i<thePerm.size(); i++) {
				if (thePerm.get(i)!=permBo.thePerm.get(i)) {
					return false;
				}
			}
			return true;
		}
		
		@Override 
		public int hashCode() {
			return thePerm.hashCode();
		}
		
	}
	
	private void otherPermuteUnique(Set<Perm> result, ArrayList<Integer> thisResult, int[] num, boolean[] used, int level) {
		if (level==num.length) {
			ArrayList<Integer> copyResult=  new ArrayList<Integer>(thisResult);
			result.add(new Perm(copyResult));
		}
		else {
			for (int i=0; i<num.length; i++) {
				if (used[i]) continue;
				
				thisResult.add(num[i]);
				used[i] = true;
				otherPermuteUnique(result, thisResult, num, used, level+1);
				used[i] = false;
				thisResult.remove(thisResult.size()-1);
			}
		}
	}
	
	private void otherPermute(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> thisResult, int[] num, boolean[] used, int level) {
		if (level==num.length) {
			ArrayList<Integer> copyResult=  new ArrayList<Integer>(thisResult);
			result.add(copyResult);
		}
		else {
			for (int i=0; i<num.length; i++) {
				if (used[i]) continue;
				
				thisResult.add(num[i]);
				used[i] = true;
				otherPermute(result, thisResult, num, used, level+1);
				used[i] = false;
				thisResult.remove(thisResult.size()-1);
			}
		}
	}
	
	private ArrayList<ArrayList<Integer>> doPermute(int[] num, int count) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (count==num.length-1) {
			ArrayList<Integer> justMe = new ArrayList<Integer>();
			justMe.add(num[count]);
			result.add(justMe);
			return result;
		}
		return appendAll(num[count], doPermute(num, count+1));		
	}
	
	private ArrayList<ArrayList<Integer>> appendAll(int prefix, ArrayList<ArrayList<Integer>> partialResult) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		
		for (ArrayList<Integer> perm: partialResult) {
			for(int i=0; i< perm.size(); i++) {
				perm.add(i, prefix);
				result.add(perm);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] test = new int[] {0,1};
		
		Permutations perm = new Permutations();
		perm.permute(test);
	}
}
