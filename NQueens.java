package leetcod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leetcod.NQueens.Tuple;

/*
 * 1. runtime err. fail to update okSpot after adding 1st queen
 * 2. err in mirroring, when making new copies, just mirror, don't flip.
 * 3. right mirror could == low mirror. need to consider axis symetrical
 * 4. runtime err. n=8, typo in queenKill
 * 5. when n=1, there is a solution.
 * 6. fail to find solution for n>=6, missed some solution for n==5 - concept of backtracking lost.
 * 7. my solution solves up to n=5. Exceed time limit by great measure. TODO: speed up.
 */
public class NQueens {
    public int totalNQueens(int n) {
        if (n<=0) return 0;
         if (n==1) return 1;
         int[] count = new int[1];
         doWork(n, 0, new int[n], count);
         return count[0];
     }
     
     public void doWork(int n, int i, int[] queens, int[] count) {
         if (n==i) {
             count[0] = count[0]+1;
         }
         
         for (int j=0; j<queens.length; j++) {
             if (isValid(i, j, queens)) {
                 queens[i] = j;
                 doWork(n, i+1, queens, count);
             }
         }
     }
     
     public boolean isValid(int row, int pos, int[] queens) {
         for (int i=0; i<row; i++) {
             if (queens[i]==pos) return false;
             if (Math.abs(pos-queens[i])==row-i) return false;
         }
         return true;
     }
    
	public ArrayList<String[]> solveNQueens_20130526(int n) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        if (n<=0) return result;
        if (n==1) {
            result.add(new String[]{"Q"});
            return result;
        }
        
        doWork(result, n, 0, new int[n]);
        return result;
    }
    
    public void doWork(ArrayList<String[]> result, int n, int i, int[] queens) {
        if (n==i) {
            result.add(translate(queens));
        }
        else {
            List<Integer> candidates = getOkColumns(i, queens);
            for (int c: candidates) {
                queens[i] = c;
                doWork(result, n, i+1, queens);
            }
        }
    }
    
    public List<Integer> getOkColumns(int row, int[] queens) {
        BitSet bs = new BitSet(queens.length);
        bs.clear();
        for (int i=0; i<row; i++) {
            bs.set(queens[i]);
            int rowDiff = row-i;
            if (queens[i]+rowDiff<queens.length) bs.set(queens[i]+rowDiff);
            if (queens[i]-rowDiff>=0) bs.set(queens[i]-rowDiff);
        }
        List<Integer> goodPos = new ArrayList<Integer>();
        for (int i=0; i<queens.length; i++) {
            if (!bs.get(i)) goodPos.add(i);
        }
        return goodPos;
    }
    
    public String[] translate(int[] queens) {
        String[] result = new String[queens.length];
        for (int i=0; i<queens.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<queens.length; j++) {
                if (queens[i]!=j) sb.append('.');
                else sb.append('Q');
            }
            result[i] = sb.toString();
        }
        return result;
    }
    
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		if (n == 1) {
			result.add(new String[] { "Q" });
			return result;
		}
		if (n < 4) {
			return result;
		}
		int totalCells = n * n;

		for (int i = 0; i < n / 2; i++) {
			int okSpots = totalCells;
			boolean[][] okMatrix = new boolean[n][n];
			List<Tuple> queens = new ArrayList<Tuple>();

			queens.add(new Tuple(0, i));
			okSpots -= queenKill(queens.get(queens.size()-1), okMatrix);

			while (queens.size() < n && queens.size()>0) {
				if (okSpots > 0) {
					Tuple nextQueen = nextOkPosition(okMatrix, queens.get(queens.size()-1).x, queens.get(queens.size()-1).y);
					queens.add(nextQueen);
					okSpots -= queenKill(nextQueen, okMatrix);
				} else {
					int deleteQueenRow = queens.get(queens.size()-1).x;
					int deleteQueenCol = queens.get(queens.size()-1).y;
					queens.remove(queens.size()-1);
					okSpots = recomputeOkMatrix(queens, okMatrix);
					okMatrix[deleteQueenRow][deleteQueenCol] = false;
					okSpots--;
				}
			}

			if (queens.size() == n) {
				char[][] orig = generateResult(n, queens);
				result.add(translate(orig));
				result.add(translate(rightMirror(orig)));
				if (!isSymetrical(orig, queens)) {
					//result.add(translate(downMirror(orig)));
				}
			}
		}

		return result;
	}

	private int recomputeOkMatrix(List<Tuple> queens, boolean[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = false;
			}
		}
		int killed = 0;
		for (Tuple tup : queens) {
			killed += queenKill(tup, matrix);
		}
		return matrix.length * matrix.length - killed;
	}

	private boolean isSymetrical(char[][] matrix, List<Tuple> queens) {

		for (Tuple queen : queens) {
			if (matrix[generateZhouDuiChen(matrix.length, queen.x)][generateZhouDuiChen(
					matrix.length, queen.y)] != 'Q') {
				return false;
			}
		}
		return true;
	}

	private int generateZhouDuiChen(int n, int i) {
		return n - 1 - i;
	}

	private int queenKill(Tuple queenPosition, boolean[][] matrix) {
		if (queenPosition == null) {
			return 0;
		}
		int row = queenPosition.x;
		int column = queenPosition.y;
		int newKillCount = 1;
		matrix[row][column] = true;

		for (int i = 0; i < matrix[0].length; i++) {
			if (!matrix[row][i]) {
				newKillCount++;
				matrix[row][i] = true;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			if (!matrix[i][column]) {
				newKillCount++;
				matrix[i][column] = true;
			}
		}
		int i = 1;
		while (row + i < matrix.length && column + i < matrix[0].length) {
			if (!matrix[row + i][column + i]) {
				newKillCount++;
				matrix[row + i][column + i] = true;
			}
			i++;
		}
		i = 1;
		while (row - i >= 0 && column - i >= 0) {
			if (!matrix[row - i][column - i]) {
				newKillCount++;
				matrix[row - i][column - i] = true;
			}
			i++;
		}
		i = 1;
		while (row - i >= 0 && column + i < matrix[0].length) {
			if (!matrix[row - i][column + i]) {
				newKillCount++;
				matrix[row - i][column + i] = true;
			}
			i++;
		}
		i = 1;
		while (row + i < matrix.length && column - i >= 0) {
			if (!matrix[row + i][column - i]) {
				newKillCount++;
				matrix[row + i][column - i] = true;
			}
			i++;
		}
		return newKillCount;
	}

	private Tuple nextOkPosition(boolean[][] matrix, int row, int column) {
		for (int i = row > 0 ? row : 0; i < matrix.length; i++) {
			int columnStart = 0;
			if (column > 0) {
				if (i == row) {
					columnStart = column + 1;
				} else {
					columnStart = 0;
				}
			}

			for (int j = columnStart; j < matrix[0].length; j++) {
				if (matrix[i][j] == false) {
					return new Tuple(i, j);
				}
			}
		}
		return null;
	}

	private char[][] generateResult(int n, List<Tuple> queens) {
		char[][] nNoQueens = new char[n][n];

		for (int i = 0; i < n; i++) {
			Arrays.fill(nNoQueens[i], '.');
		}

		for (Tuple tuple : queens) {
			int row = tuple.x;
			int column = tuple.y;
			nNoQueens[row][column] = 'Q';
		}
		return nNoQueens;
	}

	private char[][] rightMirror(char[][] input) {
		char[][] mirror = new char[input.length][input[0].length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
				mirror[i][j] = input[i][input[0].length - 1 - j];
			}
		}
		return mirror;
	}

	private char[][] downMirror(char[][] input) {
		char[][] mirror = new char[input.length][input[0].length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
				mirror[i][j] = input[input.length - 1 - i][j];
			}
		}
		return mirror;
	}

	private String[] translate(char[][] matrix) {
		String[] result = new String[matrix.length];
		int i = 0;
		for (char[] row : matrix) {
			StringBuilder sb = new StringBuilder();
			for (char c : row) {
				sb.append(c);
			}
			result[i++] = sb.toString();
		}
		return result;
	}

	public class Tuple {
		int x;
		int y;

		public Tuple(int v1, int v2) {
			x = v1;
			y = v2;
		}
	}

	public static void main(String[] args) {
		NQueens nq = new NQueens();
		ArrayList<String[]> result = nq.solveNQueens(6);
		for (String[] ss : result) {
			System.out.println("------------");
			for (String s : ss) {
				System.out.println(s);
			}
		}
	}
}
