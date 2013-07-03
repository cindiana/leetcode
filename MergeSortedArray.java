package leetcod;

/*
 * 1. runtime err. error in using index in for loop
 */
public class MergeSortedArray {
	public void merge_20130508(int A[], int m, int B[], int n) {
        int tail = m+n-1;
        int tailA = m-1;
        int tailB = n-1;
        while(tailA>=0 && tailB>=0) {
            if (A[tailA]>B[tailB]) {
                A[tail] = A[tailA];
                tailA--;
            }
            else {
                A[tail] = B[tailB];
                tailB--;
            }
            tail--;
        }
        while(tailB>=0) {
            A[tail] = B[tailB];
            tailB--;
            tail--;
        }
    }
	
	public void merge(int A[], int m, int B[], int n) {
		if (n==0 || B==null) {
			return;
		}
		if (m==0) {
			for(int i :B) {
				A[m++] = i;
			}
			return;
		}
		
		int j=0;
        while(j<n) {
        	if (A[m-1]<B[j]){
        		break;
        	}
        	j++;
        }
        // B[0,j-1] go in A. count:j. if j==n, all of B go into A. 
        //append B[j,n-1] to A+j
        for(int i=j; i<n; i++) {
            A[m+i] = B[i];
        }
        
        int k=0;
        while(k<m){
        	if (A[k]>B[0]) {
        		break;
        	}
        	k++;
        }//A[0,k-1] can stay. when k==m, B append after A.
        
        
        A[m-1+j] = A[m-1];
        int bIndex = j-1;
        int aIndex = m-2;
        int currIndex = m-2+j;
        while(bIndex>=0 && aIndex>=k) {
        	if(B[bIndex]>=A[aIndex]) {
        		A[currIndex] = B[bIndex];
        		bIndex--;
        	}
        	else {
        		A[currIndex] = A[aIndex];
        		aIndex--;
        	}
        	currIndex--;
        }
        while(aIndex>=k) {
        	A[currIndex] = A[aIndex];
        	aIndex--;
        }
        
        while(bIndex>=0) {
        	A[currIndex] = B[bIndex];
        	bIndex--;
        	currIndex--;
        }
    }
	
	public static void main(String[] args) {
		MergeSortedArray msa = new MergeSortedArray();
		int[] A=new int[6];
		A[0] = 1;A[2]=2;A[3]=3;
		int[] B=new int[]{4,5,6};
		msa.merge(A, 3, B, 3);
	}
}
