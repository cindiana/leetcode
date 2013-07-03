package leetcod;


/*
 * 1. "a", "ab" fail. Tweaked base case for all [0][x], [x][0], and recursive case.
 * 2. big test exceeds time limit - change base case init boundary from 0 to 1, then pass.
 */
public class EditDistance {
	public int minDistance(String word1, String word2) {
		if(word1==null||word1.length()==0) return word2.length();
		if (word2==null||word2.length()==0) return word1.length();
		
        int[][] matrix = new int[word1.length()+1][word2.length()+1];
        for(int i=1; i<word1.length()+1; i++) {
        	matrix[i][0] = i;
        }
        for(int j=1; j<word2.length()+1; j++) {
        	matrix[0][j] = j;
        }
        matrix[0][0] = 0;
        
        for(int i=1; i<word1.length()+1; i++) {
        	for (int j=1; j<word2.length()+1; j++) {
        		if (word1.charAt(i-1)==word2.charAt(j-1)){
        			matrix[i][j] = matrix[i-1][j-1];
        		}
        		else {
        			int temp = Math.min(matrix[i-1][j-1] + 1, matrix[i-1][j]+1);
        			matrix[i][j] = Math.min(temp, matrix[i][j-1]+1);
        		}
        	}
        }
        
        return matrix[word1.length()][word2.length()];
    }
	
	
}
