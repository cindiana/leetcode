package leetcod;

/*
 * 1. sanity check missed length==0
 * 2. use MIT algorithm
 */
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int A[], int B[]) {
        if (A==null && B==null) return 0.0;
        if (A==null || A.length==0) return medianOfArray(B);
        if (B==null || B.length==0) return medianOfArray(A);
        
        return doWork(A, B, 0, A.length-1);
    }
    
    public double doWork(int[]A, int[] B, int l, int r) {
        if (l>r)
            return doWork(B, A, Math.max(0, (A.length+B.length)/2-A.length), Math.min(B.length, (A.length+B.length)/2));
        int i = (l+r)/2;
        int j = (A.length+B.length)/2 - i -1;
        if (j>=0 && j<B.length && B[j]>A[i])
            return doWork(A, B, i+1, r);
        else if (j>-2&& j<B.length-1 && B[j+1]<A[i])
            return doWork(A, B, l, i-1);
        else {
            if ((A.length+B.length)%2==1) return A[i]*1.0;
            else{
                if (j<0) {
                	return (A[i]+A[i-1])/2.0;
                }
                else {
                	if (j>B.length-1) {
                		j = B.length-1;
                    }
                	if (i==0) return (A[i]+B[j])/2.0;
                	else return (A[i] + Math.max(B[j], A[i-1]))/2.0;
                }
            }
        }
    }
    
    public double medianOfArray(int[] A) {
        if (A==null || A.length==0) return 0.0;
        if (A.length%2==0) {
            return (A[A.length/2]+A[A.length/2 - 1])/2.0;
        }
        return A[A.length/2];
    }
	public static void main(String[] args) {
		MedianOfTwoSortedArrays motsa = new MedianOfTwoSortedArrays();
		int[] B = new int[]{1,2,3,4,6,7};
		int[] A = new int[]{5};
		System.out.println(motsa.findMedianSortedArrays(A, B));
	}
}
