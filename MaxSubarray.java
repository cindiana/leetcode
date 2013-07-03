package leetcod;

/*
 * not agree with leetcode with definition of subarray. modify and then passed all.
 */
public class MaxSubarray {
	public int maxSubArray_20130526(int[] A) {
        if (A==null || A.length==0) return 0;
        int max = A[0];
        int running = 0;
        
        for (int i:A) {
            if (running+i>=0) {
                running = running+i;
                if(running>max) max = running;
            }else {
                running = 0;
                if (i>max) max = i;
            }
        }
        return max;
    }
	
	public int maxSubArray(int[] A) {
        if (A==null || A.length==0) return 0;
        if (A.length==1) { return A[0];}
        
        int i = 0;
        int max = A[0];       
        int thisRun = 0;
        while(i<A.length) {
        	thisRun+=A[i];
        	max = Math.max(thisRun, max);
        	if(A[i]<0) {
        		if (thisRun<=0) {
        			thisRun = 0;
        		}
        	}
        	i++;
        }
        
        return max;
	}
}
