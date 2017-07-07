import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1C
 * Problem B. Ugly Numbers
 */
public class Main {

    public String solve(Scanner scanner) {
        String s=scanner.next();

        // 2+3+5+7-2x3-2x5-2x7-3x5-3x7-5x7+2x3x5+2x3x7+2x5x7+3x5x7-2x3x5x7
        return cal(s, 2).add(cal(s, 3)).add(cal(s, 5)).add(cal(s, 7))
                .subtract(cal(s, 6)).subtract(cal(s, 10)).subtract(cal(s, 14))
                .subtract(cal(s, 15)).subtract(cal(s, 21)).subtract(cal(s, 35))
                .add(cal(s, 30)).add(cal(s, 42)).add(cal(s, 70)).add(cal(s, 105))
                .subtract(cal(s, 210)).toString();
    }

    private BigInteger cal(String s, int mod) {
        int l=s.length();
        BigInteger[][] dp=new BigInteger[l+1][mod];
        dp[0][0]=BigInteger.ONE;
        for (int i=1;i<mod;i++) dp[0][i]=BigInteger.ZERO;

        for (int i=1;i<=l;i++) {
            for (int m=0;m<mod;m++) {
                dp[i][m]=BigInteger.ZERO;
                for (int x=1;x<=i;x++) {
                    BigInteger num=new BigInteger(s.substring(x-1, i));
                    int md=num.mod(BigInteger.valueOf(mod)).intValue();
                    // +
                    dp[i][m]=dp[i][m].add(dp[x-1][(m-md+mod)%mod]);
                    // -
                    if (x!=1)
                        dp[i][m]=dp[i][m].add(dp[x-1][(m+md)%mod]);

                }
            }
        }
        return dp[l][0];
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