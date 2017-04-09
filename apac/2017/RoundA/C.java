import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int m=scanner.nextInt();
        double[] c=new double[m+1];
        for (int i=0;i<=m;i++) c[i]=scanner.nextDouble();
        c[0]=-c[0];

        double start=-1+1e-12, end=1-1e-12;
        double vs=cal(c, start);
        while (end-start>1e-12) {
            double mid=(start+end)/2;
            double vm=cal(c, mid);
            if (vm==0) {start=mid;break;}
            if (vm*vs<0) end=mid;
            else start=mid;
        }
        return String.format("%.12f",start);
    }

    private double cal(double[] c, double r) {
        double x=0;
        for (double v: c) x=x*(1+r)+v;
        return x;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}