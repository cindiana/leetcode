package leetcod;
/*
 * O(n)
 * Starting from the very left and very right, close in.
 * While closing in, ignore lefts shorter than visited left, rights shorter than visited,
 * compute water only when longer ones show up. Keep updating max. 
 */
public class ContainerWithMostWater {
	public int maxArea_20130505(int[] height) {
        if (height==null || height.length<2) return 0;
        
        int left = 0;
        int right = height.length-1;
        int max = 0;
        
        while(left<right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right-left));
            if (height[left]<height[right]) {
                int current = height[left];
                while(left<height.length && height[left]<=current) {
                    left++;
                }
            }
            else {
                int current = height[right];
                while(right>0 && height[right]<=current) {
                    right--;
                }
            }
        }
        return max;
    }
}
