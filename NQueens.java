import java.util.ArrayList;
import java.util.List;

public class NQueens {
    
    public static boolean isSafe(List<String> board, int row, int col, int n) {
        int dupRow = row;
        int dupCol = col;

        while (row >= 0 && col >= 0) {
            if (board.get(row).charAt(col) == 'Q') {
                return false;
            }
            row--;
            col--;
        } 

        row = dupRow;
        col = dupCol;
        while (col >= 0) {
            if (board.get(row).charAt(col) == 'Q') {
                return false;
            }
            col--;
        }

        row = dupRow;
        col = dupCol;
        while (row < n && col >= 0) {
            if (board.get(row).charAt(col) == 'Q') {
                return false;
            }
            row++;
            col--;
        }

        return true;
    }

    public static void solve(List<String> board, List<List<String>> ans, int n, int col) {
        if (col == n) {
            ans.add(new ArrayList<>(board));  
            return;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col, n)) {
                StringBuilder sb = new StringBuilder(board.get(row));
                sb.setCharAt(col, 'Q');
                board.add(col,sb.toString());

                solve(board, ans, n, col + 1);

                sb.setCharAt(col, '.');
                board.set(row, sb.toString());
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        List<String> board = new ArrayList<>();

        String temp = ".".repeat(n);
        for (int i = 0; i < n; i++) {
            board.add(temp);
        }

        List<List<String>> ans = new ArrayList<>();
        solve(board, ans, n, 0);

        for (List<String> solution : ans) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
