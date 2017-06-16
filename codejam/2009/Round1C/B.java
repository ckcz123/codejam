import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1B
 * Problem B. Center of Mass
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[][] input=new long[n][6];
        for (int i=0;i<n;i++) {
            for (int j=0;j<6;j++) input[i][j]=scanner.nextLong();
        }

        // (v0+v3t)^2+(v1+v4t)^2+(v2+v5t)^2
        long[] v=new long[6];
        for (int j=0;j<6;j++) {
            for (int i=0;i<n;i++) v[j]+=input[i][j];
            // v[j]/=n;
        }

        // at^2+bt+c
        long a=v[3]*v[3]+v[4]*v[4]+v[5]*v[5];
        long b=2*v[0]*v[3]+2*v[1]*v[4]+2*v[2]*v[5];
        long c=v[0]*v[0]+v[1]*v[1]+v[2]*v[2];

        // a==0
        if (a==0)
            return String.format("%.9f %.9f", Math.sqrt(c)/n, 0.);

        double t=-b/(2.*a);
        if (t<=0) t=0;
        double d=Math.sqrt(a*t*t+b*t+c);
        return String.format("%.9f %.9f", d/n, t);

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