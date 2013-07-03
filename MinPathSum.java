package leetcod;

/*
 * 1. runtime err. 2D array[i][j]. It's a size i int[j] 
 */
public class MinPathSum {
	public int minPathSum_20130525(int[][] grid) {
        int[][] m = new int[grid.length][grid[0].length];
        m[0][0] = grid[0][0];
        for (int i=1; i<grid.length; i++) {
            m[i][0] = grid[i][0] + m[i-1][0];
        }
        for (int i=1; i<grid[0].length; i++) {
            m[0][i] = grid[0][i] + m[0][i-1];
        }
        
        for (int i=1; i<grid.length; i++) {
            for (int j=1; j<grid[0].length; j++) {
                m[i][j] = grid[i][j]+Math.min(m[i-1][j], m[i][j-1]);
            }
        }
        
        return m[grid.length-1][grid[0].length-1];
    }
	
	public int minPathSum(int[][] grid) {
		if (grid==null || grid.length==0 || grid[0].length==0) return 0;
        return nonRecursive(grid);
        
    }
	
	private int nonRecursive(int[][] grid) {
		int[][] counter = new int[grid.length][grid[0].length];
		
		counter[0][0] = grid[0][0];
		for(int i=1; i<grid[0].length; i++) {
			counter[0][i] = grid[0][i]+counter[0][i-1];
		}
		for (int i=1; i<grid.length; i++) {
			counter[i][0] = grid[i][0] + counter[i-1][0];
		}
		
		for (int i=1; i<grid.length; i++) {
			for (int j=1; j<grid[0].length; j++) {
				counter[i][j] = Math.min(counter[i-1][j], counter[i][j-1]) + grid[i][j];
			}
		}
		return counter[grid.length-1][grid[0].length-1];
	}
}
