package leetcod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FourSum {
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num==null || num.length<4) return result;
        
        HashSet<ArrayList<Integer>> unique = new HashSet<ArrayList<Integer>>();
        Arrays.sort(num);
        for (int i=0; i<num.length; i++) {
            for (int j=i+1; j<num.length; j++) {
                int twoSum = num[i] + num[j];
                int p1 = j+1;
                int p2 = num.length-1;
                while(p1<p2)
                {
                    if (twoSum+num[p1]+num[p2]==target) {
                        ArrayList<Integer> solution = new ArrayList<Integer>();
                        solution.add(num[i]);
                        solution.add(num[j]);
                        solution.add(num[p1]);
                        solution.add(num[p2]);
                        unique.add(solution);
                        while(p1+1<num.length-1 && num[p1]==num[p1+1]) p1++;
                        while(p2>p1 && num[p2]==num[p2-1]) p2--;
                        p1++;
                        p2--;
                    }
                    else if(twoSum+num[p1]+num[p2]<target) {
                        p1++;
                    }
                    else {
                        p2--;
                    }
                }
            } 
        }
        
        return new ArrayList<ArrayList<Integer>>(unique);
    }
}
