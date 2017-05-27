import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1C
 * Problem B. Out of Gas
 */
public class Main {

    class Point {
        double time, x;
        public Point(double _time, double _x) {
            time=_time; x=_x;
        }
    }

    private String solve(Scanner scanner) {
        return solveLarge(scanner);
    }

    private String solveLarge(Scanner scanner) {
        double d=scanner.nextDouble();
        int n=scanner.nextInt(), m=scanner.nextInt();
        double[][] cars=new double[n][2];
        for (int i=0;i<n;i++) {
            cars[i][0]=scanner.nextDouble();
            cars[i][1]=scanner.nextDouble();
        }
        ArrayList<Point> list=new ArrayList<>();
        Point curr=new Point(0,0);
        for (double[] car: cars) {
            double t=car[0], x=car[1];
            double dt=t-curr.time, dx=x-curr.x;
            if (x>=d) {
                t=curr.time+(d-curr.x)/dx*dt;
                x=d;
            }
            Point point=new Point(t, x);
            list.add(point);
            curr=point;
            if (x>=d-1e-9) break;
        }
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        while (m-->0) {
            double a=scanner.nextDouble();
            double start=0, end=1e12;
            while (end-start>1e-10) {
                double mid=(start+end)/2;
                if (check(list, a, mid)) end=mid;
                else start=mid;
            }
            double need=Math.sqrt(2*d/a);
            ans.add(String.format("%.9f", start+need));
        }
        return String.join("\n", ans);
    }

    private boolean check(ArrayList<Point> list, double a, double t) {
        for (Point p: list) {
            if (p.time<=t+1e-10) continue;
            double dt=p.time-t, dis=0.5*a*dt*dt;
            if (dis>p.x+1e-10) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d:%s", t, new Main().solve(scanner)));
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