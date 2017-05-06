import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 1B
 * Problem C. Hiking Deer
 */
public class Main {

    class Hiker {
        long start, time;
        int id;
        public Hiker(long s, long t, int i) {
            start=s;
            time=t;
            id=i;
        }
    }

    public String solve(Scanner scanner) {
        PriorityQueue<Hiker> queue=new PriorityQueue<>((o1,o2)->o1.start==o2.start?
                Integer.compare(o1.id, o2.id):Long.compare(o1.start, o2.start));
        int n=0, l=scanner.nextInt();
        for (int i=1;i<=l;i++) {
            int d=scanner.nextInt(), h=scanner.nextInt();
            long m=scanner.nextLong();
            for (int j=0;j<h;j++) {
                queue.offer(new Hiker((360-d)*(m+j), 360*(m+j), n++));
            }
        }

        int increase=0, decrease=n, ans=n;
        boolean[] used=new boolean[n];
        while (increase<n) {
            long time=queue.peek().start;
            while (queue.peek().start==time) {
                Hiker hiker=queue.poll();
                if (!used[hiker.id]) {
                    used[hiker.id]=true;
                    decrease--;
                }
                else increase++;
                hiker.start+=hiker.time;
                queue.offer(hiker);
            }
            ans=Math.min(ans, increase+decrease);
        }
        return String.valueOf(ans);
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
