import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Qualification Round
 * Problem D. Hall of Mirrors
 * Only small solved.
 */
public class Main {

    class Point {
        double x, y;
        public Point(double _x, double _y) {x=_x;y=_y;}
        private int gcd(int a, int b) {
            return b==0?a:gcd(b, a%b);
        }
        public Point direction() {
            int x1=(int)x, y1=(int)y;
            if (x1==0) y1=y1==0?0:y1>0?1:-1;
            else if (y1==0) x1=x1>0?1:-1;
            else {
                int g=gcd(Math.abs(x1), Math.abs(y1));
                x1/=g;y1/=g;
            }
            x=x1;y=y1;
            return this;
        }
        public int hashCode() {return 10000*(int)(2*x)+(int)(2*y);}
        public boolean equals(Object another) {
            return (another instanceof Point) && hashCode()==another.hashCode();
        }
        public String toString() {return "["+x+","+y+"]";}
    }

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {

        int r=scanner.nextInt(), c=scanner.nextInt(), d=scanner.nextInt();
        int x=0, y=0;
        for (int i=0;i<r;i++) {
            String s=scanner.next();
            for (int j=0;j<c;j++) {
                if (s.charAt(j)=='X') {
                    x=i-1;y=j-1;
                }
            }
        }
        r-=2;c-=2;
        Point my=new Point(x+.5, y+.5);
        HashSet<Point> set=new HashSet<>();
        Queue<Point> queue=new LinkedList<>();
        set.add(my);
        queue.add(my);
        while (!queue.isEmpty()) {
            Point point=queue.poll();
            int up=-1000*r;
            while (up<point.x) up+=r;
            int right=-1000*c;
            while (right<point.y) right+=c;
            int down=up-r, left=right-c;

            Point[] ps=new Point[] {
                    // down:
                    new Point(2*down-point.x, point.y),
                    // up:
                    new Point(2*up-point.x, point.y),
                    // left:
                    new Point(point.x, 2*left-point.y),
                    // right:
                    new Point(point.x, 2*right-point.y)
            };
            for (Point p: ps) {
                if (set.contains(p)) continue;
                // distance
                int dx=(int)(p.x-my.x), dy=(int)(p.y-my.y);
                if (d*d<dx*dx+dy*dy) continue;
                set.add(p);
                queue.offer(p);
            }
        }
        HashSet<Point> directions=new HashSet<>();
        for (Point p: set) {
            if (p.equals(my)) continue;
            int dx=(int)(p.x-my.x), dy=(int)(p.y-my.y);
            if (d*d<dx*dx+dy*dy) continue;
            directions.add(new Point(dx, dy).direction());
        }
        return String.valueOf(directions.size());
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



