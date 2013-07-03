package leetcod;
/*
 * 1. fail [9,9]. I always add 1. Wrong.
 */
public class PlusOne {
	public int[] plusOne_20130523(int[] digits) {
        if (digits==null || digits.length==0) return new int[]{1};
        
        if (digits[digits.length-1]<9) {
            digits[digits.length-1] = digits[digits.length-1]+1; 
            return digits;
        }
        int carry = 1;
        int index = digits.length-1;
        while(carry>0 && index>=0) {
            int digit = digits[index];
            digits[index] = (digit+1)%10;
            carry = (digit+1)/10;
            index--;
        }
        if (index<0 && carry>0) {
            int[] oneMoreDigit = new int[digits.length+1];
            for (int i=0; i<digits.length; i++) {
                oneMoreDigit[i+1] = digits[i];
            }
            oneMoreDigit[0] = 1;
            return oneMoreDigit;
        }
        return digits;
    }
	
	public int[] plusOne(int[] digits) {
        if (digits==null || digits.length==0) {
        	return digits;
        }
        
        int index = digits.length-1;
        int carry = 0;
        while(index>=0) {
        	int sum = digits[index] + (index==digits.length-1? 1:0) + carry;
        	if (sum<10) {
        		digits[index]  =sum;
        		return digits;
        	}
        	else {
        		digits[index] = sum%10;
        		carry = sum/10;
        	}
        	index--;
        }
        
        if (carry>0) {
        	int[] newDigits = new int[digits.length+1];
        	newDigits[0] = carry;
        	for (int i=0; i<digits.length; i++) {
        		newDigits[i+1] = digits[i];
        	}
        	return newDigits;
        }
        else {
        	return digits;
        }
    }
}
