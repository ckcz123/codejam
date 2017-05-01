import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        double u=scanner.nextDouble();
        double[] p=new double[n]; for (int i=0;i<n;i++) p[i]=scanner.nextDouble();
        Arrays.sort(p);
        return String.format("%.9f", cal(p, n, u));
    }

    private double cal(double[] p, int n, double u) {
        double avg=u;
        for (int i=0;i<n;i++) avg+=p[i];
        avg/=n;
        int index=n;
        double ans=1.;
        while (p[index-1]>avg+1e-10) {
            ans*=p[index-1];
            index--;
        }
        if (index<n)
            return ans*cal(p, index, u);
        return Math.pow(avg, n);
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
            catch (Exception e) {System.err.println("ERROR #"+t);e.printStackTrace();}
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}