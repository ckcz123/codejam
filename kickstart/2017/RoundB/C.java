import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round B Problem C: Christmas Tree
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int m=scanner.nextInt(),n=scanner.nextInt(),k=scanner.nextInt();
        char[][] map=new char[m][n];
        for (int i=0;i<m;i++) map[i]=scanner.next().toCharArray();
        int[][] height=new int[m][n];
        int ans=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                int cnt=0;
                for (int r=i;r<m;r++) {
                    int h=r-i, left=j-h, right=j+h;
                    if (left<0 || right>=n) break;
                    boolean t=true;
                    for (int c=left;c<=right;c++) {
                        if (map[r][c]=='.') t=false;
                    }
                    if (!t) break;
                    cnt++;
                }
                height[i][j]=cnt;
            }
        }
        int[][][] dp=new int[m][n][k+1];
        for (int i=m-1;i>=0;i--) {
            for (int j=0;j<n;j++) {
                if (height[i][j]==0) continue;
                for (int v=1;v<=k;v++) {
                    for (int h=1;h<=height[i][j];h++) {
                        int row=i+h, left=j-h+1, right=j+h-1;
                        if (v==1) dp[i][j][v]=Math.max(dp[i][j][v], h*h);
                        else if (row<m) {
                            for (int u=left;u<=right;u++) {
                                if (dp[row][u][v-1]>0)
                                    dp[i][j][v]=Math.max(dp[i][j][v], h*h+dp[row][u][v-1]);

                            }
                        }
                    }
                }
                ans=Math.max(ans, dp[i][j][k]);
            }
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
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