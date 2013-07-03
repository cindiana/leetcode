package leetcod;

/*
 * 1. Runtime error caused by using Arrays.fill. Abort cuz want to use primitive array.
 * 2. first round of matrix population overlooked boundary: we can step out side of matrix.
 * 3. got for loop upper boundary all wrong at 3 places.
 * 4. memory oob running big test. Matrix approach fail
 * 5. recursive code exceed time limit.??? What's next???
 */
public class JumpGame {
	public boolean canJump_20130612(int[] A) {
        if (A==null || A.length<2) return true;
        int farthest = 0;
        int prev = -1;
        int localFar = 0;
        
        while(farthest > prev) {
            for (int i=prev+1; i<=farthest; i++) {
                if (i+A[i]>localFar) {
                    localFar = i+A[i];
                }
            }
            if (localFar>=A.length-1) return true;
            if (localFar>farthest) {
                prev = farthest;
                farthest = localFar;
            }
            else break;
        } 
        return farthest>A.length-1;
    }
	
	public boolean canJump(int[] A) {
		if (A==null || A.length==0) {
        	return false;
        }
		return doCanJump(A, 0, A.length-1);
	}
	private boolean doCanJump(int[] A, int a, int b) {
		if (a==b || A[a]>=(b-a)) {
			return true;
		}
		for (int i=a+1; i<b; i++) {
			if (doCanJump(A, a, i) && doCanJump(A, i,b)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean canJumpMatrix(int[] A) {
        if (A==null || A.length==0) {
        	return false;
        }
        boolean[][] matrix = new boolean[A.length][A.length];
        
        for(int i=0; i<A.length; i++) {
        	int step = 0;
        	while(step<=A[i] && step+i<=A.length-1) {
        		if (i==0 && step==A.length-1) {return true;}
        		matrix[i][i+step] = true;
        		step++;
        	}
        }
        
        for(int i=0; i<A.length; i++) {
        	for (int j=i+2; j<A.length; j++) {
        		if (matrix[i][j]) {
        			continue;
        		}
        		for (int k=i+1;k<j;k++) {
        			if  (matrix[i][k]&&matrix[k][j]) {
        				matrix[i][j] = true;
        				break;
        			}
        		}
        	}
        }
        
        return matrix[0][A.length-1];
    }
	
	public boolean canJump_2013(int[] A) {
        if (A==null || A.length==0) return false;
        if (A.length==1) return true;
        
        int canGoUpTo = 0;
        int current = 0;
        while(current<=canGoUpTo) {
            if (current+A[current]>=A.length-1) return true;
            canGoUpTo = Math.max(canGoUpTo, current+A[current]);
            current++;
        }
                
        return false;
    }
	
	public static void main(String[] args) {
		int[] p = new int[] {1,2,3};
		JumpGame jg = new JumpGame();
		System.out.println(jg.canJump(p));
	}
}
