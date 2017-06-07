import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 1B
 * Problem C. Your Rank is Pure
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[][] dp=new long[n+1][n+1];
        for (int i=2;i<=n;i++) dp[i][1]=1;

        // cal C(n, m)
        init();

        for (int i=3;i<=n;i++) {
            for (int j=2;j<=i-1;j++) {
                // The set has j elements.
                // {x} j {y} i
                // x<=j-2; y<=i-j-x
                // dp[j][x+1] * (i-j-x)Cy

                for (int x=0;x<=j-2;x++) {
                    int y=j-x-2;
                    if (y<0 || y>i-j-1) continue;
                    dp[i][j]=(dp[i][j]+(dp[j][x+1]*c[i-j-1][y])%100003)%100003;
                }
            }
        }

        long ans=0;
        for (int i=1;i<=n;i++) {
            ans=(ans+dp[n][i])%100003;
        }
        return String.valueOf(ans);
    }

    private static long[][] c;
    private void init() {
        if (c!=null) return;
        c=new long[550][550];
        for (int i=0;i<550;i++) {
            for (int j=0;j<=i;j++) {
                if (j==0 || j==i) c[i][j]=1;
                else c[i][j]=(c[i-1][j]+c[i-1][j-1])%100003;
            }
        }
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