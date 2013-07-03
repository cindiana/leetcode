package leetcod;

public class RemoveDupFromSortedArray {
	public int removeDuplicatesII_20130526(int[] A) {
        if (A==null || A.length==0) return 0;
        if (A.length<3) return A.length;
        
        int p1 = 1;
        int p2 = 1;
        boolean over2 = false;
        while(p2<A.length) {
            if (A[p2]==A[p1-1]) {
                if(!over2) {
                    over2 = true;
                }
                else {
                    p2++;
                    continue;
                }
            }
            else {
                over2 = false;
            }
               
            A[p1] = A[p2];            
            p1++;
            p2++;
        }
        return p1;
    }
	
	public int removeDuplicates(int[] A) {
        if (A==null || A.length==0) return 0;
        if (A.length==1) return 1;
        int p1 = 1; //scavenger
        int p2 = 1; //next write slot
        while(p1<A.length) {
            if (A[p1]!=A[p1-1]) {
                if (p1!=p2) {
                    A[p2] = A[p1];
                }
                p1++;
                p2++;
            }
            else {
                p1++;
            }
        }
        
        return p2;
    }
}
