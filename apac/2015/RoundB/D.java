import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long k=scanner.nextLong();
        return solveLarge(n, k);
        /*for (int i=1;i<=10;i++) {
            for (int k=1;k<=10000;k++) {
                String small=solveSmall(i, k);
                String large=solveLarge(i, k);
                if (!small.equals(large)) {
                    System.err.println(String.format("n=%d, k=%d, small=%s, large=%s", i, k, small, large));
                }
            }
        }
        return "";*/

    }

    // solve large...
    private String solveLarge(int n, long k) {
        BigInteger[][] dp=new BigInteger[n+1][n+1];
        dp[1][1]=BigInteger.ONE;
        for (int i=2;i<=n;i++) {
            dp[i][i]=BigInteger.ONE;
            for (int j=i-1;j>=2;j--) {
                dp[i][j]=dp[i-1][j-1].add(dp[i][j+1]);
            }
            dp[i][1]=dp[i][2].add(dp[i-1][1]);
        }
        if (BigInteger.valueOf(k).compareTo(dp[n][1])>0) return "Doesn't Exist!";
        return get(n, k, dp);
    }

    private String get(int n, long k, BigInteger[][] dp) {
        if (n==1) return "()";
        StringBuilder builder=new StringBuilder();
        for (int i=n;i>=1;i--) {
            if (BigInteger.valueOf(k).compareTo(dp[n][i])<=0) {
                String s=get(n-1, k-(i==n?0:dp[n][i+1].longValue()), dp);
                builder.append(s);
                builder.insert(i-1, "()");
                break;
            }
        }
        return builder.toString();
    }


    // solve small...
    private static List<String>[] lists=new List[11];
    private String solveSmall(int n, long k) {
        if (lists[n]==null) {
            lists[n]=generateParenthesis(n);
            Collections.sort(lists[n]);
        }
        return k>lists[n].size()?"Doesn't Exist!":lists[n].get((int)k-1);
    }
    private List<String> generateParenthesis(int n) {
        ArrayList<String> ans=new ArrayList<>();
        generateParenthesis(ans, "", n, 0);
        return ans;
    }
    private void generateParenthesis(ArrayList<String> ans, String s, int n, int m) {
        if (m+n==0) {
            ans.add(s);
            return;
        }
        if (n>0) generateParenthesis(ans, s+"(", n-1, m+1);
        if (m>0) generateParenthesis(ans, s+")", n, m-1);
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