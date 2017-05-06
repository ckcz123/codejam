import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Qualification Round
 * Problem C. Minesweeper Master
 */
public class Main {

    private String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt(), m=scanner.nextInt();
        char[][] board=new char[r][c];
        for (char[] b: board)
            Arrays.fill(b, '.');
        if (m==r*c-1) {
            for (char[] b: board)
                Arrays.fill(b, '*');
            board[0][0]='c';
            return output(board);
        }
        if (r==1 || c==1) {
            for (int i=r-1;i>=0;i--)
                for (int j=c-1;j>=0;j--)
                    if (m-->0)
                        board[i][j]='*';
            board[0][0]='c';
            return output(board);
        }
        for (int filledRows=0;filledRows<=r-2;filledRows++) {
            for (int filledCols=0;filledCols<=c-2;filledCols++) {
                int left=m-c*filledRows-r*filledCols+filledCols*filledRows;
                if (left<0) continue;
                int leftRows=r-filledRows, leftCols=c-filledCols;
                int able=(leftRows-2)*(leftCols-2);
                if (able<left) continue;

                board[0][0]='c';
                for (int i=0;i<r;i++)
                    for (int j=0;j<c;j++)
                        if (i>=leftRows || j>=leftCols)
                            board[i][j]='*';
                for (int i=leftRows-1;i>=2;i--) {
                    for (int j=leftCols-1;j>=2;j--) {
                        if (left-->0)
                            board[i][j]='*';
                    }
                }
                return output(board);
            }
        }
        return "\nImpossible";
    }

    private String output(char[][] board) {
        return "\n"+String.join("\n",
                Arrays.stream(board).map(String::valueOf).collect(Collectors.toList()));
    }


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}