import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        double[] p=new double[n];
        for (int i=0;i<n;i++) p[i]=scanner.nextDouble();
        double ans=0;
        Arrays.sort(p);
        for (int x=0;x<=k;x++) {
            int y=k-x, j=0;
            double[] candidate=new double[k];
            for (int i=0;i<x;i++)
                candidate[j++]=p[i];
            for (int i=0;i<y;i++)
                candidate[j++]=p[n-1-i];
            ans=Math.max(ans, cal(candidate, k));
        }
        return String.format("%.9f", ans);
    }

    private double cal(double[] p, int k) {
        double[][] ans=new double[k+1][k+1];
        ans[0][0]=1;
        for (int i=1;i<=k;i++) {
            for (int j=0;j<=i;j++) {
                ans[i][j]=p[i-1]*(j==0?0:ans[i-1][j-1])+(1-p[i-1])*ans[i-1][j];
            }
        }
        return ans[k][k/2];
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