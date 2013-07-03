package leetcod;

import java.util.ArrayList;

/*
 * 1. runtime error: algorithm incomplete, and badly assumed that intervals has elements.
 * 2. overlooked requirement: [1,2][3,4] ok. do not need to merge
 * 3. re-org methods. Can't be vague in algorithm design phase.
 * 4. failed {[1,5]}[5,7], bug in determined last involved from left, should not include equal case.
 * 5. Still failed {[1,5]}[5,7], bug in out-of-range case handling with extra equal case. 
 */
public class InsertInterval {
	public ArrayList<Interval> insert_20130516(ArrayList<Interval> intervals, Interval newInterval) {
        if (newInterval==null || newInterval.start>newInterval.end) return intervals;
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals==null || intervals.size()==0) {            
            if (newInterval!=null) {
                result.add(newInterval);
            }
            return result;
        }
        if (newInterval.end<intervals.get(0).start) {
            intervals.add(0, newInterval);
            return intervals;
        }
        if (newInterval.start>intervals.get(intervals.size()-1).end) {
            intervals.add(newInterval);
            return intervals;
        }
        
        boolean newHandled = false;
        if (newInterval.start<intervals.get(0).start) {
            result.add(newInterval);
            newHandled = true;
        }
        for (int i=0; i<intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (result.size()==0 || !intersect(result.get(result.size()-1), current)) {
                result.add(current);
            }
            else {
                Interval last = result.get(result.size()-1);
                result.remove(result.size()-1);
                result.add(merge(current, last));
            }
            if (!newHandled && (current.start<=newInterval.start && current.end>=newInterval.start ||
                i<intervals.size()-1 && current.end<newInterval.start && intervals.get(i+1).start>newInterval.start)) {
                Interval last = result.get(result.size()-1);
                if (intersect(last, newInterval)) {
                    result.remove(result.size()-1);
                    result.add(merge(newInterval, last));
                }
                else {
                    result.add(newInterval);
                }
                newHandled = true;
            }
        }
        return result;
    }
    
    public boolean intersect(Interval i1, Interval i2) {
        if (i1.start<i2.start && i1.end<i2.start) return false;
        if (i2.start<i1.start && i2.end<i1.start) return false;
        return true;
    }
    
    public Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }
	public ArrayList<Interval> insert(ArrayList<Interval> intervals,
			Interval newInterval) {
		ArrayList<Interval> result = new ArrayList<Interval>();
		if (intervals == null || newInterval == null) {
			return result;
		}
		if (intervals.size()==0) {
			result.add(newInterval);
			return result;
		}
		if (newInterval.start > intervals.get(intervals.size() - 1).end) {
			intervals.add(newInterval);
			return intervals;
		}
		if (newInterval.end < intervals.get(0).start) {
			intervals.add(0, newInterval);
			return intervals;
		}

		int n = findLastLeftNotInvolved(intervals, newInterval.start);
		int p = findFirstRightNotInvolved(newInterval.end, n,
				intervals);		
			
		if (n >= 0) {
			result.addAll(intervals.subList(0, n+1));
		}
		result.add(new Interval(Math.min(newInterval.start, intervals.get(n+1).start), Math.max(newInterval.end, intervals.get(p-1).end)));
		result.addAll(intervals.subList(p, intervals.size()));

		return result;

	}

	private int findFirstRightNotInvolved(int n, int startIndex,
			ArrayList<Interval> intervals) {
		int p = startIndex>0? startIndex: 0;
		while (p < intervals.size()) {
			if (intervals.get(p).start > n) {
				return p;
			}
			p++;
		}
		return p;
	}

	private int findLastLeftNotInvolved(ArrayList<Interval> intervals, int start) {
		int i = intervals.size()-1;
		while (i>=0) {
			if (start > intervals.get(i).end) {
				return i;
			}
			i--;
		}
		return -1;
	}

	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public static void main(String[] args) {
		InsertInterval ii = new InsertInterval();
		ArrayList<Interval> input = new ArrayList<Interval>();
		input.add(ii.new Interval(1, 5));
		/*input.add(ii.new Interval(3, 5));
		input.add(ii.new Interval(6, 7));
		input.add(ii.new Interval(8, 10));
		input.add(ii.new Interval(14, 16));*/
		ArrayList<Interval> output = ii.insert(input, ii.new Interval(5, 7));

		for (Interval i : output) {
			System.out.print("[" + i.start + "," + i.end + "]");
		}
	}

}
