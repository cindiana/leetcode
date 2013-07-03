package leetcod;

public class RemoveElement {
	public int removeElement_20130516(int[] A, int elem) {
        if (A==null || A.length==0) return 0;
        int leftAllGood = 0;
        int rightAllShit = A.length-1;
        
        while(leftAllGood<=rightAllShit) {
            if (A[leftAllGood]!=elem) {leftAllGood++;continue;}
            if (A[rightAllShit]==elem) {rightAllShit--;continue;}
            if (A[leftAllGood]==elem){
                A[leftAllGood] = A[rightAllShit];
                leftAllGood++;
                rightAllShit--;
            }
        }
        return leftAllGood;
    }
	public int removeElement(int[] A, int elem) {
        if (A==null || A.length==0) {
        	return 0;
        }
   
        int p1=0;
        int p2=0;
        while(p1<A.length) {
        	if (A[p1]!=elem){
        		A[p2] = A[p1];
        		p2++;
        	}
        	p1++;
        }
        return p2;
    }
}
