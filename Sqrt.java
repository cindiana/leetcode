package leetcod;

public class Sqrt {
	public int sqrt_20130522(int x) {
        if (x<=0) return 0;
        if (x==1) return 1;
        
        int l = 0;
        int r = x;
        while(l<r) {
            int mid = (l+r)/2;
            int s = x/mid;
            if (s>mid && s-mid<3 || mid==s) return mid;
            
            r = Math.max(s, mid);
            l = Math.min(s, mid);
            
        }
        return 0;
        
    }
	
	public int sqrt_wikipedia(int x) {
        int res = 0;
        int bit = 1<<30; // The second-to-top bit is set: 1L<<30 for long
 
        // "bit" starts at the highest power of four <= the argument.
        while (bit > x)
        bit >>= 2;
 
        while (bit != 0) {
            if (x >= res + bit) {
                x -= res + bit;
                res = (res >> 1) + bit;
            }
            else
                res >>= 1;
            bit >>= 2;
        }
        return res;
    }
	
	public static void main(String[] args) {
		Sqrt s = new Sqrt();
		s.sqrt_20130522(2147483647);
	}
}
