import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1A
 * Problem C. Collecting Cards
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        init();
        double[] e=new double[n+1];
        double[][] t=new double[n+1][n+1];
        for (int i=0;i<=n;i++)
            for (int j=Math.max(m, i);j<=Math.min(n, i+m);j++)
                t[i][j]=c[n-i][j-i]*c[i][m-(j-i)]/c[n][m];
        for (int i=n-1;i>=0;i--) {
            double v=1.;
            for (int j=i+1;j<=n;j++)
                v+=e[j]*t[i][j];
            e[i]=v/(1-t[i][i]);
        }
        return String.format("%.9f", e[0]);
    }

    private static double[][] c;
    private void init() {
        if (c!=null) return;
        c=new double[50][50];
        for (int i=0;i<50;i++) {
            for (int j=0;j<=i;j++) {
                if (j==0 || j==i) c[i][j]=1.;
                else c[i][j]=c[i-1][j]+c[i-1][j-1];
            }
        }
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