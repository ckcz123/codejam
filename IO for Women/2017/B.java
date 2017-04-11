import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        double[] p=new double[2*n];
        for (int i=0;i<2*n;i++)
            p[i]=scanner.nextDouble();
        Arrays.sort(p);
        double ans=1;
        for (int i=0;i<n;i++)
            ans*=(1-p[i]*p[2*n-1-i]);
        return String.format("%.9f", ans);
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