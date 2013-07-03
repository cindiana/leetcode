package leetcod;

public class SearchInRotatedSortedArray {
	public boolean search_20130623(int[] A, int target) {
        int l = 0;
        int r = A.length-1;
        while(l<=r) {
            int m = (l+r)/2;
            if (A[m]==target) return true;
            if (A[l]<A[m]) {
                if (A[l]<=target && A[m]>target) r = m-1;
                else l = m+1;
            }
            else if (A[l]>A[m]) {
                if (A[m]<target && A[r]>=target) l = m+1;
                else r = m-1;
            }
            else {
                if (A[r]!=A[m]) l=m+1;
                else { l++; r--;}
            }
        }
        return false;
    }
	 public boolean search(int[] A, int target) {
	        if (A==null||A.length==0) return false;
	        
	        int l = 0;
	        int r = A.length-1;
	        
	        while(l<=r) {
	            int mid = (l+r)/2;
	            if (A[mid]==target) return true;
	            
	            if (A[mid]<A[r]) { //mid-right is increasing
	                if (A[mid]<target) {
	                    if (target<A[r]) l = mid+1;
	                    else if (target>A[r]) r = mid-1;
	                    else return true;
	                }
	                else {
	                    r = mid-1;            
	                }
	            }
	            else if (A[mid]>A[r]) {//valley is between mid-right
	                if (A[mid]<target) {
	                    l = mid+1;
	                }
	                else {
	                    if (target<A[l]) { l = mid+1; }
	                    else if (target>A[l]) {r = mid-1;}
	                    else return true;
	                }
	            }
	            else {
	                if (A[l]!=A[mid]) {
	                    r = mid -1;
	                }
	                else {
	                    for (int i=l; i<r; i++) {
	                        if (A[i]==target) return true;
	                    }
	                    return false;
	                }
	            }
	        }
	        
	        return false;
	    }
}
