import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round D
 * Problem A. Go Sightseeing
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long ts=scanner.nextLong(), tf=scanner.nextLong();
        long[][] cities=new long[n][3];
        for (int i=1;i<n;i++) {
            cities[i]=new long[] {scanner.nextLong(), scanner.nextLong(), scanner.nextLong()};
        }

        long[][] dp=new long[n+1][n+1];
        dp[1][0]=0;
        for (int i=2;i<=n;i++) {
            for (int j=0;j<i;j++) {

                long min=tf+1;
                if (j>0) {
                    min=Math.min(ceil(dp[i-1][j-1]+ts, cities[i-1][0], cities[i-1][1])+cities[i-1][2], min);
                }
                if (j<i-1) {
                    min=Math.min(ceil(dp[i-1][j], cities[i-1][0], cities[i-1][1])+cities[i-1][2], min);
                }
                dp[i][j]=min;
            }
        }
        for (int i=n-1;i>=0;i--) {
            if (dp[n][i]<=tf) {
                return String.valueOf(i);
            }
        }
        return "IMPOSSIBLE";
    }

    private long ceil(long x, long a, long b) {
        // a+b*n
        if (x<=a) return a;
        long y=x-a;
        if (y%b==0) return x;
        return a+y/b*b+b;
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