package leetcod;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class SudokuSolver {
	static char[] lookup = new char[] {'1','2','3','4','5','6','7','8','9'}; 
    public void solveSudoku(char[][] board) {
        
        doWork(board, 0, -1);
    }
    
    public boolean doWork(char[][] board, int lasti, int lastj) {
        for (int i=lasti; i<9; i++) {
            for (int j=i==lasti?lastj+1:0; j<9; j++) {
                if (board[i][j]=='.') {
                    List<Character> candidates = getCandidates(board, i, j);
                    if (candidates.size()==0) {
                    	return false;
                    }
                    for (char c: candidates) {
                        board[i][j] = c;
                        if (doWork(board,i,j)) return true;                       
                    }
                    board[i][j] = '.';
                    return false;
                }
            }
        }
        return true;
    }
    
    public List<Character> getCandidates(char[][] board, int i, int j) {
        BitSet set = new BitSet(9);
        for (int k=0; k<9; k++) {
            if (board[i][k]!='.') {
                set.set(board[i][k]-'1');
            }
            if (board[k][j]!='.') {
                set.set(board[k][j]-'1');
            }
        }
        int start = 3*(i/3);
        int start2= 3*(j/3);
        for (int p=start; p< start +3; p++) {
            for (int q=start2; q< start2 + 3; q++) {
                if (board[p][q]!='.') {
                    set.set(board[p][q]-'1');
                } 
            }
        }
        List<Character> list = new ArrayList<Character>();
        for (int p=0; p<9; p++) {
            if (!set.get(p)) {
                list.add(lookup[p]);
            }
        }
        return list;
    }
    
	public static void main(String[] args) {
		char[][] puzzle = new char[][]{"..9748...".toCharArray(),
									   "7........".toCharArray(),
									   ".2.1.9...".toCharArray(),
									   "..7...24.".toCharArray(),
									   ".64.1.59.".toCharArray(),
									   ".98...3..".toCharArray(),
									   "...8.3.2.".toCharArray(),
									   "........6".toCharArray(),
									   "...2759..".toCharArray()};
		SudokuSolver solver = new SudokuSolver();
		solver.solveSudoku(puzzle);
	}
}
