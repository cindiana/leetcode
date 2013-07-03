package leetcod;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> indexMap = new HashMap<Integer,Integer>();
        for (int i=0; i<numbers.length; i++) {
            indexMap.put(numbers[i],i+1);
        }
        int[] toReturn = new int[2];
        for(int i=0; i<numbers.length; i++) {
        	if (indexMap.containsKey(target-numbers[i])) {
        		toReturn[0] = i+1;
        		toReturn[1] = indexMap.get(target-numbers[i]);
        	}
        }
        return toReturn;
    }
	
}
