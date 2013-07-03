package leetcod;

import java.util.ArrayList;
 /*
 * 0-error!
 */
import java.util.List;

public class Triangle {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle==null || triangle.size()==0) return 0;
        if (triangle.size()==1) {
            if (triangle.get(0).size()==1) {
                return triangle.get(0).get(0);
            }
            return 0;
        }
        
        List<Integer> lastRunningSum = new ArrayList<Integer>();
        for (int i: triangle.get(triangle.size()-1)) {
            lastRunningSum.add(i);
        }
        
        int numRuns = triangle.size()-1; 
        while(numRuns>0) {
            List<Integer> thisRunningSum = new ArrayList<Integer>();
            for (int i=0; i<triangle.get(numRuns-1).size(); i++) {
                thisRunningSum.add(Math.min(lastRunningSum.get(i), lastRunningSum.get(i+1))
                    + triangle.get(numRuns-1).get(i));
            }
            lastRunningSum = thisRunningSum;
            numRuns--;
        }
        
        return lastRunningSum.get(0);
    }
}