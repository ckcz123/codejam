import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 1A
 * Problem B. Make it Smooth
 */
public class Main {

    public String solve(Scanner scanner) {
        int delete=scanner.nextInt(), insert=scanner.nextInt(), m=scanner.nextInt(), n=scanner.nextInt();
        int[] a=new int[n];
        for (int i=0;i<n;i++) a[i]=scanner.nextInt();
        int cost=Integer.MAX_VALUE/10;
        int[][] dp=new int[n+1][256];
        for (int i=1;i<=n;i++) {
            for (int j=0;j<=255;j++) {
                int need=Math.abs(j-a[i-1]);
                dp[i][j]=Integer.MAX_VALUE/10;
                int[] v=new int[256];
                Arrays.fill(v, Integer.MAX_VALUE/10);
                for (int w=256;w!=0;w=(w>=0?-(w-1):-w)) {
                    int last=j+w;
                    if (last<0 || last>255) continue;

                    v[last]=Math.min(dp[i-1][last], v[last]);
                    // insert
                    if (last>j+m) v[last-m]=Math.min(v[last-m], v[last]+insert);
                    if (last<j-m) v[last+m]=Math.min(v[last+m], v[last]+insert);
                }

                v[j]=Math.min(v[j], dp[i-1][j]);

                // update
                for (int x=Math.max(0, j-m);x<=Math.min(255, j+m);x++)
                    dp[i][j]=Math.min(dp[i][j], need+v[x]);

                // delete
                dp[i][j]=Math.min(dp[i][j], delete+v[j]);
            }

        }
        for (int j=0;j<=255;j++)
            cost=Math.min(cost, dp[n][j]);
        return String.valueOf(cost);
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