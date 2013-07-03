package leetcod;
/*
 * 1. fail [1,0], last inspected is p3<=p2
 * 2. fail [1,2,0]. do not advance p3 when swapping with p2. work through an example to detect.
 */
public class SortColors {
	 public void sortColors_20130623(int[] A) {
	        int nextR = 0;
	        int nextB = A.length-1;
	        int curr = 0;
	        while(curr<=nextB) {
	            if (A[curr]==0) {
	                A[curr] = A[nextR];
	                A[nextR]= 0;
	                nextR++;
	                curr++;
	            }
	            else if(A[curr]==1) {
	                curr++;
	            }
	            else {
	                A[curr] = A[nextB];
	                A[nextB] = 2;                
	                nextB--;
	            }
	        }
	        
	    }
	public void sortColors_20130511(int[] A) {
        if (A==null || A.length<2) return;
        int leftAllZero = 0;
        int rightAllTwo = A.length-1;
        int running = 0;
        
        while(running<=rightAllTwo) {
            if (A[running]==0) {
                swap(A, running, leftAllZero);
                leftAllZero++;                
                running++;
            }
            else if (A[running]==2) {
                swap(A, running, rightAllTwo);
                rightAllTwo--;
            }
            else {
                running++;
            }
        }
        
    }
    
    public void swap_20130511(int[] A, int a, int b) {
        if (a==b) return;
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
    
	public void sortColors(int[] A) {
        if (A==null || A.length<=1) return;
        int p1 = 0;
        int p2 = A.length-1;
        
        while(p1<A.length && A[p1]==0) {
        	p1++;
        }
        int p3 = p1;
        while(p3<A.length && A[p3]==1) {
        	p3++;
        }
        while(p2>=0 && A[p2]==2) {
        	p2--;
        }
        
        while(p3<=p2) {
        	if (A[p3]==0) {
        		swap(A, p3, p1);
        		p1+=1;
        		p3++;
        	}
        	else if (A[p3]==2){
        		swap(A, p3, p2);
        		p2-=1;
        	}
        	else {
        		p3++;
        	}
        }
    }
	
	private void swap(int[] A, int i1, int i2) {
		int temp = A[i1];
		A[i1] = A[i2];
		A[i2] = temp;
	}
}
