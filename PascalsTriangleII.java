package leetcod;

public class PascalsTriangleII {
	public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numRows<=0) return result;
        ArrayList<Integer> row1 = new ArrayList<Integer>();
        row1.add(1);
        result.add(row1);
        
        for (int i=0; i<numRows-1; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            ArrayList<Integer> last = result.get(result.size()-1);
            row.add(last.get(0));
            for (int j=1; j<last.size(); j++) {
                row.add(last.get(j) + last.get(j-1));
            }
            row.add(last.get(last.size()-1));
            result.add(row);
        }
        return result;
    }
}
