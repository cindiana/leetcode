package leetcod;

public class SearchInsertPosition {
	public int searchInsert(int[] A, int target) {
        if (A==null || A.length<1) return -1;
        if (target<A[0]) return 0;
        if (target>A[A.length-1]) return A.length;
        
        int left = 0;
        int right = A.length-1;
        while(left<=right) {
            int mid =(left+right)/2;
            if (A[mid]==target) return mid;
            if (A[mid]<target) {
                if (mid+1<A.length && A[mid+1]>target) return mid+1;
                else left = mid+1;
            }
            else if(A[mid]>target) {
                if(mid>0 && A[mid-1]<target) return mid;
                else right = mid-1;
            }
        }
        return -1;
    }
}
