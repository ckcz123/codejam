import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Qualification Round
 * Problem C. Fighting the Zombie
 */
public class Main {

    public String solve(Scanner scanner) {
        int k=scanner.nextInt(), n=scanner.nextInt();
        double ans=0;
        while (n-->0) {
            int kk=k, index;
            String x=scanner.next();
            if ((index=x.indexOf('+'))>=0) {
                kk-=Integer.parseInt(x.substring(index+1));
                x=x.substring(0, index);
            }
            else if ((index=x.indexOf('-'))>=0) {
                kk+=Integer.parseInt(x.substring(index+1));
                x=x.substring(0, index);
            }
            String[] xx=x.split("d");
            ans=Math.max(ans, calculate(Integer.parseInt(xx[0]), Integer.parseInt(xx[1]), kk));
        }
        return String.format("%.9f", ans);
    }

    private double calculate(int x, int y, int k) {
        if (k<=x) return 1;
        if (k>x*y) return 0;
        double[][] dp=new double[x+1][k];
        dp[0][0]=1;
        for (int i=1;i<=x;i++) {
            for (int j=0;j<k;j++) {
                for (int u=1;u<=y;u++) {
                    if (j>=u)
                        dp[i][j]+=dp[i-1][j-u];
                }
                dp[i][j]/=y;
            }
        }
        double ans=1;
        for (int j=0;j<k;j++)
            ans-=dp[x][j];
        return ans;
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
