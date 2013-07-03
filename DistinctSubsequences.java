package leetcod;

public class DistinctSubsequences {
	public int numDistinct_20130527(String S, String T) {
        if (S.length()<T.length()) return 0;
        
        int[][] m = new int[S.length()+1][T.length()+1];
        
        for (int i=0; i<T.length()+1; i++) m[0][i] = 0;
        for (int i=0; i<S.length()+1; i++) m[i][0] = 1;
        
        for (int i=1; i<S.length()+1; i++) {
            for (int j=1; j<T.length()+1; j++) {
                if (S.charAt(i-1)==T.charAt(j-1)) {
                    m[i][j] = m[i-1][j-1]+m[i-1][j];
                }
                else {
                    m[i][j] = m[i-1][j];
                }
            }
        }
        return m[S.length()][T.length()];
    }
	
	public int numDistinct2(String S, String T) {
        if (S.length()<T.length()) return 0;
        if (S.equals(T)) return 1;
        if (T.length()==0) return 1;
        if (T.length()==1) {
            int count = 0;
            for (int i=0; i<S.length(); i++) {
                if(S.charAt(i)==T.charAt(0)) count++;
            }
            return count;
        }
           
        int count = 0;
        int i = S.indexOf(T.charAt(0));
        while(i>=0) {
            count+=numDistinct2(S.substring(i+1),T.substring(1));
            i = S.indexOf(T.charAt(0), i+1);
        }
        return count;
    }
	
	public int numDistinct(String S, String T) {
        if (S.length()<T.length()) return 0;
        if (S.equals(T)) return 1;
        int ps=0;
        int pt=0;
        int ways = 1;
               
        while(ps<S.length()) {
            while(ps<S.length() && S.charAt(ps)!=T.charAt(pt)) {//align S with first char of T
               ps++;
           }
           if (ps>S.length()-1 || S.length()-ps<T.length()-pt) return 0;
           int runLen = 0;
           while(pt<T.length() && ps<S.length() && S.charAt(ps)==T.charAt(pt) && (runLen==0||T.charAt(pt)==T.charAt(pt-1))){
               ps++; pt++;
               runLen++;
           }
           if (pt<T.length() && T.charAt(pt)==T.charAt(pt-1)) return 0; //T run is longer than S run for current char
           int extraCount =0;
           while(ps<S.length() && S.charAt(ps)==T.charAt(pt-1)) {
               ps++;
               extraCount++;
           }
           ways*=combi(runLen+extraCount, extraCount);
           if (pt==T.length()) pt = 0;//start over
       }
        if(pt<T.length()-1) return 0; //T is not exhausted while S is
        return ways;
    }
    public int combi(int a, int b) {
        if (a==b) return 1;
        if (b==1 || b ==a-1) return a;
        return fact(a)/(fact(b)*fact(a-b));
    }
    public int fact(int f) {
        int k = 1;
        for (int i=2;i<f; i++) {
            k*=i;
        }
        return k;
    }
    
    public static void main(String[] args) {
    	DistinctSubsequences ds = new DistinctSubsequences();
    	System.out.println(ds.numDistinct2("daacaedaceacabbaabdccdaaeaebacddadcaeaacadbceaecddecdeedcebcdacdaebccdeebcbdeaccabcecbeeaadbccbaeccbbdaeadecabbbedceaddcdeabbcdaeadcddedddcececbeeabcbecaeadddeddccbdbcdcbceabcacddbbcedebbcaccac", "ceadbaa"));
    }
}
