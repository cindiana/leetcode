package leetcod;

import java.util.Arrays;


/*
 * 1. failed [1,1] - initial currMax x width should be (height.length-1)
 * 2. failed [1,2,4,3] - incorrectly typed Math.max in close-in block.
 * 3. failed big test - need to handle ties. case [2,8,1,2].
 * 4. handled ties using recursion - passed all small test, but exceeded running time limit with unnecessary recursion
 * 5. the optimization to skip apparently short lines introduced bug: by skipping them, we fail to 
 * calculate closein from them, therefore lose a good potential line after them. 
 */
public class MaxWaterInContainer {
    public int maxArea(int[] height) {
        if (height==null || height.length<2) {
            return 0;
        }
        
        return doMax(height);
    }
    
    private int doMax(int[] height) {
        int l=0;
        int r=height.length-1;
        int currMax = (height.length-1)*Math.min(height[l], height[r]);       
        
        
        while(l<r && l<height.length-2 && r>0) {          
            
            // close in one line at a time
            if(height[l]>height[r]) { //save l, inspect r-1
                int closein = (r-l-1)*Math.min(height[l], height[r-1]);
                r--;                                        
                currMax = Math.max(currMax, closein);
                                
            }
            else if (height[l]<height[r]){// save r, inspect l+1
                int closein = (r-l-1)*Math.min(height[l+1], height[r]);
                l++;                                               
                currMax = Math.max(currMax, closein);                                
            }            
            else{
                int lessLeft = doMax(Arrays.copyOfRange(height, l+1, r+1));
                int lessRight = doMax(Arrays.copyOfRange(height, l, r));
                int winner = Math.max(lessLeft,lessRight);
                return Math.max(winner, currMax);
            }
        }
        
        return currMax;
    }
    
    public static void main(String[] args) {
        MaxWaterInContainer m = new MaxWaterInContainer();
        /* should be: 18048, mine: 17100;;;testdata2: 15072/15423
         * [76,155,15,188,180,154,84,34,187,142,22,5,27,183,111,128,50,58,2,112,179,2,100,111,115,76,134,120,118,103,31,146,58,198,134,38,104,170,25,92,112,199,49,140,135,160,20,185,171,23,98,150,177,198,61,92,26,147,164,144,51,196,42,109,194,177,100,99,99,125,143,12,76,192,152,11,152,124,197,123,147,95,73,124,45,86,168,24,34,133,120,85,81,163,146,75,92,198,126,191]
         */
        int[] testData1 = new int[]{76,155,15,188,180,154,84,34,187,142,22,5,27,183,111,128,50,58,2,112,179,2,100,111,115,76,134,120,118,103,31,146,58,198,134,38,104,170,25,92,112,199,49,140,135,160,20,185,171,23,98,150,177,198,61,92,26,147,164,144,51,196,42,109,194,177,100,99,99,125,143,12,76,192,152,11,152,124,197,123,147,95,73,124,45,86,168,24,34,133,120,85,81,163,146,75,92,198,126,191};
        //int[] testData2 = new int[] {159,157,139,51,98,71,4,125,48,125,64,4,105,79,136,169,113,13,95,88,190,5,148,17,152,20,196,141,35,42,188,147,199,127,198,49,150,154,175,199,80,191,3,137,22,92,58,87,57,153,175,199,110,75,16,62,96,12,3,83,55,144,30,6,23,28,56,174,183,183,173,15,126,128,104,148,172,163,35,181,68,162,181,179,37,197,193,85,10,197,169,17,141,199,175,164,180,183,90,115};
        for(int i=0; i<testData1.length; i++) {
            for (int j=0; j<testData1.length; j++) {
                int water = Math.abs(i-j) * Math.min(testData1[i], testData1[j]);
                
                if (water==17100) {
                    System.out.println("["+i + "]="+testData1[i] + "[" + j + "]="+testData1[j] +" - 17100");
                }
                else if (water==18048) {
                    System.out.println("["+i + "]="+testData1[i] + "[" + j + "]="+testData1[j] +" - 18048");
                }
            }
        }
        System.out.println(m.maxArea(testData1));
    }

}
