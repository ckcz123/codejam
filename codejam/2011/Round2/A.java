import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 2
 * Problem A.
 */
public class Main {

    class Interval {
        double s, e, w;
        public Interval(double _s, double _e, double _w) {s=_s;e=_e;w=_w;}
    }

    private String solve(Scanner scanner) {
        double x=scanner.nextDouble(), s=scanner.nextDouble(), r=scanner.nextDouble(),
                t=scanner.nextDouble();
        int n=scanner.nextInt();
        Interval[] input=new Interval[n];
        for (int i=0;i<n;i++) input[i]=new Interval(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        PriorityQueue<Interval> queue=new PriorityQueue<>(Comparator.comparingDouble(i->i.w));
        double start=0;
        for (Interval interval: input) {
            queue.offer(new Interval(start, interval.s, s));
            queue.offer(new Interval(interval.s, interval.e, interval.w+s));
            start=interval.e;
        }
        queue.offer(new Interval(start, x, s));
        double ans=.0;
        while (!queue.isEmpty()) {
            Interval interval=queue.poll();
            double canRun=Math.min(t, (interval.e-interval.s)/(interval.w+r-s)),
                    totalRun=canRun*(interval.w+r-s);
            t-=canRun;
            ans+=canRun+(interval.e-interval.s-totalRun)/interval.w;
        }
        return String.format("%.9f", ans);
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