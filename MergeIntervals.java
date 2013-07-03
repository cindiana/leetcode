package leetcod;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 1. runtime err. ClassCastException from MyInterval to Interval, because Interval speciried parameterless ctor.
 */
public class MergeIntervals {
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		ArrayList<Interval> result = new ArrayList<Interval>();
		if (intervals == null || intervals.size() == 0)
			return result;
		if (intervals.size() == 1)
			return intervals;

		ArrayList<MyInterval> temp = new ArrayList<MyInterval>();
		for (Interval in : intervals) {
			temp.add(new MyInterval(in));
		}
		Collections.sort(temp);

		int i = 0;
		while (i < temp.size() - 1) {
			// if i and i+1 overlap, merge to i+1, make i null
			if (!(temp.get(i).end < temp.get(i + 1).start)) {
				temp.get(i + 1).start = temp.get(i).start;
				temp.get(i + 1).end = Math.max(temp.get(i).end,
						temp.get(i + 1).end);
				temp.set(i, null);
			}
			i++;
		}

		for (MyInterval in : temp) {
			if (in != null) {
				result.add(new Interval( in.start, in.end));
			}
		}

		return result;
	}

	public class MyInterval extends Interval implements Comparable<MyInterval> {
		int start;
		int end;
		
		MyInterval(Interval in) {
			start = in.start;
			end = in.end;
		}

		@Override
		public int compareTo(MyInterval obj) {
			if (start > obj.start) {
				return 1;
			} else if (start < obj.start) {
				return -1;
			}
			if (end == obj.end) {
				return 0;
			} else if (end < obj.end) {
				return -1;
			}
			return 1;
		}
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
		
		@Override 
		public String toString() {
			return "[" + start + "," + end + "]";
		}
	}
	
	public static void main(String[] args) {
		MergeIntervals mi = new MergeIntervals();
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add( mi.new Interval(1,4));
		intervals.add( mi.new Interval(1,4));
		ArrayList<Interval> merged = mi.merge(intervals);
		
		for (Interval in: merged) {
			System.out.println(in);
		}
	}
}
