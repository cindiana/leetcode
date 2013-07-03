package leetcod;

import java.util.Stack;

/* 
 * 1. failed [1,1]. Failed to consider equal case.
 * 2. still fail [1,1]. Need to let rightShorterThanMeIndex return values at height.length. Just like left could return -1
 * 3. fail [2,1,2]. Same as #2, need to let leftShortThanMeIndex return -1 resulting from while loop
 * 4. Brutalforce exceeds time limit.
 */
public class LargestRectangular {
	public int largestRectangleArea_20130508(int[] height) {
        if (height==null || height.length==0) return 0;
        int max = 0;
        Stack<Integer> s = new Stack<Integer>();
        for (int i=0; i<height.length; i++) {
            if (s.isEmpty()||s.peek()<=height[i]) {
                s.push(height[i]);
            }
            else {
                int popCount = 0;
                while(!s.isEmpty() && s.peek()>height[i]) {
                    int val = s.pop();
                    popCount++;
                    max = Math.max(popCount*val, max);
                }
                for (int j=0; j<popCount+1; j++) {
                    s.push(height[i]);
                }
            }
        }
        
        int popCount = 0;
        while(!s.isEmpty()) {
            int val = s.pop();
            popCount++;
            max = Math.max(popCount*val, max);
        }
        return max;
    }
	
	public int largestRectangleArea(int[] height) {
        if (height==null || height.length==0) 
        {return 0;}
        
        return smart(height);
    }
	
	private int smart(int[] height) {
		int[] area= new int[height.length]; //initialize it to 0  
		int n, i, t;  
		Stack St = new Stack();  //include stack for using this #include<stack>  
		boolean done;  
		  
		for (i=0; i<height.length; i++)  
		{  
		while (!St.empty())  
		{  
		   if(height[i] <= height[Integer.valueOf(St.peek().toString())])  
		   {  
		       St.pop();  
		   }  
		   else  
		       break;  
		}  
		if(St.empty())  
		   t = -1;  
		else  
		   t = Integer.valueOf(St.peek().toString());  
		//Calculating Li  
		area[i] = i - t - 1;  
		St.push(i);  
		}  
		  
		//clearing stack for finding Ri  
		while (!St.empty())  
		St.pop();  
		  
		for (i=height.length-1; i>=0; i--)  
		{  
		while (!St.empty())  
		{  
		   if(height[i] <= height[Integer.valueOf(St.peek().toString())])  
		   {  
		       St.pop();  
		   }  
		   else  
		       break;  
		}  
		if(St.empty())  
		   t = height.length;  
		else  
		   t = Integer.valueOf(St.peek().toString());  
		//calculating Ri, after this step area[i] = Li + Ri  
		area[i] += t - i -1;  
		St.push(i);  
		}  
		  
		int max = 0;  
		//Calculating Area[i] and find max Area  
		for (i=0; i<height.length; i++)  
		{  
		area[i] = height[i] * (area[i] + 1);  
		if (area[i] > max)  
		   max = area[i];  
		}  
		  
		return max;  
	}
	
	private int brutalForce(int[] height) {
		int max = 0;
		for (int i=0; i<height.length; i++) {
			int thisMax = height[i] * (rightShorterThanMeIndex(height, i) - leftShorterThanMeIndex(height, i) -1 );
			if (thisMax>max) {
				max = thisMax;
			}
		}
		return max;
	}
	
	private int rightShorterThanMeIndex(int[] height, int meIndex) {
		int i = meIndex+1;
		while(i<height.length && height[i]>=height[meIndex]) {
			i++;
		}
		return i;
	}
	private int leftShorterThanMeIndex(int[] height, int meIndex) {
		int i = meIndex-1;
		while(i>=0 && height[i]>=height[meIndex]) {
			i--;
		}
		return i;
	}
	
	public static void main(String[] args) {
		int[] input = new int[] {5,4,1,2};
		LargestRectangular lr = new LargestRectangular();
		System.out.println(lr.largestRectangleArea_20130508(input));
	}
}
