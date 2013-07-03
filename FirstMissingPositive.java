package leetcod;

import java.util.BitSet;

/*
 * 1. Using a BitSet of size int.MAX_VALUE causes out of memory error. Removing initial size fixed this
 * 2. Failed [2] due to lower bound of 2nd for loop. 
 */
public class FirstMissingPositive {
	public int firstMissingPositive(int[] A) {
		if (A == null || A.length == 0) {
			return 1;
		}

		BitSet bits = new BitSet();
		int max = 0;
		for (int a : A) {
			if (a > 0) {
				if (a < Integer.MAX_VALUE) {
					bits.set(a - 1);
				}
				if (a > max) {
					max = a;
				}
			}
		}

		int firstMissing = 0;
		for (int i = max - 2; i >= 0; i--) {
			if (!bits.get(i)) {
				firstMissing = i + 1;
			}
		}

		if (firstMissing == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (firstMissing == 0)
			return max + 1;
		else
			return firstMissing;
	}
	
	public int firstMissingPositive2(int[] A) {
		if (A==null || A.length==0) return 1;
        
        int negativeCount = 0;
        for (int i=0; i<A.length; i++) {
            if (A[i]<=0) {
                int temp = A[negativeCount];
                A[negativeCount] = A[i];
                A[i] = temp;
                negativeCount++;
            }
        }
        
        for (int i=negativeCount; i<A.length; i++) { 
            if (Math.abs(A[i])+negativeCount-1 < A.length) {
            	A[Math.abs(A[i])+negativeCount-1] = (-1)*Math.abs(A[Math.abs(A[i])+negativeCount-1]);
            }
        }
        
        for (int i=negativeCount; i<A.length; i++) {
            if (A[i]>0) return (i-negativeCount+1);
        }
        
        return A.length-negativeCount+1;
    }

	public static void main(String[] args) {
		FirstMissingPositive f = new FirstMissingPositive();
		System.out.println(f.firstMissingPositive2(new int[] { 2,1}));// 3,4,-1,1
	}
}
