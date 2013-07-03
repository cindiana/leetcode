package leetcod;

import java.util.ArrayList;

/*
 * 1. typo: i typed 1
 * 2. weird 1+2=>4 error involving 1<<(n-1). Move it out since it only needs to be calculated once anyway
 * 3. failed to return n=0;
 */
public class GrayCode {
	public ArrayList<Integer> grayCode_20130525(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        if (n==0) return result;
        int i = 0;
        while(i<n) {
            ArrayList<Integer> next = new ArrayList<Integer>();
            boolean even = true;
            for (int r : result) {
                if (even) {
                    next.add(r<<1);
                    next.add((r<<1) + 1);
                    even = false;
                }
                else {
                    next.add((r<<1) + 1);
                    next.add(r<<1);
                    even = true;
                }
            }
            
            result = next;
            i++;
        }
        
        return result;
    }
	
	public ArrayList<Integer> grayCode(int n) {
		if (n<0) {
			return new ArrayList<Integer> ();
		}
		if (n==0) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(Integer.valueOf(0));
			return temp;
		}
        return doGray(n);
        
    }
	private ArrayList<Integer> doGray(int n){
		if (n==1) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(Integer.valueOf(0));
			temp.add(Integer.valueOf(1));
			return temp;
		}
		
		ArrayList<Integer> prev = doGray(n-1);
		ArrayList<Integer> current = new ArrayList<Integer>();
		// prefix 0
		for(int i=0; i<prev.size(); i++) {
			current.add(prev.get(i));
		}
		// prefix 1
		int prefix = 1<<(n-1);
		for (int i=prev.size()-1; i>=0; i-- ) {
			current.add(prev.get(i) + prefix);
		}
		return current;
	}
	
	public static void main(String[] args) {
		GrayCode g = new GrayCode();
		for (Integer i : g.grayCode(2)) {
			System.out.println(i);
		}
	}
}
