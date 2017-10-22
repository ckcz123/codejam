import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round G
 * Problem C. Matrix Cutting
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        int[][] matrix=new int[n][m];
        for (int i=0;i<n;i++) for (int j=0;j<m;j++) matrix[i][j]=scanner.nextInt();

        // calculate min
        int[][][][] minval=new int[n][m][n][m];
        for (int row_delta=0;row_delta<n;row_delta++) {
            for (int top=0;top<n-row_delta;top++) {
                int bottom=top+row_delta;
                for (int col_delta=0;col_delta<m;col_delta++) {
                    for (int left=0;left<m-col_delta;left++) {
                        int right=left+col_delta;
                        int v=matrix[bottom][right];

                        if (row_delta>0)
                            v=Math.min(v, Math.min(minval[top][left][bottom-1][right], minval[bottom][left][bottom][right]));
                        if (col_delta>0)
                            v=Math.min(v, Math.min(minval[top][left][bottom][right-1], minval[top][right][bottom][right]));

                        minval[top][left][bottom][right]=v;
                    }
                }
            }
        }

        int[][][][] ans=new int[n][m][n][m];
        for (int row_delta=0;row_delta<n;row_delta++) {
            for (int top = 0; top < n - row_delta; top++) {
                int bottom = top + row_delta;
                for (int col_delta = 0; col_delta < m; col_delta++) {
                    for (int left = 0; left < m - col_delta; left++) {
                        int right = left + col_delta;
                        int x=(top==bottom&&left==right)?0:minval[top][left][bottom][right];
                        int max=0;
                        for (int r=top;r<bottom;r++)
                            max=Math.max(max, ans[top][left][r][right]+ans[r+1][left][bottom][right]);
                        for (int c=left;c<right;c++)
                            max=Math.max(max, ans[top][left][bottom][c]+ans[top][c+1][bottom][right]);
                        ans[top][left][bottom][right]=max+x;
                    }
                }
            }
        }
        return String.valueOf(ans[0][0][n-1][m-1]);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Throwable e) {
                System.err.println("ERROR in case #"+t);
                e.printStackTrace();
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}