package leetcod;

import java.util.ArrayList;

public class SpiralMatrix {
	// SpirialMatrix II 20130527
	public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if (n==0) return result;
        if (n==1) {result[0][0] = 1; return result;}
        
        int layerCount = n/2;
        int runner = 1;
        for (int i=0; i<layerCount; i++) {
            int steps = n-i*2-1;
            for (int j=0; j<steps; j++) {
                result[i][i+j] = runner;
                runner++;
            }
            for (int j=0; j<steps; j++) {
                result[i+j][n-1-i] = runner;
                runner++;
            }
            for (int j=0; j<steps; j++) {
                result[n-i-1][n-i-1-j] = runner;
                runner++;
            }
            for (int j=0; j<steps; j++) {
                result[n-i-1-j][i] = runner;
                runner++;
            }
        }
        
        
        if (n%2==1) result[n/2][n/2] = runner;
        return result;
    }
	
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix==null || matrix.length==0 || matrix[0].length==0) return result;
        

        int ceil1 = matrix.length%2==0? matrix.length/2 : matrix.length/2 + 1;
        int ceil2 = matrix[0].length%2==0? matrix[0].length/2 : matrix[0].length/2 +1 ;
        int numLayers = Math.min(ceil1, ceil2);
        
        int layer =0;
        while(layer<numLayers) {
            int width = matrix[0].length - 2*layer;
            int height = matrix.length - 2*layer;
            
            
            if (height==1) {
                for (int i=0; i<width; i++) {
                    result.add(matrix[layer][layer+i]);
                }
                return result;
            }
        
            if (width==1) {
                for (int i=0; i<height; i++) {
                    result.add(matrix[i+layer][layer]);
                }
                return result;
            }
            
            for (int i=0; i<width-1; i++) {
                result.add(matrix[layer][i+layer]);
            }
            for (int i=0; i<height; i++) {
                result.add(matrix[i+layer][matrix[0].length-1-layer]);
            }
            for (int i=0; i<width-2; i++) {
                result.add(matrix[matrix.length-1-layer][matrix[0].length-1-layer-i-1]);
            }
            for (int i=0; i<height-1; i++) {
                result.add(matrix[matrix.length-1-layer-i][layer]);
            }
            
            
            layer++;
        }
        return result;
    }
}
