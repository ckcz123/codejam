import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round D
 * Problem C. Trash Throwing
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        double p=scanner.nextDouble(), h=scanner.nextDouble();
        double[][] points=new double[n][2];
        for (int i=0;i<n;i++) {
            points[i]=new double[] {scanner.nextDouble(), -scanner.nextDouble()};
            if (points[i][0]>=p/2) points[i][0]=p-points[i][0];
        }

        // binary-search r
        double start=0, end=h;
        while (end-start>1e-10) {
            double mid=(start+end)/2;
            if (ableR(n, p, h, points, mid)) start=mid;
            else end=mid;
        }
        return String.format("%.9f", start);
    }

    private boolean ableR(int n, double p, double h, double[][] points, double r) {
        double[][] a=new double[n][2];
        // a>=a[i][0] || 0<=a<=a[i][1]
        double max=(r-h)/(-p*p/4);
        for (int i=0;i<n;i++) {
            double x=points[i][0], y=points[i][1];
            double _a=y/x/(x-p);
            if (x<=r) {
                a[i][0]=max+1;
            }
            else {
                double start=_a, end=max+1;
                while (end-start>1e-10) {
                    double mid=(start+end)/2;
                    double cx=p/2, cy=mid*cx*(cx-p);
                    if (isOutCircle(mid, x-cx, y-cy, r, p)) end=mid;
                    else start=mid;
                }
                a[i][0]=start;
            }
            if (y+r>=0) {
                a[i][1]=-1;
            }
            else {
                double start=0, end=_a;
                while (end-start>1e-10) {
                    double mid=(start+end)/2;
                    double cx=p/2, cy=mid*cx*(cx-p);
                    if (isOutCircle(mid, x-cx, y-cy, r, p)) start=mid;
                    else end=mid;
                }
                a[i][1]=start;
            }
        }
        ArrayList<double[]> list=new ArrayList<>();
        list.add(new double[] {0., (r-h)/(-p*p/4)});
        for (double[] aa: a) {
            ArrayList<double[]> next=new ArrayList<>();
            for (double[] d: list) {
                double[] nd=d.clone();
                nd[0]=Math.max(nd[0], aa[0]);
                if (nd[0]<=nd[1]+1e-10) next.add(nd);
                double[] nd2=d.clone();
                nd2[1]=Math.min(nd2[1], aa[1]);
                if (nd2[0]<=nd2[1]+1e-10) next.add(nd2);
            }
            list=next;
        }
        return !list.isEmpty();
    }

    private boolean isOutCircle(double a, double x, double y, double r, double p) {
        // (x-x0)^2+(ax^2-y0)^2>=r^2
        double a4=a*a, a2=-2*a*y+1, a1=-2*x, a0=x*x+y*y-r*r;
        // a4x^4+a2x^2+a1x+a0>=0
        double b3=4*a4, b1=2*a2, b0=a1;
        // b3x^3+b1x+b0=0
        // find solution
        // -p/2 -> 0
        double start=-p/2, end=0;
        while (end-start>1e-10) {
            double mid=(start+end)/2;
            if (b3*mid*mid*mid+b1*mid+b0<=0) start=mid;
            else end=mid;
        }
        return a4*start*start*start*start+a2*start*start+a1*start+a0>=-1e-10;
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