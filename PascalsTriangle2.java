package leetcod;

import java.util.ArrayList;

public class PascalsTriangle2 {
	public ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (rowIndex<=0) return result;
        result.add(1);
        if (rowIndex==1) return result;
        
        for (int i=1; i<rowIndex; i++) {
            ArrayList<Integer> currentRow = new ArrayList<Integer>();
            currentRow.add(1);
            for (int j=0; j<result.size()-1; j++) {
                currentRow.add(result.get(j) + result.get(j+1));
            }
            currentRow.add(1);
            result = currentRow;
        }
        
        return result;
    }
}
