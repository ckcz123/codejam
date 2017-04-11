import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[][] distance=new long[n][n];
        long[][] linked=new long[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<i;j++) {
                long x=scanner.nextLong();
                linked[i][j]=linked[j][i]=x;
                if (x==-1) x=Long.MAX_VALUE/100;
                distance[i][j]=distance[j][i]=x;
            }
        }

        // floyd
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    distance[i][j]=Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                }
            }
        }

        double ans=1e15;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (i==j || linked[i][j]<0) continue;
                ans=Math.min(ans, solve(n, distance, i, j, linked[i][j]));
            }
        }

        return String.format("%.9f", ans);
    }

    private double solve(int n, long[][] distance, int x, int y, long value) {
        long[] toX=distance[x], toY=distance[y];
        HashSet<Double> candidate=new HashSet<>();
        candidate.add(0d);
        candidate.add(value+0.0);
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                double v=(toY[j]+value-toX[i])/2.0;
                if (v>=0 && v<=value)
                    candidate.add(v);
            }
        }
        double ans=1e15;
        for (Double v: candidate) {
            double max=0;
            for (int i=0;i<n;i++) {
                max=Math.max(max, Math.min(toX[i]+v, toY[i]+value-v));
            }
            ans=Math.min(ans, max);
        }
        return ans;
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