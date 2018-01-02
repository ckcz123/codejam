import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Round 1
 * Problem A. Pie Progress
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        int[][] dp=new int[n+1][n+1];
        for (int i=1;i<=n;i++) dp[0][i]=999999999;
        for (int i=1;i<=n;i++) {
            int[] data=new int[m+1];
            for (int j=1;j<=m;j++) data[j]=scanner.nextInt();
            Arrays.sort(data);
            for (int j=1;j<=m;j++) data[j]+=data[j-1];
            for (int j=i;j<=n;j++) {
                dp[i][j]=999999999;
                for (int w=0;w<=m;w++) {
                    if (j-w>=i-1) {
                        dp[i][j]=Math.min(dp[i][j], dp[i-1][j-w]+data[w]+w*w);
                    }
                }
            }
        }
        return String.valueOf(dp[n][n]);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
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
