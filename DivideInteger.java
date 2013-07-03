package leetcod;

/*
 * 1. forgot to check for divisor==0 for err, and divisor==1 for easy return.
 * 2. missed zheng-chu case by missing a - in while termination condition.
 * 3. didn't handle negative int
 */
public class DivideInteger {
	public int divide_20130518(int dividend, int divisor) {
		if (divisor==0) return 0;
        if (dividend==divisor) return 1;
        int sign = (dividend>0&&divisor>0 || dividend<0&&divisor<0)? 1:-1;
        
        
        long longdividend = Math.abs((long)dividend);
        long longdividor = Math.abs((long)divisor);
        
        if (longdividend<longdividor) return 0;
        
        int result = 0;
        
        while(longdividend>=longdividor){
            int tempResult = 1;
            long temp = longdividor;
            while((temp<<1)>0 && (temp<<1) < longdividend) {
                temp = temp<<1;
                tempResult = tempResult<<1;
            }
            longdividend-=temp;
            result+=tempResult;
        }
        
        
        
        if (sign==-1) {
            return (result ^ -1) + 1;
        }
        return result;
    }

	public static void main(String[] args) {
		DivideInteger d = new DivideInteger();
		System.out.println(d.divide_20130518(-2147483648, 1));
	}
}
