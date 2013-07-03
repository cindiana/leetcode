package leetcod;

/*
 * 1. typo failed most cases
 * 2. fail [1,3,2], algorithm err.
 * 3. fail [2,3,1], algorithm more err. When fixing index bug, look around and fix related.
 * 4. fail [3,2,1], mindless err in reversing array to lose half values. Should swap instead.
 */
public class NextPermutation {
	public void nextPermutation(int[] num) {
        if (num==null || num.length<2) {
        	return;
        }
        
        int swapIndex = 0;
        int minBiggerIndex = 0;
        for (int i=1; i<num.length; i++)
        {
        	if (num[i]>num[i-1]) {
        		swapIndex = i;
        		minBiggerIndex = i;
        	}
        	else if (swapIndex>0){
        		if (num[i]>num[swapIndex-1]){
        			if (minBiggerIndex==0 || num[i] - num[swapIndex-1] < num[minBiggerIndex] - num[swapIndex-1]){
        				minBiggerIndex = i;
        			}
        		}
        	}
        }
        
        if (swapIndex>0) {
        	int temp = num[swapIndex-1];
        	num[swapIndex-1] = num[minBiggerIndex];
        	num[minBiggerIndex] = temp;
        	//sort[i,] asce
        	int length = num.length-swapIndex-1;
        	while(length>0) {
        		for(int i=0; i<length; i++) {
        			if (num[i+swapIndex]>num[i+swapIndex+1]){
        				swap(num, i+swapIndex, i+swapIndex+1);
        			}
        		}
        		length--;
        	}
        }
        else {
        	for (int i=0; i<num.length/2; i++) {
        		swap(num, i, num.length-1-i);
        	}
        }
    }
	
	private void swap(int[] input, int a, int b) {
		int temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
	
	public static void main(String[] args) {
		int[] input = new int[]{1,3,2};
		NextPermutation np = new NextPermutation();
		np.nextPermutation(input);
	}
}
