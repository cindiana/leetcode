package leetcod;

public class RomanToInt {
	public int romanToInt(String s) {
        int r = 0;
        int maxRight = 0;
        for (int i=s.length()-1; i>=0; i--) {
            int v = map(s.charAt(i));
            if (v>=maxRight) {
                maxRight = v;
                r+=v;
            }
            else 
            {
                r-=v;
            }
        }
        return r;
    }
    private int map(char c) {
        switch(c) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
        }
        return 0;
    }
}
