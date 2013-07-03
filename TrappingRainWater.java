package leetcod;

import java.util.HashMap;
import java.util.Map;

/*
 * 1. fail [2,0,2]. boundary err when ignoring lines.
 * 2. fail [2,0,2]. failing to work thru example leads to wrong understanding of problem itself.
 * 3. exceed time limit. no condition modifier in outer while loop.
 * 4. fail [2,0,2]. trapStart is inclusive. Also trapEnd can == trapStart in elevation.
 * 5. fail [2,0,2]. min - short plateaue
 * 6. fail [4,2,3]. algorithm err in finding trapEnd.
 * 7. fail [5,4,1,2]. bug in getWater to add negative to sum.
 * 8. fail [5,5,1,7,1,1,5,2,7,6]. logic bug in trapend condition.
 * 9. fail [0,1,0,2,1,0,1,3,2,1,2,1]. algorithm err. end needs not to pass start.
 */
public class TrappingRainWater {
	/*
	 * 填平法。遇坑填平，填多了减掉。
	 */
	// 1-pass corrrect!
	public int trap_20130512(int[] A) {
        if (A==null || A.length<3) return 0;
        int water = 0;
        Stack<Integer> s = new Stack<Integer>();
        if (A[0]>A[1]) s.push(A[0]);
        for (int i=1; i<A.length; i++) {
            if (s.isEmpty() || A[i]<s.peek()) {
                s.push(A[i]);
            }
            else {
                int popCount = 0;
                int leftHeight = 0;
                while(!s.isEmpty() && s.peek()<A[i]) {
                    if (s.size()==1) leftHeight = s.peek();
                    popCount++;
                    water += A[i]-s.pop();
                }
                if (!s.isEmpty()) {
                    for (int j=0; j<=popCount; j++) {
                        s.push(A[i]);
                    }
                }
                else {
                    water -= popCount*(A[i]-leftHeight);
                    s.push(A[i]);
                }
            }
        }
        return water;
    }
	public int trap(int[] A) {
		if (A == null || A.length < 2) {
			return 0;
		}
		int[] maxFromLeft = new int[A.length];
		maxFromLeft[0] = A[0];
		int[] maxFromRight = new int[A.length];
		maxFromRight[A.length-1] = A[A.length-1];
		
		for (int i=1; i<A.length; i++) {
			if (A[i]>maxFromLeft[i-1]) {
				maxFromLeft[i] = A[i];
			}
			else {
				maxFromLeft[i] = maxFromLeft[i-1];
			}
		}
		for (int i=A.length-2; i>=0; i--) {
			if (A[i]>maxFromRight[i+1]) {
				maxFromRight[i] = A[i];
			}
			else {
				maxFromRight[i] = maxFromRight[i+1];
			}
		}
		int sum = 0;
		for (int i=1; i<A.length-1; i++) {
			sum += Math.min(maxFromLeft[i], maxFromRight[i])-A[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		TrappingRainWater rt = new TrappingRainWater();
		rt.trap(new int[] { 2,0,2 });
	}
}
