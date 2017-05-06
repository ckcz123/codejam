import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) throws Throwable {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        String s=new StringBuilder(scanner.next()).reverse().toString()
                .replace('.','0').replace('X','1');
        int n=s.length();
        return String.format("%.12f", dfs(new double[1<<n], n, Integer.parseInt(s, 2)));

    }

    private double dfs(double[] dp, int n, int mask) {
        if (mask==(1<<n)-1 || dp[mask]!=0) return dp[mask];
        double p=.0;
        for (int i=0;i<n;i++) {
            for (int j=0;;j++) {
                int index=(i+j)%n;
                if ((mask&(1<<index))!=0) continue;
                p+=(dfs(dp, n, mask|(1<<index))+(n-j))/n;
                break;
            }
        }
        dp[mask]=p;
        return p;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
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