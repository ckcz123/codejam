import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 2
 * Problem D. Grazing Google Goats
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        double[][] points=new double[n][2];
        for (int i=0;i<n;i++) {
            points[i]=new double[] {scanner.nextDouble(), scanner.nextDouble()};
        }
        ArrayList<String> list=new ArrayList<>();
        while (m-->0) {
            double x=scanner.nextDouble(), y=scanner.nextDouble();
            Circle[] circles=new Circle[n];
            for (int i=0;i<n;i++) {
                double xi=points[i][0], yi=points[i][1];
                circles[i]=new Circle(xi, yi, (xi-x)*(xi-x)+(yi-y)*(yi-y));
            }
            double ans=.0;
            for (int i=0;i<n;i++)
                for (int j=i+1;j<n;j++)
                    ans+=calArea(circles[i], circles[j]);
            list.add(String.format("%.9f", ans));
        }
        return String.join(" ",list);
    }

    class Circle {
        double x,y,r,r2;
        public Circle(double _x, double _y, double _r2) {
            x=_x;
            y=_y;
            r2=_r2;
            r=Math.sqrt(_r2);
        }
    }

    private double calArea(Circle c1, Circle c2) {
        double dis2=(c1.x-c2.x)*(c1.x-c2.x)+(c1.y-c2.y)*(c1.y-c2.y),
                dis=Math.sqrt(dis2);
        double epos=1e-12;
        if (c1.r+dis<=c2.r+epos) return Math.PI*c1.r2;
        if (c2.r+dis<=c1.r+epos) return Math.PI*c2.r2;
        return insertArea(c1,c2,dis,dis2)+insertArea(c2,c1,dis,dis2);
    }

    private double insertArea(Circle c1, Circle c2, double dis, double dis2) {
        double a=(c1.r2-c2.r2+dis2)/dis/2.;
        double h=Math.sqrt(Math.max(0, c1.r2-a*a));
        double alpha=Math.atan2(h, a);
        return alpha*c1.r2-a*h;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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



