package leetcod;

public class JumpGameII {
	public int jump_peekedAnswer(int[] A) {
        if (A==null || A.length<=1) return 0;
        int jumps = 0;
        int last = 0;
        int curr = 0;
        
        for (int i=0; i<A.length; i++) {
            if (i>last) {
                last = curr;
                jumps++;
            }
            curr = Math.max(curr, i+A[i]);
        }
        
        return jumps;
    }
	public int jump_20130612(int[] A) {
        if (A==null || A.length<2) return 0;
        int farthest = 0;
        int prev = -1;
        int jumps = 0;
        int localFar = 0;
        
        while(farthest > prev) {
            for (int i=prev+1; i<=farthest; i++) {
                if (i+A[i]>localFar) {
                    localFar = i+A[i];
                }
            }
            
            if (localFar>farthest) {
                prev = farthest;
                farthest = localFar;
                jumps++;
                if (localFar>=A.length-1) return jumps;
            }
            else break;
        } 
        return jumps;        
    }
	public int jump_20130516try1(int[] A) {
        if (A==null || A.length<=1) return 0;
        int jumps = 0;
        int runStart = 0;
        
        while(runStart<A.length-1) {
            if (runStart + A[runStart]>=A.length-1) return jumps+1;
            
            int runMax = runStart;
            for (int i=1; i<=A[runStart]; i++) {
                runMax = Math.max(runMax, runStart+i+A[i+runStart]);
            }
            if (runMax==runStart) return -1;
            runStart = runMax;
            jumps+=2;
        }
        return jumps;
    }
	public int jumpII_2013(int[] A) {
        if (A==null || A.length==0) return -1;
        if (A.length==1) return 0;
        
        int[] minSteps = new int[A.length];
        for (int i=0; i<A.length; i++) {
            minSteps[i] = Integer.MAX_VALUE;
        }
        minSteps[0] = 0;
        int canGoUpTo = 0;
        int current = 0;
        while(current<=canGoUpTo) {
            if (current+A[current]>=A.length-1) return minSteps[current]+1;
            for (int i=1; i<=A[current]; i++) {
                minSteps[i+current] = Math.min(minSteps[i+current], minSteps[current]+1);
            }
            canGoUpTo = Math.max(canGoUpTo, current+A[current]);
            current++;
        }
                
        return -1;
    }

	public int jump_DP(int[] A) {
        if (A==null || A.length<=1) return 0;
        int[] mins = new int[A.length];
        for (int i=0; i<mins.length; i++) {
            mins[i] = Integer.MAX_VALUE;
        }
        
        mins[0] = 0;
        for (int i=0; i<A.length; i++) {
            if (A[i]+i>=A.length) return mins[i]+1;
            for (int j=1; j<=A[i] && (j+i)<A.length; j++) {
                mins[j+i] = Math.min(mins[j+i], mins[i]+1);
                if (j+i==A.length-1) return mins[j+i];
            }
        }
        return mins[A.length-1];
    }
	
	public static void main(String[] args) {
		int[] input = new int[]{10,9,8,7,6,5,4,3,2,1,1,0};
		JumpGameII game = new JumpGameII();
		System.out.println(game.jump(input));
	}
}
