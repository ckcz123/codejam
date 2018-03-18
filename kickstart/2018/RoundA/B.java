import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2018 Round A
 * Problem B. Lucky Dip
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        long[] values=new long[n];
        for (int i=0;i<n;i++) values[i]=scanner.nextLong();
        Arrays.sort(values);
        long[] sum=new long[n+1];
        for (int i=1;i<=n;i++)
            sum[i]=sum[i-1]+values[i-1];

        double ans=sum[n]/(n+0.0);
        if (k==1) {
            long mx=0;
            for (int w=0;w<=n;w++) {
                mx=Math.max(mx, w*sum[n]-n*sum[w]);
            }
            ans+=mx/(n*n+0.0);
        }
        return String.format("%.9f", ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        System.setIn(new FileInputStream("input.txt"));
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