package leetcod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * - ThreeSum - Forgot to use sorted list. Upper range of for loop off by 1,
 * causing calculation for array size of 3 to be skipped. Can't handle
 * {0,0,0,0}, producing duplicates. When fixing handing dups, introduced
 * infinite while loop by using continue w/o changing terminating condition.
 * After finding a match, should not break. After finding a dup, should not
 * break. When fixing the break, forgot to change while loop condition.
 * Algorithm flaw: dup does not always come continuously, because we have dups
 * for a[i] too. Need to use hashtable
 * 
 * - ThreeSumCloest - Neglected requirement and coded wrong signature. After
 * fixing signature, failed to modify all paths for return to return compatible
 * type. When delta==0, return target, not 0. Don't get prefixed with
 * conditioning!
 * 
 * @author cindy
 * 
 */
public class ThreeSum {
	public ArrayList<ArrayList<Integer>> threeSum_20130623(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num==null || num.length<3) return result;
        Arrays.sort(num);
        int i=0;
        while( i<num.length-2) {
            if (num[i]>0) break;
            int l = i+1;
            if (num[i]+num[l]>0) break;
            int r = num.length-1;
            while(l<r) {
                int sum = num[i]+num[l]+num[r];
                if (sum==0) {
                    ArrayList<Integer> res = new ArrayList<Integer>();
                    res.add(num[i]);
                    res.add(num[l]);
                    res.add(num[r]);
                    result.add(res);
                    while(r>l&&num[r]==num[r-1]) r--; r--;
                    while(l<r && num[l]==num[l+1]) l++; l++;
                }
                else if (sum>0) {
                    while(r>l&&num[r]==num[r-1]) r--; r--;
                }
                else {
                    while(l<r && num[l]==num[l+1]) l++; l++;
                }
            }
            while(i+1<num.length-2 && num[i]==num[i+1]) i++; i++;
        }
        return result;
    }
	/*
	 * sort, then pivot from 0 to length-3, for items to the right, do 2sum close-in
	 */
	public static ArrayList<ArrayList<Integer>> threeSum_20130518(int[] num) {
		ArrayList<ArrayList<Integer>> returnResult = new ArrayList<ArrayList<Integer>>();
        if (num==null || num.length<3) return returnResult;
        
        HashSet<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>();
        Arrays.sort(num);
        for (int i=0; i<num.length; i++) {
            int me = num[i];
            int left = i+1;
            int right = num.length-1;
            while(left<right) {
                if (num[left]+num[right] == 0-me) {
                    ArrayList<Integer> r = new ArrayList<Integer>();
                    r.add(me);
                    r.add(num[left]);
                    r.add(num[right]);
                    result.add(r);
                    while(left+1<right && num[left]==num[left+1]) left++;
                    while(right-1>left && num[right]==num[right-1]) right--;
                    left++;
                    right--;
                }
                else if (num[left]+num[right]>0-me) {
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        
        return new ArrayList(result);
    }
	
	public static ArrayList<ArrayList<Integer>> ThreeSum(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		// 1. sort a
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num.length; i++) {
			list.add(num[i]);
		}
		java.util.Collections.sort(list);

		// 2. find sum
		Hashtable<Integer, Hashtable<Integer, Integer>> dup = new Hashtable<Integer, Hashtable<Integer, Integer>>();
		for (int i = 0; i < num.length - 2; i++) {
			int i2 = i + 1;
			int i3 = num.length - 1;

			while (i2 < i3) {
				if (list.get(i) + list.get(i2) + list.get(i3) == 0) {
					if (dup.containsKey(list.get(i))) {
						Hashtable<Integer, Integer> twoSum = dup.get(list
								.get(i));
						if (twoSum.containsKey(list.get(i2))) {
							i2++;
							i3--;
							continue;
						}
					}
					ArrayList<Integer> r = new ArrayList<Integer>();
					r.add(list.get(i));
					r.add(list.get(i2));
					r.add(list.get(i3));
					result.add(r);

					Hashtable<Integer, Integer> twoSum;
					if (dup.containsKey(list.get(i))) {
						twoSum = dup.get(list.get(i));
						twoSum.put(list.get(i2), list.get(i3));
					} else {
						twoSum = new Hashtable<Integer, Integer>();
						twoSum.put(list.get(i2), list.get(i3));
						dup.put(list.get(i), twoSum);
					}

					i2++;
					i3--;
				} else if (list.get(i) + list.get(i2) + list.get(i3) < 0) {
					i2++;
				} else {
					i3--;
				}
			}
		}

		return result;
	}

    public int threeSumClosest_20130525(int[] num, int target) {
        if (num==null || num.length<3) return 0;
        Arrays.sort(num);
        int minDiff = Integer.MAX_VALUE;
        int closest = 0;
        
        for (int i=0; i<num.length; i++) {
            int p1 = i+1;
            int p2 = num.length-1;
            int diff = 0;
            boolean sameSign = true;
            
            while(p1<p2) {
                int sum = num[i]+num[p1]+num[p2];
               
                diff = sum - target;
                if (diff==0) return sum;
                if (Math.abs(diff) < minDiff) {
                    closest = sum;
                    minDiff = Math.abs(diff);
                }
                
                if (diff>0) {
                    p2--;
                }
                else {
                    p1++;
                }
            }
        }
        
        return closest;
        
    }

	public static int ThreeSumCloest(int[] num, int target) {
		if (num.length < 3)
			return Integer.MIN_VALUE;

		// 1. sort
		List<Integer> sorted = new ArrayList<Integer>();
		for (int i = 0; i < num.length; i++) {
			sorted.add(num[i]);
		}
		Collections.sort(sorted);

		// 2. for each sorted[i], get closest.
		int minDelta = Integer.MAX_VALUE;
		int x = sorted.get(0), y = sorted.get(1), z = sorted.get(2);

		for (int i = 0; i < sorted.size() - 2; i++) {
			int i2 = i + 1;
			int i3 = sorted.size() - 1;
			while (i2 < i3) {
				int sum = sorted.get(i) + sorted.get(i2) + sorted.get(i3);

				if (sum == target) {
					return target;
				} else {
					if (Math.abs(target - sum) < minDelta) {
						x = sorted.get(i);
						y = sorted.get(i2);
						z = sorted.get(i3);
						minDelta = Math.abs(target - sum);
					}

					if (sum > target) {
						i3--;
					} else {
						i2++;
					}
				}
			}
		}

		return x + y + z;
	}

	public static void main(String[] args) {
		int[] a = new int[] { -4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6 };// [[-4,-2,6],[-4,0,4],[-4,1,3],[-4,2,2],[-2,-2,4],[-2,0,2]]
		ArrayList<ArrayList<Integer>> s = threeSum_20130518(a);
		for (ArrayList<Integer> sum : s) {
			for (Integer i : sum) {
				System.out.print(i + ",");
			}
			System.out.println();
		}
	}
}
