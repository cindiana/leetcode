package leetcod;

import java.util.ArrayList;
import java.util.Arrays;

public class SubSets {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        return doWork_subSets(S, 0);
    }
    
    public ArrayList<ArrayList<Integer>> doWork_subSets(int[] sorted, int start) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (start ==sorted.length-1) {
            ArrayList<Integer> hasMe = new ArrayList<Integer>();
            hasMe.add(sorted[start]);
            result.add(hasMe);
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        ArrayList<ArrayList<Integer>> subSets = doWork(sorted, start+1);
        for (ArrayList<Integer> set : subSets) {
            ArrayList<Integer> newSet = new ArrayList<Integer>();
            newSet.addAll(set);
            newSet.add(0, sorted[start]);
            result.add(newSet);
            result.add(set);
        }
        return result;
    }
    
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        return doWork(num, 0);
    }
    
    public ArrayList<ArrayList<Integer>> doWork(int[] sorted, int start) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (start==sorted.length-1) {
            ArrayList<Integer> hasMe = new ArrayList<Integer>();
            hasMe.add(sorted[start]);
            result.add(hasMe);
            
            ArrayList<Integer> noMe = new ArrayList<Integer>();
            result.add(noMe);
            return result;
        }
        if (sorted[start]==sorted[sorted.length-1]) {
            int numDups = sorted.length-start;
            for (int i=0; i<numDups+1; i++) {
                ArrayList<Integer> iDups = new ArrayList<Integer>();
                for (int j=0; j<i; j++) {
                    iDups.add(sorted[start]);
                }
                result.add(iDups);
            }
            return result;
        }
        
        int next = start+1;
        while(next<sorted.length && sorted[start]==sorted[next]) {
            next++;
        }
        ArrayList<ArrayList<Integer>> subSets = doWork(sorted, next);
        for (ArrayList<Integer> set: subSets) {
            for (int i=0; i<next-start+1; i++) {
                ArrayList<Integer> newSet = new ArrayList<Integer>();
                newSet.addAll(set);
                for (int j=0; j<i; j++) {
                    newSet.add(0, sorted[start]);
                }
                result.add(newSet);
            } 
        } 
        return result;
    }
}
