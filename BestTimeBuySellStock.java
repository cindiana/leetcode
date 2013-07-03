package leetcod;

public class BestTimeBuySellStock {
	 public int maxProfit(int[] prices) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if (prices==null || prices.length<2) return 0;
	        int[] profits = new int[prices.length];
	        int max = 0;
	        int min = prices[0];
	        for (int i=1; i<prices.length; i++) {
	            if (prices[i]>min) {
	                max = Math.max(prices[i]-min, max);
	                profits[i] = max;
	            }
	            else {
	                min = Math.min(prices[i], min);
	                profits[i] = max;
	            }
	        }
	        
	        int maxFromRight = prices[prices.length-1];
	        int result = 0;
	        for (int i=prices.length-1; i>=0;i--) {
	            if (prices[i]<=maxFromRight) {
	                result = Math.max(result, profits[i]+(maxFromRight-prices[i]));
	            }
	            else {
	                maxFromRight = Math.max(maxFromRight, prices[i]);
	            }
	        }
	        return result;
	    }
}
