package leetcod;

/*
 * 1. fail all. Notice that for a rotateLen, only rotateLen-1 rotates are needed.
 * 2. fail [[1,2,3],[4,5,6],[7,8,9]]. need to differentiate first cell with the rest.
 * 3. fail n=4. should not treat 4th node as mirror of 1st.
 * 4. fail n=5. tweak index. notice that topx needs to play for internal nodes.
 */
public class RotateImage {
	public void rotate_20130527(int[][] matrix) {
        if (matrix==null || matrix.length==0 || matrix[0].length==0) return;
        int layerCount = matrix.length/2;
        for (int i=0; i<layerCount; i++) {
            int steps = matrix.length-2*i-1;
            for (int j=0; j<steps; j++) {
                int topleft = matrix[i][i+j];
                matrix[i][i+j] = matrix[matrix.length-i-1-j][i];
                matrix[matrix.length-i-1-j][i] = matrix[matrix.length-i-1][matrix.length-i-1-j];
                matrix[matrix.length-i-1][matrix.length-i-1-j] = matrix[i+j][matrix.length-i-1];
                matrix[i+j][matrix.length-i-1] = topleft;
            }
        }
    }
	
	public void rotate(int[][] matrix) {
        int rotateLen = matrix.length;
        while(rotateLen>1) {
        	int topx = (matrix.length - rotateLen)/2;
        	int temp = matrix[topx][topx];
    		matrix[topx][topx] = matrix[matrix.length-1-topx][topx];
    		matrix[matrix.length-1-topx][topx] = matrix[matrix.length-1-topx][matrix.length-1-topx];
    		matrix[matrix.length-1-topx][matrix.length-1-topx] = matrix[topx][matrix.length-1-topx];
    		matrix[topx][matrix.length-1-topx] = temp;   
        	
        	
        	for (int i=1; i< rotateLen-1; i++) {    		
        		temp = matrix[topx+i][topx];
        		matrix[topx+i][topx] = matrix[matrix.length-1-topx][topx+i];
        		matrix[matrix.length-1-topx][topx+i] = matrix[matrix.length-1-i-topx][matrix.length-1-topx];
        		matrix[matrix.length-1-i-topx][matrix.length-1-topx] = matrix[topx][matrix.length-1-i-topx];
        		matrix[topx][matrix.length-1-i-topx] = temp;       		
        	}       	
        	rotateLen -=2;
        }
        
    }
}
