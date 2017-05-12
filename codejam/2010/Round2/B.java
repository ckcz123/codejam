import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 2
 * Problem B. World Cup 2010
 */
public class Main {

    private String solve(Scanner scanner) {
        int p=scanner.nextInt(), n=(1<<p);
        int[] m=new int[n];
        for (int i=0;i<n;i++) m[i]=p-scanner.nextInt();
        long[][] prices=new long[n][n];
        for (int i=1;i<=p;i++) {
            int delta=1<<i;
            for (int j=0;j<n;j+=delta) {
                prices[j][j+delta-1]=scanner.nextLong();
            }
        }
        return String.valueOf(dfs(m, prices, new long[n][n][p+1], 0, n-1, 0));
    }

    private long dfs(int[] m, long[][] prices, long[][][] dp,
                     int start, int end, int watch) {
        if (start==end)
            return watch>=m[start]?0:Long.MAX_VALUE/20;
        if (dp[start][end][watch]!=0)
            return dp[start][end][watch];
        long price=prices[start][end];
        int mid=(start+end)/2;
        dp[start][end][watch]
                =Math.min(Long.MAX_VALUE/20, Math.min(
                price+dfs(m, prices, dp, start, mid, watch+1)
                        +dfs(m, prices, dp, mid+1, end, watch+1),
                dfs(m, prices, dp, start, mid, watch)
                        +dfs(m, prices, dp, mid+1, end, watch)));
        return dp[start][end][watch];
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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