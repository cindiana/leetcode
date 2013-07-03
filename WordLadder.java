package leetcod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class WordLadder {
	public List<ArrayList<String>> constructPath(int level, String word,
    		HashMap<Integer, HashMap<String, HashSet<String>>> backtrack) {
	    	List<ArrayList<String>> r = new ArrayList<ArrayList<String>>();
		ArrayList<String> start = new ArrayList<String>();
		start.add(word);
		r.add(start);
		HashMap<String, HashSet<String>> track;
		List<ArrayList<String>> newR;
		for (int i = level - 1; i >= 0; i--) {
			track = backtrack.get(i);
			newR = new ArrayList<ArrayList<String>>();
			for (ArrayList<String> tail : r) {
				for (String s : track.get(tail.get(0))) {
					ArrayList<String> newTail = (ArrayList<String>) tail
							.clone();
					newTail.add(0, s);
					newR.add(newTail);
				}
			}
			r = newR;
		}
		return r;
	}

	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		Queue<String> candidates = new LinkedList<String>();
		String dummy = "";
		candidates.add(start);
		candidates.add(dummy);

		int level = 0;
		HashMap<Integer, HashMap<String, HashSet<String>>> backtrack = new HashMap<Integer, HashMap<String, HashSet<String>>>();
		HashSet<String> thisround = new HashSet<String>();
        while (!candidates.isEmpty()) {
			String word = candidates.poll();
			if (word.equals(dummy)) {
				level++;
                thisround = new HashSet<String>();
				continue;
			}
			if (word.equals(end)) {
				result.addAll(constructPath(level, word, backtrack));
				break;
			}
            
    		for (int i = 0; i < word.length(); i++) {
				for (char j = 'a'; j <= 'z'; j++) {
					if (j != word.charAt(i)) {
						StringBuilder sb = new StringBuilder(word);
						sb.setCharAt(i, j);
						String s = sb.toString();
						if (dict.contains(s)) {
							HashMap<String, HashSet<String>> track;
							if (backtrack.containsKey(level)) {
								track = backtrack.get(level);
							} else {
								track = new HashMap<String, HashSet<String>>();
								backtrack.put(level, track);
							}
							if (track.containsKey(s)) {
								track.get(s).add(word);
							} else {
								HashSet<String> list = new HashSet<String>();
								list.add(word);
								track.put(s, list);
							}
							candidates.add(s);	
							thisround.add(s);
						}
					}
				}
			}
			
			if (candidates.peek().equals(dummy)) {
				candidates.add(dummy);
                for (String s: thisround) {
    		    	dict.remove(s);
			    }
			}
		}
		return result;
	}

	public int ladderLength(String start, String end, HashSet<String> dict) {
		Queue<String> candidates = new LinkedList<String>();
		String dummy = "";
		candidates.add(start);
		candidates.add(dummy);

		int level = 1;
		while (!candidates.isEmpty()) {
			String word = candidates.poll();
			if (word.equals(dummy)) {
				level++;
				continue;
			}
			if (word.equals(end)) {
				return level;
			}
			for (int i = 0; i < word.length(); i++) {
				for (char j = 'a'; j <= 'z'; j++) {
					if (j != word.charAt(i)) {
						StringBuilder sb = new StringBuilder(word);
						sb.setCharAt(i, j);
						String s = sb.toString();
						if (dict.contains(s)) {
							dict.remove(s);
							candidates.add(s);
						}
					}
				}
			}
			if (candidates.peek().equals(dummy)) {
				candidates.add(dummy);
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		WordLadder wl = new WordLadder();
		HashSet<String> dict = new HashSet<String>();
		// "red", "tax", ["ted","tex","red","tax","tad","den","rex","pee"]
		dict.add("ted");
		dict.add("tex");
		dict.add("red");
		dict.add("tax");
		dict.add("tad");
		dict.add("den");
		dict.add("rex");
		dict.add("pee");
		System.out.println(wl.findLadders("red", "tax", dict));
	}
}
