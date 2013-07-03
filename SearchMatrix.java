package leetcod;

/*
 * 1. exceed time limit. need to update mid for each while loop
 * 2. exceed time limit. l<r-1 to avoid infinite l=mid, when l==r-1
 * 3. fail [[1]] 1, add special handling for finding target row when matrix height is 1 or 2.
 * 4. fail [[1]] 1, add special handling for finding target in row when row length is 1 or 2.
 * 5. fail [[1],[3]] 3. = case for special handling for target row.
 * 6. fail [[1,3,5]] 1. l<r-1 introduces trouble.
 * --------read article and rewrote algorithm--------------
 * 1. fail [[1,3]] 1. swap x and y. run thru simplest example to verify code.
 */
public class SearchMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0)
			return false;
		if (matrix[0][0] > target
				|| matrix[matrix.length - 1][matrix[matrix.length - 1].length - 1] < target)
			return false;

		int x = 0;
		int y = matrix[0].length - 1;

		while (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
			if (matrix[x][y] == target) {
				return true;
			}
			if (matrix[x][y] < target) {
				x++;
			}
			else {
				y--;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		SearchMatrix sm = new SearchMatrix();
		sm.searchMatrix(new int[][] { { 1 }, { 3 } }, 3);
	}
}
