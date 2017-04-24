import java.io.PrintStream;
import java.util.*;

/**
 * Codejam to I/O for Women 2016 Problem C: Polynesiaglot
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int c=scanner.nextInt(), v=scanner.nextInt(), l=scanner.nextInt();
        long[] dp=new long[l+1];
        dp[0]=1;dp[1]=v;
        for (int i=2;i<=l;i++) {
            dp[i]=v*(dp[i-1]+c*dp[i-2])%1000000007;
        }
        return String.valueOf(dp[l]);
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