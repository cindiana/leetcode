package leetcod;

public class UniquePaths {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid==null || obstacleGrid.length==0 || obstacleGrid[0].length==0) return 0;
        
        int[][] m = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i=0; i<obstacleGrid.length; i++) {
            if (obstacleGrid[i][0]==1) break;
            m[i][0] = 1;
        }
        for (int i=0; i<obstacleGrid[0].length; i++) {
            if (obstacleGrid[0][i]==1) break;
            m[0][i] = 1;
        }
        for (int i=1; i<obstacleGrid.length; i++) {
            for (int j=1; j<obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j]==0) {
                    m[i][j] = m[i-1][j] + m[i][j-1];
                }
            }
        }
        return m[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
	
	public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i=0; i<m; i++) matrix[i][0] = 1;
        for (int i=0; i<n; i++) matrix[0][i] = 1;
        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
            }
        }
        return matrix[m-1][n-1];
    }
}
