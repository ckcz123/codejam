import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 3
 * Problem A. Irregular Cakes
 */
public class Main {

    class Point {
        double x, y;
        public Point(double _x, double _y) {
            x=_x;y=_y;
        }
    }

    public String solve(Scanner scanner) {
        double w=scanner.nextDouble();
        int l=scanner.nextInt(), u=scanner.nextInt(), g=scanner.nextInt();
        Point[] lower=new Point[l], upper=new Point[u];
        for (int i=0;i<l;i++) lower[i]=new Point(scanner.nextDouble(), scanner.nextDouble());
        for (int i=0;i<u;i++) upper[i]=new Point(scanner.nextDouble(), scanner.nextDouble());

        // total area
        double area=.0;
        Point[] curr=new Point[] {upper[0], lower[0]};
        while (true) {
            Point[] next=next(upper, lower, curr[0].x, w);
            if (next==null) break;
            area+=((next[0].y-next[1].y)+(curr[0].y-curr[1].y))*(next[0].x-curr[0].x)/2;
            curr=next;
        }

        double avg=area/g, need=avg;
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        curr=new Point[] {upper[0], lower[0]};
        while (true) {
            Point[] next=next(upper, lower, curr[0].x, w);
            if (next==null) break;
            double s=((next[0].y-next[1].y)+(curr[0].y-curr[1].y))*(next[0].x-curr[0].x)/2;
            if (s>=need-1e-9) {
                // binary search
                double start=curr[0].x, end=next[0].x;
                while (end-start>1e-10) {
                    double mid=(start+end)/2;
                    next=cal(upper, lower, mid);
                    s=((next[0].y-next[1].y)+(curr[0].y-curr[1].y))*(next[0].x-curr[0].x)/2;
                    if (s>need+1e-9) end=mid;
                    else start=mid;
                }
                need=avg;
                ans.add(String.format("%.9f", start));
                curr=next;
            }
            else {
                need-=s;
                curr=next;
            }
        }
        if (ans.size()>g)
            ans.remove(ans.size()-1);
        return String.join("\n", ans);
    }

    private Point[] next(Point[] upper, Point[] lower, double x, double w) {
        if (x>=w-1e-9) return null;
        TreeSet<Double> set=new TreeSet<>();
        for (Point p: upper) set.add(p.x);
        for (Point p: lower) set.add(p.x);
        double nx=set.higher(x);
        return cal(upper, lower, nx);
    }

    private Point[] cal(Point[] upper, Point[] lower, double x) {
        double epos=1e-9;
        Point[] points=new Point[2];
        for (int i=0;i<upper.length-1;i++) {
            Point p1=upper[i], p2=upper[i+1];
            if (p1.x<=x+epos && p2.x>=x-epos) {
                double k=(p2.y-p1.y)/(p2.x-p1.x), b=p1.y-k*p1.x;
                points[0]=new Point(x, k*x+b);
                break;
            }
        }
        for (int i=0;i<lower.length-1;i++) {
            Point p1=lower[i], p2=lower[i+1];
            if (p1.x<=x+epos && p2.x>=x-epos) {
                double k=(p2.y-p1.y)/(p2.x-p1.x), b=p1.y-k*p1.x;
                points[1]=new Point(x, k*x+b);
                break;
            }
        }
        return points;
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