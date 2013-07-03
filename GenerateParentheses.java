package leetcod;

import java.util.ArrayList;


/* 
 * 1. misunderstanding of ArrayList.set(i, obj) caused runtime error.
 * 2. for n=3, missed (()()). Algorithm flaw. The "embrace-append" algorithm is wrong
 * 3. use new recursive algorithm, forgot to bound l and r by n. Caused stackOverFlow
 */
public class GenerateParentheses {
	
	public ArrayList<String> generateParenthesis_20130504(int n) {        
        ArrayList<String> result = new ArrayList<String>();
        if (n<=0) return result;
        doGen(result, n, 0, 0, "");
        return result;
    }
    
    public void doGen(ArrayList<String> result, int n, int left, int right, String current) {
        if (left==n && right==n) {
            result.add(current);
            return;
        }
        if (left<n) {
            doGen(result, n, left+1, right, current+"(");
        }
        if (right<left) {
            doGen(result, n, left, right+1, current+")");
        }
    }
    
	public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> result = new ArrayList<String>();
		if (n <= 0)
			return result;

		return doGenerate(n, 0, 0, "");

	}

	private ArrayList<String> doGenerate(int n, int lUsed, int rUsed, String sofar) {
		if (lUsed==n && rUsed==n) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(sofar);
			return temp;
		}
		ArrayList<String> temp = new ArrayList<String>();
		if (lUsed<n) {
			ArrayList<String> useLeft = doGenerate(n, lUsed+1, rUsed, sofar+'(');
			temp.addAll(useLeft);
		}
		if (lUsed>rUsed) {
			ArrayList<String> useRight = doGenerate(n, lUsed, rUsed+1, sofar+')');
			temp.addAll(useRight);
		}
		return temp;
	}
    

	public static void main(String[] args) {
		GenerateParentheses gen = new GenerateParentheses();
		ArrayList<String> output = gen.generateParenthesis(2);
		for (String s : output) {
			System.out.println(s);
		}
		
	}
}
