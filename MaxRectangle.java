package leetcod;

import java.util.Stack;

public class MaxRectangle {
	public int maximalRectangle_20130505(char[][] matrix) {
        if (matrix==null || matrix.length==0 || matrix[0].length==0) return 0;
        
        int[][] vertical = new int[matrix.length][matrix[0].length];       
        for (int i=matrix.length-1; i>=0; i--) {
            for (int j=0; j<matrix[0].length; j++) {
                vertical[i][j] = matrix[i][j]=='0'? 0: 1+ (i==matrix.length-1?0:vertical[i+1][j]);
            }
        }
      
        int max = 0;
        int thisStart = -1;
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (vertical[i][j]>0) {
                    if (thisStart==-1) {
                        thisStart = j;
                    }
                    if (j==matrix[0].length-1) {
                        int thisMax = getMax_histogram(vertical[i], thisStart, j);
                        if (thisMax > max) {
                            max = thisMax;
                        }
                        thisStart = -1;
                    }
                }
                else {
                    if (thisStart>-1) {
                        int thisMax = getMax_histogram(vertical[i], thisStart, j-1);
                        if (thisMax > max) {
                            max = thisMax;
                        }
                        thisStart = -1;
                    }
                }
            }           
        }
        return max;
    }
	
	public int getMax_histogram(int[] row, int start, int end) {
        int max = 0;
        Stack<Tuple> tuples = new Stack<Tuple>();
        for (int i=start; i<=end; i++) {
            if (tuples.isEmpty() || row[i]>=tuples.peek().v) {
                tuples.push(new Tuple(i, row[i]));
            }
            else if (row[i]<tuples.peek().v) {
                int popCount = 0;
                while (!tuples.isEmpty() && tuples.peek().v>row[i]) {
                    Tuple tup = tuples.pop();
                    popCount++;
                    max = Math.max(max, tup.v*popCount);
                }
                int base = tuples.size();
                for (int j=0; j<=popCount; j++)  {
                    tuples.push(new Tuple(base+j, row[i]));
                }
            }
        }
        int popCount = 0;
        while(!tuples.isEmpty()) {
            Tuple t = tuples.pop();
            popCount++;
            max = Math.max(max, t.v *popCount);
        }
        return max;
    }
	
	public class Tuple {
        int i;
        int v;
        public Tuple(int x, int y) {
            i = x;
            v = y;
        }
    }
    
    public int getMax(int[] row, int start, int end) {
        int max = row[start];
        int min = row[start];
        int runStart = start;
        if (start==end) return max;
        
        for (int i=start+1; i<=end; i++) {
            if (row[i]<= min ) {
                min = row[i];
            }
            max = Math.max(max, min*(i-runStart+1)); 
            if (row[i] > min){
                int runningMin = row[i];
                for (int j=i; j>=start; j--) {
                    if (row[j]==min) break;
                    if (row[j]<runningMin) {
                        runningMin = row[j];                       
                    }
                    max = Math.max(max, runningMin*(i-j+1));
                }
            }
        }
        return max;
    }
    
    public int getMax_N2(int[] row, int start, int end) {
        
        int max = 0;
        int maxWindowSize = end-start+1;
        for (int i=1; i<=maxWindowSize; i++) {
            int windowStart = start;
            while (windowStart+i-1<=end) {
                int min = Integer.MAX_VALUE;
                for (int j=0; j<i; j++) {
                    min = Math.min(min, row[windowStart + j]);
                }
                max = Math.max(max, i*min);
                windowStart++;
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
    	MaxRectangle mr = new MaxRectangle();
    	char[][] matrix = new char[][]{
    			"10101110".toCharArray(),"11011000".toCharArray(),
    			"11100101".toCharArray(),"10111110".toCharArray(),
    			"00011110".toCharArray()
    	};
    	System.out.println(mr.maximalRectangle_20130505(matrix));
    }
    
	public int maximalRectangle(char[][] matrix) {
        if (matrix==null||matrix.length==0||matrix[0].length==0) {
        	return 0;
        }
        
        int maxSize = 0;
        
        int width = matrix[0].length;
        int height = matrix.length;
        int[][] countMatrix = new int[width][height];
        for(int i=0; i<width; i++) {
        	for (int j=height-1;j>=0; j--) {
        		if (matrix[i][j]=='1') {
        			if (j==0) {
        				countMatrix[i][j]=1;
        			}
        			else {
        				countMatrix[i][j] = countMatrix[i][j-1]+1;
        			}
        		}
        	}       	
        }
        for(int j=0; j<height; j++) {
        	int thisStart = -1;
        	int step = 1;
        	while(countMatrix[++thisStart][j]<=0 && thisStart<width-1);
        	if (thisStart==width-1) {
        		//not a single cell in this row has positive count
        		continue;
        	}
        	while(thisStart+step<width && countMatrix[thisStart+step][j]>0) {
        		step++;
        	}
        	int thisEnd = thisStart+step-1;
        	
        }
        return maxSize;
    }
}
