package leetcod;

/*
 * 1. recursive code run out of JVM when n gets big. Need to write iterative version. 
 */
public class ClimbingStairs {    
	public int climbStairs_2013(int n) {
        if (n<=0) return 0;
        if (n==1) return 1;
        if (n==2) return 2;
        int start = 2;
        int current = 2;
        int prev = 1;
        while(n>start) {
            int next = current+prev;
            prev = current;
            current = next;
            
            start++;
        }
        
        return current;
    }
	
	
    public int climbStairs(int n) {
        //return doClimb(n);
        return climb(n);
    }
    
    private int climb(int n) {
        if (n==1) return 1;
        if (n==2) return 2;
        
        int prev = 1;
        int current = 2;
        for (int i=2; i<n; i++) {
            int temp = current+prev;
            prev = current;
            current = temp;            
        }
        return current;
    }
    
    
    private int doClimb(int n) {
        if (n==1) return 1;
        if (n==2) return 2;
        return doClimb(n-1) + doClimb(n-2);
    }
}
