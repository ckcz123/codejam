import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 AMER Semifinal
 * Problem C. Test Passing Probability
 */
public class Main {

    public String solve(Scanner scanner) throws Exception {
        int m=scanner.nextInt(), q=scanner.nextInt();
        PriorityQueue<Double> queue=new PriorityQueue<>();
        queue.offer(1.);
        while (q-->0) {
            double[] p=new double[4];
            for (int i=0;i<4;i++) p[i]=scanner.nextDouble();
            PriorityQueue<Double> next=new PriorityQueue<>();
            while (!queue.isEmpty()) {
                double x=queue.poll();
                for (int i=0;i<4;i++) {
                    double y=x*p[i];
                    if (next.size()<m) next.offer(y);
                    else if (next.peek()<y) {
                        next.poll(); next.offer(y);
                    }
                }
            }
            queue=next;
        }
        double ans=.0;
        while (!queue.isEmpty())
            ans+=queue.poll();
        return String.format("%.9f", ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
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