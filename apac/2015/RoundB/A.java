import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt();
        long mod=1000000007;
        long[][] c=new long[n+1][n+1];
        for (int j=0;j<=n;j++) c[0][j]=1;
        for (int i=1;i<=n;i++) {
            for (int j=i;j<=n;j++)  {
                c[i][j]=(c[i-1][j-1]+c[i][j-1])%mod;
            }
        }
        long[][] dp=new long[m+1][n+1];
        dp[0][0]=1;
        for (int i=1;i<=m;i++) {
            for (int j=i;j<=n;j++) {
                for (int k=1;k<=j-i+1;k++) {
                    dp[i][j]+=(c[k][j]*dp[i-1][j-k])%mod;
                    dp[i][j]%=mod;
                }
            }
        }
        return String.valueOf(dp[m][n]);
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
