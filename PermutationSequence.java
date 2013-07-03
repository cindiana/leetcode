package leetcod;

import java.util.ArrayList;
import java.util.List;

/*
 * 1. fail 3,3. Bug in boundary condition in getFirstRank.
 */
public class PermutationSequence {
	
	public String getPermutation_20130527(int n, int k) {
        int[] facts = new int[n];//0~n-1
        facts[0] = 1;
        for (int i=1; i<n; i++) {
            facts[i] = i*facts[i-1];
        }
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=n; i++) list.add(i);//list has 1-n
        
        int actualK = k-1;
        for(int i=n-1; i>=1; i--) {
            int slot = actualK/facts[i];
            sb.append(list.get(slot));
            list.remove(slot);
            actualK-=slot*facts[i];
        }
        sb.append(list.get(0));
        return sb.toString();
	}	
	
	public String getPermutation(int n, int k) {
        if (n<=0 || k<=0) {
        	return null;
        }
        List<Integer> ints = new ArrayList<Integer>();
        for (int i=0; i<n; i++) {
        	ints.add(i+1);
        }
        return doGetPerm(ints,k);
    }
	
	private String doGetPerm(List<Integer> ints, int k) {
		int firstRank = getFirstRank(ints.size(), k);
		if (firstRank==-1) {
			return "";
		}
		
		if (ints.size()==1) {
			if (k==1) {
				return ints.get(0).toString();
			}
			else {
				return "";
			}
		}
		
		String prefix = ints.get(firstRank-1).toString();
		ints.remove(firstRank-1);
		return prefix + doGetPerm(ints, k-(firstRank-1)*getPound(ints.size()));
	}
	
	private int getPound(int n) {
		int r = 1;
		while(n>1) {
			r*=n;
			n--;
		}
		return r;
	}
	
	private int getFirstRank(int n, int k) {
		int rank = 1;
		int permNum = 1;
		while(rank<=n) {
			permNum *= rank;
			if (k<=permNum) {
				if (rank<n) return 1;
				else 
				{
					return k/(permNum/rank) + ((k%(permNum/rank))==0? 0:1);
				}
			}			
			rank++;
		}
		return -1;
	}
	public static void main(String[] args) {
		PermutationSequence ps = new PermutationSequence();
		ps.getPermutation_20130527(3, 2);
	}
}
