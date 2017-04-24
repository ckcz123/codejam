import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * APAC 2016 Round D Problem B: gBalloon
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        // return solveSmall(scanner);
        return solveLarge(scanner);
    }

    // binary search: O(mn*log(p_max)), 1.175s to solve large...
    private String solveLarge(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), q=scanner.nextInt();
        int[] wind=new int[m];
        for (int i=0;i<m;i++) wind[i]=scanner.nextInt();
        int[][] balloons=new int[n][2];
        for (int i=0;i<n;i++)
            balloons[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        int start=1, end=11000;
        while (start<end) {
            int mid=(start+end)/2, power=0;
            for (int i=0;i<n;i++) {
                int p=balloons[i][0], h=balloons[i][1];
                if (p==0) continue;
                int need=q+1;
                for (int j=0;j<m;j++) {
                    if (wind[j]*p>=0 || Math.abs(wind[j])*mid<Math.abs(p)) continue;
                    need=Math.min(need, Math.abs(h-j));
                }
                power+=need;
            }
            if (power<=q) end=mid;
            else start=mid+1;
        }
        return start>10500?"IMPOSSIBLE":String.valueOf(start);

    }

    // dp, O(nmq), 164.685s to solve large..
    /*private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), q=scanner.nextInt();
        int[][] dp=new int[n+1][q+1];
        int[] wind=new int[m];
        for (int i=0;i<m;i++) wind[i]=scanner.nextInt();
        int[][] balloons=new int[n][2];
        for (int i=0;i<n;i++)
            balloons[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        for (int i=1;i<=n;i++) {
            for (int j=0;j<=q;j++) {
                int p=balloons[i-1][0];
                if (p==0) {dp[i][j]=dp[i-1][j];continue;}
                dp[i][j]=Integer.MAX_VALUE;
                for (int k=0;k<m;k++) {
                    int delta=Math.abs(k-balloons[i-1][1]);
                    if (j<delta) continue;
                    dp[i][j]=Math.min(dp[i][j], Math.max(dp[i-1][j-delta],
                            wind[k]*p>=0?Integer.MAX_VALUE:
                                    (Math.abs(p)-1)/Math.abs(wind[k])+1));
                }
            }
        }
        return dp[n][q]==Integer.MAX_VALUE?"IMPOSSIBLE":String.valueOf(dp[n][q]);
    }*/

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