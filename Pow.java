package leetcod;
/*
 * 1. fail -2.14, 3. clean up pre-processing for sign and flip.
 * 2. fail server. more clean up pre-processing. Be aware of what I am trying to achieve and focus.
 * 3. time limit exceed for big test.
 */
public class Pow {
	public double pow_20130528(double x, int n) {
        if (n==0) return 1.0;
        if (x==0.0) return 0.0;
        boolean negative = false;
        if (n<0) negative = true;
        n = Math.abs(n);
        
        double result;
        double f = pow(x, n/2);
        if (n%2==0) {            
            result = f*f;
        }
        else {
            result = x*f*f;
        }
        
        if (negative) return 1.0/result;
        return result;
    }
    public double pow_iterate_20130528(double x, int n) {
        if (n==0) return 1.0;
        if (x==0.0) return 0.0;
        boolean negative = false;
        if (n<0) negative = true;
        n = Math.abs(n);        
        int k = n;
        double result = 1;
        while(k>0) {
            double run = x;
            int power = 1;
            while((power<<1)<=k) {
                power = power<<1;
                run*=run;
            }
            result*=run;
            k -= power;
        }
        if (negative) return 1.0/result;
        return result;
    }
    
	public double pow(double x, int n) {
        if (n==0) {
        	return 1;
        }
        if (n==1) {
        	return x;
        }
        boolean flip = n<0;
        if (flip) n = n*(-1);
        
        boolean negative = x<0 && n%2==1;
        if (x<0) {
        	x = x*(-1);
        }
        
        double positivePow = doPow(x, n);
        double withSign = negative? positivePow*(-1) : positivePow;
        if (flip) {
        	return 1.0/withSign;
        }
        return withSign;
    }
	
	private double doPow(double x, int n) {
		if (n==1) {
			return x;
		}
		double half = doPow(x, n/2);
		if (n%2==0){			
			return half*half;
		}
		else {
			return half*half*x;
		}
	}
}
