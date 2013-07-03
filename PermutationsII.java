package leetcod;

import java.util.ArrayList;
import java.util.HashSet;

public class PermutationsII {
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num==null||num.length==0) return result;
        
        return doPerm( num, 0);
    }
    
    public ArrayList<ArrayList<Integer>> doPerm(int[] num, int start) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (start==num.length-1) {           
            ArrayList<Integer> me = new ArrayList<Integer>();
            me.add(num[start]);
            result.add(me);
            return result;
        }
        
        ArrayList<ArrayList<Integer>> subResult = doPerm(num, start+1);
        HashSet<ArrayList<Integer>> temp = new HashSet<ArrayList<Integer>>();
        for (ArrayList<Integer> sub: subResult) {
            ArrayList<Integer> addOne = (ArrayList<Integer>)sub.clone();
            addOne.add(0, num[start]);
            temp.add(addOne);
            for (int i=0; i<sub.size(); i++) {
                if (sub.get(i)!=num[start]) {
                    ArrayList<Integer> addMe = (ArrayList<Integer>)sub.clone();
                    addMe.add(i+1, num[start]);
                    temp.add(addMe);
                }
            }
        }
        return new ArrayList<ArrayList<Integer>>(temp);
    }
}
