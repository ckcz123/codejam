import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Codejam 2012 Round 2
 * Problem B. Aerobics
 */
public class Main {

    class Point {
        long x, y, r;
        int id;
        public Point(int _id, long _r) {id=_id;r=_r;x=y=0;}
        public void set(long _x, long _y) {x=_x;y=_y;}
    }

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        long w=scanner.nextLong(), l=scanner.nextLong();
        Point[] points=new Point[n];
        for (int i=0;i<n;i++) points[i]=new Point(i, scanner.nextLong());
        Arrays.sort(points, Comparator.comparingLong(p->p.r));

        Random random=new Random();
        for (int i=n-1;i>=0;i--) {
            int cnt=0;
            long x=0, y=0;
            while (cnt<500) {
                x=random.nextInt((int)w+1);
                y=random.nextInt((int)l+1);
                boolean able=true;
                for (int j=i+1;j<n;j++) {
                    long xx=points[j].x, yy=points[j].y;
                    long dis=Math.max(Math.abs(x-xx), Math.abs(y-yy));
                    if (dis<points[i].r+points[j].r) {
                        able=false;break;
                    }
                }
                if (able) break;
                cnt++;
            }
            if (cnt==500) {
                System.err.println("ERROR");
                return "";
            }
            points[i].set(x,y);
        }
        Arrays.sort(points, Comparator.comparingInt(p->p.id));
        return String.join(" ", Arrays.stream(points).flatMap(
                p->Stream.of(String.valueOf(p.x), String.valueOf(p.y)))
                .collect(Collectors.toList()));
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