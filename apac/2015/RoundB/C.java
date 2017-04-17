import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        int[] nums=new int[n];
        for (int i=0;i<n;i++) nums[i]=scanner.nextInt();
        int[][] dp=new int[n+1][n+1];
        for (int d=0;d<=n-1;d++) {
            for (int i=1;i<=n-d;i++) {
                int j=i+d;
                if (j-i<=1) {
                    dp[i][j]=d+1; continue;
                }
                dp[i][j]=dp[i][j-1]+1;

                // find b
                for (int b=j-1;b>i;b--) {
                    if (nums[b-1]==nums[j-1]-k && dp[b+1][j-1]==0) {
                        // find a
                        for (int a=b-1;a>=i;a--) {
                            if (nums[a-1]==nums[b-1]-k && dp[a+1][b-1]==0)
                                dp[i][j]=Math.min(dp[i][j], dp[i][a-1]);
                        }
                    }
                }
            }
        }
        return String.valueOf(dp[1][n]);
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
