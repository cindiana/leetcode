package leetcod;
import java.util.ArrayList;
import java.util.List;

/*
 * 1. didn't realize p==elements.length
 * 2. forgot to append element at currIndex
 * 3. error in preparing return value for base case.
 */
public class Combination {
	public ArrayList<ArrayList<Integer>> combine_20130623_1min1pass(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (k>n) return result;
        
        if (k==n) {
            ArrayList<Integer> all = new ArrayList<Integer>();
            for (int i=1; i<=n; i++) all.add(i);
            result.add(all);
            return result;
        }
        if (k==1) {
            for (int i=1; i<=n; i++) {
                ArrayList<Integer> justMe = new ArrayList<Integer>();
                justMe.add(i);
                result.add(justMe);
            }
            return result;
        }
        
        ArrayList<ArrayList<Integer>> hasMe = combine(n-1, k-1);
        ArrayList<ArrayList<Integer>> hasMeNot = combine(n-1, k);
        for (ArrayList<Integer> l:hasMe) {
            l.add(n);
        }
        result.addAll(hasMe);
        result.addAll(hasMeNot);
        return result;
    }
	// 1-pass correct!
	public ArrayList<ArrayList<Integer>> combine_20130513(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (n<k || k<=0) return result;
        if (n==k) {
           ArrayList<Integer> all = new ArrayList<Integer>();
           for (int i=1; i<=n; i++) {
               all.add(i);
           }
           result.add(all);
           return result;
        }
        if (k==1) {
            for (int i=1; i<=n; i++) {
                ArrayList<Integer> pick1 = new ArrayList<Integer>();
                pick1.add(i);
                result.add(pick1);
            }
            return result;
        }
        ArrayList<ArrayList<Integer>> pickLast = combine(n-1, k-1);
        for (ArrayList<Integer> sub: pickLast) {
            sub.add(n);
        }
        ArrayList<ArrayList<Integer>> pickLastNot = combine(n-1, k);
        result.addAll(pickLast);
        result.addAll(pickLastNot);
        return result;
    }    
	
    public static List<Combi> recursiveCpq(int[] elements, int q) {
        return doCombi(elements, q, 0);
    }
    
    public static List<Combi> Cpq(int[] elements, int q) {
        ArrayList<Combi> result = new ArrayList<Combi>();
        
        return result;
    }

    static List<Combi> doCombi(int[] elements, int q, int currIndex) {
        if (q == 0 || currIndex == elements.length) {
            List<Combi> toReturn = new ArrayList<Combi>();
            toReturn.add(new Combi(new ArrayList<Integer>()));
            return toReturn;
        }
        if (elements.length - currIndex == q) {
            ArrayList<Integer> rest = new ArrayList<Integer>();
            for (int i = 0; i < q; i++) {
                rest.add(elements[currIndex + i]);
            }
            List<Combi> toReturn = new ArrayList<Combi>();
            toReturn.add(new Combi(rest));
            return toReturn;
        }
        return combine(
                appendElement(elements[currIndex],
                        doCombi(elements, q - 1, currIndex + 1)),
                doCombi(elements, q, currIndex + 1));

    }

    static List<Combi> appendElement(int element, List<Combi> comb) {        
        for (Combi com : comb) {
            com.getContent().add(element);            
        }
        return comb;
    }

    static List<Combi> combine(List<Combi> c1, List<Combi> c2) {
        c1.addAll(c2);
        return c1;
    }

    static class Combi {
        private List<Integer> content;

        public Combi(List<Integer> elements) {
            content = elements;
        }

        public List<Integer> getContent() {
            return content;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (int c : content) {
                sb.append(c);
                sb.append(',');
            }
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        int[] elements = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (Combi combi : Cpq(elements, 2)) {
            System.out.println(combi.toString());
        }
    }

}
