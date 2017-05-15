import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Qualification Round
 * Problem B. Dancing With the Googlers
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), s=scanner.nextInt(), p=scanner.nextInt();
        int[] t=new int[n];
        for (int i=0;i<n;i++) t[i]=scanner.nextInt();
        int[][] dp=new int[n+1][s+1];
        for (int i=1;i<=n;i++) {
            for (int j=0;j<=s;j++) {

                // not a surprise
                dp[i][j]=Math.max(
                        // not a surprise
                        ((t[i-1]+2)/3>=p?1:0)+dp[i-1][j],

                        // is a surprise
                        j==0?0:(((t[i-1]+4)/3>=Math.max(p, 2)?1:0)+dp[i-1][j-1])
                );

            }
        }
        return String.valueOf(dp[n][s]);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=Integer.parseInt(scanner.nextLine());
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



