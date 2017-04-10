import java.io.PrintStream;
import java.util.*;

public class Main {

    private class Interval {
        int s, e;
        public Interval(int _s, int _e) {s=_s;e=_e;}
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), d=scanner.nextInt();
        long s0=scanner.nextLong(), as=scanner.nextLong(), cs=scanner.nextLong(),
                rs=scanner.nextLong(), m0=scanner.nextLong(), am=scanner.nextLong(),
                cm=scanner.nextLong(), rm=scanner.nextLong();

        int[] val=new int[n], father=new int[n];
        val[0]=(int)s0;
        for (int i=1;i<n;i++) {
            s0=(s0*as+cs)%rs;
            m0=(m0*am+cm)%rm;
            val[i]=(int)s0;
            father[i]=(int)m0%i;
        }
        Interval[] intervals=new Interval[n];
        for (int i=0;i<n;i++) {
            int start=val[i]-d, end=val[i];
            if (i!=0) {
                start=Math.max(start, intervals[father[i]].s);
                end=Math.min(end, intervals[father[i]].e);
            }
            intervals[i]=new Interval(start, end);
        }
        Arrays.sort(intervals, Comparator.comparingInt(o->o.s));
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
        for (Interval interval: intervals) {
            if (interval.s>interval.e) continue;
            if (!priorityQueue.isEmpty() && priorityQueue.peek()<interval.s)
                priorityQueue.poll();
            priorityQueue.offer(interval.e);
        }
        return String.valueOf(priorityQueue.size());
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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