package leetcod;

import java.util.ArrayList;
import java.util.List;

/*
 * 1. server slow. bug in findNext checking conditions of whether badChoice is present.
 * 2. didn't pass own test. After an Add to solution, solution is potentially complete.
 * 3. fail {{a}} "ab". it exists situation when solution is empty, and we had a bad choice.
 * 4. exceed time limit. getNext going thru unnecessary ifs.
 * 5. fail ["ABCE","SFCS","ADEE"], "SEE" and exceed time limit. || instead of && for diff from badChoice.
 * 6. exceed time limit.
 */
public class WordSearch {
	// 1-pass correct!!
	public boolean exist_20130512(char[][] board, String word) {
        if (board==null || board.length==0 || board[0].length==0) return false;
        
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j]==word.charAt(0)) {
                    boolean[][] used = new boolean[board.length][board[0].length];
                    used[i][j] = true;
                    if (walk(board, i, j, word, 1, used)) return true;
                }
            }
        }
        return false;
    }
    
    public boolean walk(char[][] board, int x, int y, String word, int nextChar, boolean[][] used) {
        if (nextChar==word.length()) return true;
        if (x>0) {
            if (board[x-1][y]==word.charAt(nextChar) && !used[x-1][y]) {
                used[x-1][y] = true;
                if (walk(board, x-1, y, word, nextChar+1, used)) {return true;}
                else used[x-1][y] = false;
            }
        }
        if (x<board.length-1) {
            if (board[x+1][y]==word.charAt(nextChar) && !used[x+1][y]) {
                used[x+1][y] = true;
                if (walk(board, x+1, y, word, nextChar+1, used)) return true;
                else used[x+1][y] = false;
            }
        }
        if (y>0) {
            if (board[x][y-1]==word.charAt(nextChar) && !used[x][y-1]) {
                used[x][y-1] = true;
                if (walk(board, x, y-1, word, nextChar+1, used)) return true;
                else used[x][y-1] = false;
            }
        }
        if (y<board[0].length-1) {
            if (board[x][y+1]==word.charAt(nextChar) && !used[x][y+1]) {
                used[x][y+1] = true;
                if (walk(board, x ,y+1, word, nextChar+1, used)) return true;
                else used[x][y+1] = false;
            }
        }
        return false;
    }
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || board[0].length == 0)
			return false;
		if (word == null || word.length() == 0)
			return true;

		List<Tuple> solution = new ArrayList<Tuple>();

		Tuple next = getNext(board, word.charAt(0), null, null);
		while (solution.size() < word.length()) {
			if (next == null) {// stuck
				if (solution.size() == 0) {
					return false;
				}
				Tuple badChoice = solution.get(solution.size() - 1);
				solution.remove(solution.size() - 1);
				next = getNext(
						board,
						word.charAt(solution.size()),
						solution.size() == 0 ? null : solution.get(solution
								.size() - 1), badChoice);

			} else {
				solution.add(next);
				if (solution.size() == word.length()) {
					return true;
				}
				next = getNext(
						board,
						word.charAt(solution.size()),
						solution.size() == 0 ? null : solution.get(solution
								.size() - 1), null);
			}
		}
		return false;
	}

	private Tuple getNext(char[][] board, char c, Tuple prev, Tuple badChoice) {
		if (prev == null) {
			int start = badChoice == null ? 0 : badChoice.x;
			for (int i = start; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					if (board[i][j] == c
							&& (badChoice == null || badChoice.x != i || badChoice.y != j)) {
						return new Tuple(i, j);
					}
				}
			}
			return null;
		}

		if (c == board[prev.x][prev.y]
				&& (badChoice == null || badChoice.x != prev.x || badChoice.y != prev.y))
			return new Tuple(prev.x, prev.y);
		if (prev.x + 1 < board.length
				&& c == board[prev.x + 1][prev.y]
				&& (badChoice == null || badChoice.x != prev.x + 1 || badChoice.y != prev.y))
			return new Tuple(prev.x + 1, prev.y);
		if (prev.y + 1 < board[0].length
				&& c == board[prev.x][prev.y + 1]
				&& (badChoice == null || badChoice.x != prev.x || badChoice.y != prev.y + 1))
			return new Tuple(prev.x, prev.y + 1);
		if (prev.x - 1 >= 0
				&& c == board[prev.x - 1][prev.y]
				&& (badChoice == null || badChoice.x != prev.x - 1 || badChoice.y != prev.y))
			return new Tuple(prev.x - 1, prev.y);
		if (prev.y - 1 >= 0
				&& c == board[prev.x][prev.y - 1]
				&& (badChoice == null || badChoice.x != prev.x || badChoice.y != prev.y - 1))
			return new Tuple(prev.x, prev.y - 1);
		return null;
	}

	public class Tuple {
		int x;
		int y;

		public Tuple(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}

	public static void main(String[] args) {
		char[][] chars = new char[][] { { 'A', 'B', 'C', 'E' },
				{ 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		String s = "SEE";
		WordSearch ws = new WordSearch();
		System.out.println(ws.exist(chars, s));
	}
}
