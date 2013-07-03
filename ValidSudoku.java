package leetcod;

import java.util.BitSet;

public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
	        if (board.length!=9 || board[0].length!=9) return false;
	        
	        for (int i=0; i<9; i++) {
	            BitSet row = new BitSet(9);
	            BitSet col = new BitSet(9);
	            for (int j=0; j<9; j++) {
	                if (board[i][j]!='.') {
	                    int number = board[i][j]-'1';
	                    if (row.get(number)) {
	                        return false;
	                    }
	                    row.set(number);
	                }
	                if (board[j][i]!='.') {
	                    int number = board[j][i]-'1';
	                    if (col.get(number)) {
	                        return false;
	                    }
	                    col.set(number);
	                }
	            }
	        }
	        for (int i=0; i<9; i+=3) {
	            for (int j=0; j<9; j+=3) {
	                BitSet square = new BitSet(9);
	                for (int p=i; p<i+3; p++) {
	                    for (int q=j; q<j+3; q++) {
	                        if (board[p][q]!='.') {
	                            int num = board[p][q]-'1';
	                            if (square.get(num)) {
	                                return false;
	                            }
	                            square.set(num);
	                        }
	                    }
	                }
	            }
	        }
	        return true;
	}
	
	public static void main(String[] args) {
		ValidSudoku me = new ValidSudoku();
		boolean k = me.isValidSudoku(new char[][]{{'5','3','.','.','7','.','.','.','.'},
									  {'6','.','.','1','9','5','.','.','.'},
									  {'.','9','8','.','.','.','.','6','.'},
									  {'8','.','.','.','6','.','.','.','3'},
									  {'4','.','.','8','.','3','.','.','1'},
									  {'7','.','.','.','2','.','.','.','6'},
									  {'.','6','.','.','.','.','2','8','.'},
									  {'.','.','.','4','1','9','.','.','5'},
									  {'.','.','.','.','8','.','.','7','9'}});
		System.out.println(k);
	}
}
