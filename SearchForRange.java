package leetcod;

/*
 * 1. runtime err. need to check boundary when peeking +1 -1 elements.
 * 2. fail [1] 1. consider found rStart/rEnd when we reach array boundary.
 */
public class SearchForRange {
	public int[] searchRange_20130518(int[] A, int target) {
        int[] result = new int[] {-1, -1};
        if (A==null || A.length==0) return result;
                
        int l = 0;
        int r = A.length-1;
        int left = -1;
        while(l<=r) {
            int mid = (l+r)/2;
            if (A[mid]==target) {
                if (mid==0 || A[mid-1]<target) {
                    left = mid; break;
                }
                else {
                    r = mid-1;
                }
            }
            else if (A[mid]<target) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        if (left==-1) return result;
        l = left+1;
        r = A.length-1;
        int right = left;
        while(l<=r) {
            int mid = (l+r)/2;
            if (A[mid]==target) {
                if (mid==A.length-1 || A[mid+1]>target) {
                    right = mid; break;
                }
                else {
                    l = mid+1;
                }
            }
            else if (A[mid]>target) {
                r = mid-1;
            } 
            else {
                l = mid+1;
            }
        }
        return new int[]{left, right};
    }
	public int[] searchRange(int[] A, int target) {
		int rStart = -1;
		int rEnd = -1;
		if (A == null || A.length == 0 || A[0] > target
				|| A[A.length - 1] < target)
			return new int[] { rStart, rEnd };

		int l = 0;
		int r = A.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (A[mid] == target) {
				if (mid < A.length - 1 && A[mid + 1] > target ||
					mid ==A.length-1) {
					rEnd = mid;
					break;
				}
				l = mid + 1;
			} else if (A[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}

		l = 0;
		r = A.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (A[mid] == target) {
				if (mid > 0 && A[mid - 1] < target || mid==0) {
					rStart = mid;
					break;
				}
				r = mid - 1;
			} else if (A[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}

		return new int[] { rStart, rEnd };
	}
	public static void main(String[] args) {
		SearchForRange sfr = new SearchForRange();
		System.out.println(sfr.searchRange_20130518(new int[] {0,0,1,1,1,2,4,4,4,4,5,5,5,6,8,8,9,9,10,10,10}, 8));
	}
}
