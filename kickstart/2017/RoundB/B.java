import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round B Problem B: Center
 * Check README.md for explanation.
 */
public class Main {

    class Point {
        double x, y, w;
        public Point(double _x, double _y, double _w) {
            x=_x;y=_y;w=_w;
        }
    }

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        Point[] points=new Point[n];
        double ans=1e10;
        for (int i=0;i<n;i++)
            points[i]=new Point(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
        for (int i=0;i<n;i++) {
            double b1=points[i].y-points[i].x, sum=.0;
            Point[] p1=new Point[n];
            for (int j=0;j<n;j++) {
                double b2=points[j].x+points[j].y, qx=(b2-b1)/2;
                p1[j]=new Point(qx, 0, points[j].w);
                sum+=Math.abs(qx-points[j].x)*points[j].w;
            }
            Arrays.sort(p1, Comparator.comparingDouble(p->p.x));
            ans=Math.min(ans, sum+cal(p1, n));
        }
        return String.format("%.9f", ans);
    }

    private double cal(Point[] points, int n) {
        double x=points[0].x, sum=.0, q=.0;
        for (Point p: points) {
            sum+=Math.abs(x-p.x)*p.w;
            q-=p.w;
        }
        double ans=sum;
        for (int i=0;i<n;i++) {
            double nx=points[i].x, dx=nx-x;
            sum+=q*dx; q+=2*points[i].w;
            x=nx;
            ans=Math.min(ans, sum);
        }
        return ans;
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