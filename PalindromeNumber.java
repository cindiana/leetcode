package leetcod;

/*
 * 1. fail 10; loop condition missing =
 * 2. fail 1001, algorithm design err.
 */
public class PalindromeNumber {
	public boolean isPalindrome_peeked(int x) {
        if (x<0) return false;
        if (x<10) return true;
        int div = 1;
        while(x/div>=10) {
            div*=10;
        }
        
        while(x>9) {
            int left = x/div;
            int right = x%10;
            if (left!=right) return false;
            x = (x%div)/10;
            div/=100;
        }
        
        return true;
    }
	
	public boolean isPalindrome_20130518(int x) {
        if (x<0) return false;
        if (x<10) return true;
        int digitCount = 0;
        int weight = 1;
        boolean less10=true;

        while(x/weight>0) {
            digitCount++;
            if (Integer.MAX_VALUE/weight>=10) {
            	weight*=10;
            }
            else {
            	less10 = false;
            	break;
            }
        }
        if (less10) weight/=10;
        int rW = 10;
        for (int i=0; i<digitCount/2; i++) {
        	int left = (x/weight)%10;
            int right = (x%rW)/(rW/10);
            if (left!=right) return false;
            weight/=10;
            rW*=10;
        }
        return true;
    }
	
	public boolean isPalindrome(int x) {
		if (x<0) {
			return false;
		}
        if (x<10) {
        	return true;
        }
        
        int y = x;
        int base = 1;
        while(y>=10) {
        	base*=10;
        	y=y/10;
        }
        
        int z = x;
        int first = y;
        y = x;
        while(base>=10) {
        	if (first!=z%10) {
        		return false;
        	}
        	z = z/10;
        	y = y-base*first;	
        	base/=10;
        	first = y/base;
        }
        return true;
    }
	
	public static void main(String[] args) {
		PalindromeNumber pn = new PalindromeNumber();
		pn.isPalindrome_20130518(2147447412);
	}
}
